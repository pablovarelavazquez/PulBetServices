package com.pvv.pulbet.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pvv.pulbet.dao.ApuestaDAO;
import com.pvv.pulbet.dao.UsuarioDAO;
import com.pvv.pulbet.dao.impl.ApuestaDAOImpl;
import com.pvv.pulbet.dao.impl.UsuarioDAOImpl;
import com.pvv.pulbet.dao.util.ConnectionManager;
import com.pvv.pulbet.dao.util.JDBCUtils;
import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.DuplicateInstanceException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.exceptions.MailException;
import com.pvv.pulbet.model.Apuesta;
import com.pvv.pulbet.model.LineaApuesta;
import com.pvv.pulbet.model.Usuario;
import com.pvv.pulbet.service.ApuestaCriteria;
import com.pvv.pulbet.service.ApuestaService;
import com.pvv.pulbet.service.BetStatus;
import com.pvv.pulbet.service.LineaApuestaService;
import com.pvv.pulbet.service.MailService;
import com.pvv.pulbet.service.Results;
import com.pvv.pulbet.velocity.MailEngineBuilder;
import com.pvv.pulbet.velocity.util.MapNames;
import com.pvv.pulbet.velocity.util.TemplateURL;

public class ApuestaServiceImpl implements ApuestaService{

	private static Logger logger = LogManager.getLogger(ApuestaServiceImpl.class);
	private ApuestaDAO apuestaDAO = null;
	private UsuarioDAO usuarioDAO = null;
	private MailService mailService = null;
	private LineaApuestaService lineaService = null;

	public ApuestaServiceImpl() {
		apuestaDAO = new ApuestaDAOImpl();
		usuarioDAO = new UsuarioDAOImpl();
		mailService = new MailServiceImpl();
		lineaService = new LineaApuestaServiceImpl();
	}

	@Override
	public List<Apuesta> findByUsuario(Long id) throws DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Id = {}", id);
		}

		Connection connection = null;
		
		try {

			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(true);

			return apuestaDAO.findByUsuario(connection,id);

		} catch (SQLException e){
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
		}
	}


	@Override
	public long delete(Long id)  throws InstanceNotFoundException, DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Id = {}", id);
		}
		
		Connection connection = null;
		boolean commit = false;
		Long result = null;

		try {

			connection = ConnectionManager.getConnection();

			connection.setTransactionIsolation(
					Connection.TRANSACTION_READ_COMMITTED);

			connection.setAutoCommit(false);

			result = apuestaDAO.delete(connection, id);   

			commit = true;            
			return result;

		} catch (SQLException e) {
			logger.warn(e.getMessage(), e);
			throw new DataException(e);

		} finally {
			JDBCUtils.closeConnection(connection, commit);
		}	
	}

	@Override
	public Results<Apuesta> findByCriteria(ApuestaCriteria apuesta,Boolean history, int startIndex, int count) throws DataException {
		
		
		if(logger.isDebugEnabled()) {
			logger.debug("ApuestaCriteria = {}", apuesta);
		}
		
		Connection connection = null;
		boolean commit = false;
		Results<Apuesta> results = null;

		try {

			connection = ConnectionManager.getConnection();

			connection.setTransactionIsolation(
					Connection.TRANSACTION_READ_COMMITTED);

			connection.setAutoCommit(false);

			results = apuestaDAO.findByCriteria(connection, apuesta, history, startIndex, count);

			commit = true;    

			return results;

		} catch (SQLException e) {
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection, commit);
		}	
	}

	@Override
	public Apuesta comprobarApuesta(Apuesta apuesta) throws DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("ApuestaCriteria = {}", apuesta);
		}
		
		Connection connection = null;
		boolean commit = false;
		boolean acertada = true;
		boolean finalizada = false;
		int cont = 0;
		Map<String, Object> mapa = null;

		try {

			connection = ConnectionManager.getConnection();

			connection.setTransactionIsolation(
					Connection.TRANSACTION_READ_COMMITTED);

			//Iniciamos transaccion.
			connection.setAutoCommit(false);

			
			List<LineaApuesta> lineas = new ArrayList<LineaApuesta>();
			//Intentamos procesar as lineas de aposta.
			for(LineaApuesta l : apuesta.getLineas()){

				if(l.getProcesado()==BetStatus.PENDIENTE) {
					l = lineaService.comprobarLinea(l);
				}
				lineas.add(l);
			}

			apuesta.setLineas(lineas);	

			//Comprobamos se estan todas procesadas.
			for(LineaApuesta l : lineas) {
				if(l.getProcesado()!=BetStatus.PENDIENTE) {
					cont++;
				}
			}

			//Comprobamos se esta finalizada e acertada.
			if(cont == lineas.size()) {

				for(LineaApuesta l : lineas) {
					if(l.getProcesado()==BetStatus.FALLADA){
						acertada = false;
					}


				}
				finalizada =  true;
			}

			//Se esta finalizada
			if (finalizada) {
				if(acertada) {
					//Se esta acertada, editamos aposta, banco do usuario e enviamos email.
					apuesta.setProcesado(BetStatus.ACERTADA);
					apuestaDAO.updateEstado(connection, apuesta);
					
					Usuario u = usuarioDAO.findById(connection, apuesta.getIdUsuario());
					
					Usuario changes = new Usuario();
					changes.setIdUsuario(u.getIdUsuario());
					changes.setBanco(u.getBanco() + apuesta.getGanancias());
					
					usuarioDAO.update(connection, changes);
					
					try {
						mapa = new HashMap<String, Object>();
						mapa.put(MapNames.NOMBRE, u.getNome());
						String template = TemplateURL.BET_WIN_TEMPLATE;
						String mensaje = MailEngineBuilder.createMail(template, mapa);
						mailService.sendMail(mensaje, "Has ganado tu apuesta" ,u.getEmail());
					} catch (MailException e) {
						logger.warn(e.getMessage(),e);
					}
					
				}else {
					//Se esta fallada, editamos usuario
					apuesta.setProcesado(BetStatus.FALLADA);
					apuestaDAO.updateEstado(connection, apuesta);
				}
			}


			commit = true;    
			return apuesta;

		} catch (SQLException e) {
			logger.warn(e.getMessage(), e);
			throw new DataException(e);

		} finally {
			//Se todo foi ben commit, senon rollback.
			JDBCUtils.closeConnection(connection, commit);
		}
	}

	@Override
	public Apuesta create(Apuesta a) throws DuplicateInstanceException, DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("ApuestaCriteria = {}", a);
		}
		
		Connection connection = null;
		boolean commit = false;
		

		try {

			connection = ConnectionManager.getConnection();

			connection.setTransactionIsolation(
					Connection.TRANSACTION_READ_COMMITTED);

			connection.setAutoCommit(false);

			a = apuestaDAO.create(connection, a);; 

			
			commit = true;    
			return a;

		} catch (SQLException e) {
			logger.warn(e.getMessage(), e);
			throw new DataException(e);

		} finally {
			JDBCUtils.closeConnection(connection, commit);
		}
	}

	@Override
	public Apuesta findById(Long id) throws InstanceNotFoundException, DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Id = {}", id);
		}
		
		Connection connection = null;

		try {

			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(true);

			return apuestaDAO.findById(connection, id);

		} catch (SQLException e){
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
		}
	}

	@Override
	public List<Apuesta> findAll() throws DataException {
		Connection connection = null;

		try {

			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(true);

			return apuestaDAO.findAll(connection);

		} catch (SQLException e){
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
		}
	}

}

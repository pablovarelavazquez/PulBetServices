package com.pvv.pulbet.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pvv.pulbet.dao.ApuestaDAO;
import com.pvv.pulbet.dao.impl.ApuestaDAOImpl;
import com.pvv.pulbet.dao.util.ConnectionManager;
import com.pvv.pulbet.dao.util.JDBCUtils;
import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.DuplicateInstanceException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.Apuesta;
import com.pvv.pulbet.model.LineaApuesta;
import com.pvv.pulbet.service.ApuestaCriteria;
import com.pvv.pulbet.service.ApuestaService;
import com.pvv.pulbet.service.LineaApuestaService;

public class ApuestaServiceImpl implements ApuestaService{

	private static Logger logger = LogManager.getLogger(ApuestaServiceImpl.class);
	private ApuestaDAO apuestaDAO = null;
	private LineaApuestaService lineaService = null;

	public ApuestaServiceImpl() {
		apuestaDAO = new ApuestaDAOImpl();
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

			return apuestaDAO.findByUsuario(connection, id);

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
	public List<Apuesta> findHistorial(ApuestaCriteria apuesta) throws DataException {
		
		
		if(logger.isDebugEnabled()) {
			logger.debug("ApuestaCriteria = {}", apuesta);
		}
		
		Connection connection = null;
		boolean commit = false;
		List<Apuesta> result = null;

		try {

			connection = ConnectionManager.getConnection();

			connection.setTransactionIsolation(
					Connection.TRANSACTION_READ_COMMITTED);

			connection.setAutoCommit(false);

			result = apuestaDAO.findByCriteria(connection, apuesta); 

			List<Apuesta> finalizados = new ArrayList<Apuesta>();

			for (Apuesta a: result) {
				if(a.getProcesado()!=0) {
					finalizados.add(a);
				}
			}

			commit = true;    

			return finalizados;

		} catch (SQLException e) {
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection, commit);
		}	
	}

	@Override
	public List<Apuesta> findOpenBets(ApuestaCriteria apuesta) throws DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("ApuestaCriteria = {}", apuesta);
		}
		
		Connection connection = null;
		boolean commit = false;
		List<Apuesta> result = null;

		try {

			connection = ConnectionManager.getConnection();

			connection.setTransactionIsolation(
					Connection.TRANSACTION_READ_COMMITTED);

			connection.setAutoCommit(false);

			result = apuestaDAO.findByCriteria(connection, apuesta); 

			List<Apuesta> noFinalizados = new ArrayList<Apuesta>();

			for (Apuesta a: result) {
				if(a.getProcesado()==0) {
					noFinalizados.add(a);
				}
			}

			commit = true;    
			return noFinalizados;

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

		try {

			connection = ConnectionManager.getConnection();

			connection.setTransactionIsolation(
					Connection.TRANSACTION_READ_COMMITTED);

			connection.setAutoCommit(false);

			List<LineaApuesta> lineas = new ArrayList<LineaApuesta>();
			//Procesar lineas se se pode
			for(LineaApuesta l : apuesta.getLineas()){

				if(l.getProcesado()==0) {
					l = lineaService.comprobarLinea(l);
				}
				lineas.add(l);
			}

			apuesta.setLineas(lineas);	

			//Mirar se todas as lineas estan procesadas, se e asi comprobamos se a aposta esta acertada
			for(LineaApuesta l : lineas) {
				if(l.getProcesado()!=0) {
					cont++;
				}
			}


			if(cont == lineas.size()) {

				for(LineaApuesta l : lineas) {
					if(l.getProcesado()==2){
						acertada = false;
					}


				}
				finalizada =  true;
			}

			if (finalizada) {
				if(acertada) {
					apuesta.setProcesado(1);
					apuestaDAO.updateEstado(connection, apuesta);
				}else {
					apuesta.setProcesado(2);
					apuestaDAO.updateEstado(connection, apuesta);
				}
			}


			commit = true;    
			return apuesta;

		} catch (SQLException e) {
			logger.warn(e.getMessage(), e);
			throw new DataException(e);

		} finally {
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

}

package com.pvv.pulbet.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pvv.pulbet.dao.EventoDAO;
import com.pvv.pulbet.dao.LineaApuestaDAO;
import com.pvv.pulbet.dao.impl.EventoDAOImpl;
import com.pvv.pulbet.dao.impl.LineaApuestaDAOImpl;
import com.pvv.pulbet.dao.util.ConnectionManager;
import com.pvv.pulbet.dao.util.JDBCUtils;
import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.DuplicateInstanceException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.Apuesta;
import com.pvv.pulbet.model.LineaApuesta;
import com.pvv.pulbet.model.LineaApuestaId;
import com.pvv.pulbet.service.BetStatus;
import com.pvv.pulbet.service.LineaApuestaService;

public class LineaApuestaServiceImpl implements LineaApuestaService{

	private static Logger logger = LogManager.getLogger(LineaApuestaServiceImpl.class);
	private LineaApuestaDAO lineaApuestaDAO = null;
	private EventoDAO eventoDAO = null;

	public LineaApuestaServiceImpl() {
		lineaApuestaDAO =  new LineaApuestaDAOImpl();
		eventoDAO = new EventoDAOImpl();
	}

	@Override
	public LineaApuesta comprobarLinea(LineaApuesta lineaApuesta) throws DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("LineaApuesta = {}", lineaApuesta);
		}
		
		Connection connection = null;
		boolean commit = false;
		boolean correcta = false;

		try {

			connection = ConnectionManager.getConnection();

			connection.setTransactionIsolation(
					Connection.TRANSACTION_READ_COMMITTED);

			connection.setAutoCommit(false);

			List<Long> resultados = eventoDAO.findResultadoFinal(connection, lineaApuesta.getIdEvento());

			if((resultados!=null) && !(resultados.isEmpty())) {
				
				if(resultados.contains(lineaApuesta.getIdResultado())) {
					correcta = true;
				}

			if(correcta) {
				lineaApuesta.setProcesado(BetStatus.ACERTADA);
				lineaApuestaDAO.update(connection, lineaApuesta);
			}else {
				lineaApuesta.setProcesado(BetStatus.FALLADA);
				lineaApuestaDAO.update(connection, lineaApuesta);
			}
			}

			commit = true;    
			return lineaApuesta;

		} catch (SQLException e) {
			logger.warn(e.getMessage(), e);
			throw new DataException(e);

		} finally {
			JDBCUtils.closeConnection(connection, commit);
		}

	}
	
	@Override
	public LineaApuesta create(LineaApuesta lineaApuesta) throws DuplicateInstanceException, DataException {
		if(logger.isDebugEnabled()) {
			logger.debug("LineaApuesta = {}", lineaApuesta);
		}
		
		Connection connection = null;
		boolean commit = false;
		

		try {

			connection = ConnectionManager.getConnection();

			connection.setTransactionIsolation(
					Connection.TRANSACTION_READ_COMMITTED);

			connection.setAutoCommit(false);

			lineaApuesta = lineaApuestaDAO.create(connection, lineaApuesta);; 


			commit = true;    
			return lineaApuesta;

		} catch (SQLException e) {
			logger.warn(e.getMessage(), e);
			throw new DataException(e);

		} finally {
			JDBCUtils.closeConnection(connection, commit);
		}
	}


	@Override
	public LineaApuesta update(LineaApuesta lineaApuesta) throws InstanceNotFoundException, DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("LineaApuesta = {}", lineaApuesta);
		}

		Connection connection = null;
		try {

			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(true);

			lineaApuestaDAO.update(connection,lineaApuesta);

			return lineaApuesta;
		} catch (SQLException e) {
			logger.warn(e.getMessage(), e);
			throw new DataException(e);

		} finally {
			JDBCUtils.closeConnection(connection);
		}

	}
	
	@Override
	public int delete(LineaApuestaId id) throws InstanceNotFoundException, DataException {
		if(logger.isDebugEnabled()) {
			logger.debug("Id = {}", id);
		}
		
		Connection connection = null;
		boolean commit = false;
		int result;

		try {

			connection = ConnectionManager.getConnection();

			connection.setTransactionIsolation(
					Connection.TRANSACTION_READ_COMMITTED);

			connection.setAutoCommit(false);

			result = lineaApuestaDAO.delete(connection, id);   

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
	public LineaApuesta findById(LineaApuestaId id) throws InstanceNotFoundException, DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Id = {}", id);
		}
		
		Connection connection = null;

		try {

			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(true);

			return lineaApuestaDAO.findById(connection, id);	

		} catch (SQLException e){
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
		}
	}




}

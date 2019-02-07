package com.pvv.pulbet.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pvv.pulbet.dao.EventoDAO;
import com.pvv.pulbet.dao.impl.EventoDAOImpl;
import com.pvv.pulbet.dao.util.ConnectionManager;
import com.pvv.pulbet.dao.util.JDBCUtils;
import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.Evento;
import com.pvv.pulbet.service.EventoCriteria;
import com.pvv.pulbet.service.EventoService;

public class EventoServiceImpl implements EventoService{
	
	private static Logger logger = LogManager.getLogger(EventoServiceImpl.class);
	private EventoDAO eventoDAO = null;
	
	public EventoServiceImpl() {
		eventoDAO = new EventoDAOImpl(); 
	}

	@Override
	public List<Evento> findByCriteria(EventoCriteria evento) throws DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("EventoCriteria = {}", evento);
		}
		
		Connection c = null;
		
		try {
			
			c = ConnectionManager.getConnection();
			c.setAutoCommit(true);
			
			List<Evento> eventos = eventoDAO.findByCriteria(c,evento); 
			
			return eventos;
			
		} catch (SQLException e){
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(c);
		}
	}

	@Override
	public Evento findById(Integer id) throws InstanceNotFoundException, DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Id = {}", id);
		}
		
		Connection connection = null;
		
		try {

			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(true);

			return eventoDAO.findById(connection, id);

		} catch (SQLException e){
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
		}
	}

}

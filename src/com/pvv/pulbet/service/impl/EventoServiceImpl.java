package com.pvv.pulbet.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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
	
	private EventoDAO eventoDAO = null;
	
	public EventoServiceImpl() {
		eventoDAO = new EventoDAOImpl(); 
	}

	@Override
	public List<Evento> findByCriteria(EventoCriteria evento) throws DataException {
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
		// TODO Auto-generated method stub
		return null;
	}

}

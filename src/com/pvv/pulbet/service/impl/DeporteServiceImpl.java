package com.pvv.pulbet.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pvv.pulbet.dao.DeporteDAO;
import com.pvv.pulbet.dao.impl.DeporteDAOImpl;
import com.pvv.pulbet.dao.util.ConnectionManager;
import com.pvv.pulbet.dao.util.JDBCUtils;
import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.Deporte;
import com.pvv.pulbet.service.DeporteService;

public class DeporteServiceImpl implements DeporteService{
	
	private static Logger logger = LogManager.getLogger(ApuestaServiceImpl.class);
	private DeporteDAO deporteDAO = null;
	
	public DeporteServiceImpl() {
		deporteDAO = new DeporteDAOImpl();
	}

	@Override
	public List<Deporte> findAll() throws DataException {

		Connection connection = null;
		
		try {

			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(true);

			return deporteDAO.findAll(connection);

		} catch (SQLException e){
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
		}
	}

	@Override
	public List<Deporte> findByNombre(String nombre) throws DataException {
		if(logger.isDebugEnabled()) {
			logger.debug("Nombre = {}", nombre);
		}

		Connection connection = null;
		
		try {

			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(true);

			return deporteDAO.findByNombre(connection, nombre);

		} catch (SQLException e){
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
		}
	}

	@Override
	public Deporte findById(Long id) throws InstanceNotFoundException, DataException {
		if(logger.isDebugEnabled()) {
			logger.debug("Id = {}", id);
		}

		Connection connection = null;
		
		try {

			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(true);

			return deporteDAO.findById(connection, id);

		} catch (SQLException e){
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
		}
	}

}

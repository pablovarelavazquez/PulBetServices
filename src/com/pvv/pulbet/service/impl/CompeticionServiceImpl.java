package com.pvv.pulbet.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pvv.pulbet.dao.CompeticionDAO;
import com.pvv.pulbet.dao.impl.CompeticionDAOImpl;
import com.pvv.pulbet.dao.util.ConnectionManager;
import com.pvv.pulbet.dao.util.JDBCUtils;
import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.Competicion;
import com.pvv.pulbet.service.CompeticionService;

public class CompeticionServiceImpl implements CompeticionService{

	private static Logger logger = LogManager.getLogger(CompeticionServiceImpl.class);
	private CompeticionDAO competicionDAO = null;
	
	public CompeticionServiceImpl() {
		competicionDAO = new CompeticionDAOImpl();
	}
	
	@Override
	public Competicion findById(Long id) throws InstanceNotFoundException, DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Id = {}", id);
		}

		Connection connection = null;
		
		try {

			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(true);

			return competicionDAO.findById(connection, id);

		} catch (SQLException e){
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
		}
	}

	@Override
	public List<Competicion> findByDeporte(Long id) throws DataException {
		if(logger.isDebugEnabled()) {
			logger.debug("Id = {}", id);
		}

		Connection connection = null;
		
		try {

			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(true);

			return competicionDAO.findByDeporte(connection, id);

		} catch (SQLException e){
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
		}
	}

	@Override
	public List<Competicion> findAll() throws DataException {

		Connection connection = null;
		
		try {

			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(true);

			return competicionDAO.findAll(connection);

		} catch (SQLException e){
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
		}
	}

	@Override
	public List<Competicion> findByNombre(String nombre) throws DataException {
		if(logger.isDebugEnabled()) {
			logger.debug("Nombre = {}", nombre);
		}

		Connection connection = null;
		
		try {

			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(true);

			return competicionDAO.findByNombre(connection, nombre);

		} catch (SQLException e){
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
		}
	}

}

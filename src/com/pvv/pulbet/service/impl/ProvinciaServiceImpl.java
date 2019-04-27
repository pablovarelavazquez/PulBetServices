package com.pvv.pulbet.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pvv.pulbet.dao.ProvinciaDAO;
import com.pvv.pulbet.dao.impl.ProvinciaDAOImpl;
import com.pvv.pulbet.dao.util.ConnectionManager;
import com.pvv.pulbet.dao.util.JDBCUtils;
import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.Provincia;
import com.pvv.pulbet.service.ProvinciaService;

public class ProvinciaServiceImpl implements ProvinciaService{

	private ProvinciaDAO provinciaDAO = null;
	private static Logger logger = LogManager.getLogger(PaisServiceImpl.class);

	public ProvinciaServiceImpl() {
		provinciaDAO = new ProvinciaDAOImpl();
	}

	@Override
	public Provincia findById(Long id) throws InstanceNotFoundException, DataException {
		if(logger.isDebugEnabled()) {
			logger.debug("Id = {}", id);
		}

		Connection connection = null;

		try {

			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(true);

			return provinciaDAO.findById(connection, id);

		} catch (SQLException e){
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
		}
	}

	@Override
	public List<Provincia> findAll() throws DataException {
		Connection connection = null;

		try {

			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(true);

			return provinciaDAO.findAll(connection);

		} catch (SQLException e){
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
		}
	}

	@Override
	public List<Provincia> findByNombre(String nome) throws DataException {
		if(logger.isDebugEnabled()) {
			logger.debug("Nombre = {}", nome);
		}

		Connection connection = null;
		
		try {

			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(true);

			return provinciaDAO.findByNombre(connection, nome);

		} catch (SQLException e){
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
		}
	}

	@Override
	public List<Provincia> findByPais(Integer id) throws DataException {
		if(logger.isDebugEnabled()) {
			logger.debug("ID = {}", id);
		}

		Connection connection = null;
		
		try {

			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(true);

			return provinciaDAO.findByPais(connection, id);

		} catch (SQLException e){
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
		}
	}

}

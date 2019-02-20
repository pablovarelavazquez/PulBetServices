package com.pvv.pulbet.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pvv.pulbet.dao.PaisDAO;
import com.pvv.pulbet.dao.impl.PaisDAOImpl;
import com.pvv.pulbet.dao.util.ConnectionManager;
import com.pvv.pulbet.dao.util.JDBCUtils;
import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.Pais;
import com.pvv.pulbet.service.PaisService;

public class PaisServiceImpl implements PaisService{
	
	private PaisDAO paisDAO = null;
	private static Logger logger = LogManager.getLogger(ApuestaServiceImpl.class);
	
	public PaisServiceImpl() {
		paisDAO = new PaisDAOImpl();
	}

	@Override
	public Pais findById(int id, String idioma) throws InstanceNotFoundException, DataException {

		if(logger.isDebugEnabled()) {
			logger.debug("Id = {}", id);
		}
		
		Connection connection = null;

		try {

			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(true);

			return paisDAO.findById(connection, id, idioma);

		} catch (SQLException e){
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
		}
	}

	@Override
	public List<Pais> findAll(String idioma) throws DataException {
		Connection connection = null;
		
		try {

			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(true);

			return paisDAO.findAll(connection, idioma);

		} catch (SQLException e){
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
		}
	}

	@Override
	public List<Pais> findByNombre(String nome, String idioma) throws DataException {
		if(logger.isDebugEnabled()) {
			logger.debug("Nombre = {}", nome);
		}

		Connection connection = null;
		
		try {

			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(true);

			return paisDAO.findByNombre(connection, nome, idioma);

		} catch (SQLException e){
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
		}
	}

}

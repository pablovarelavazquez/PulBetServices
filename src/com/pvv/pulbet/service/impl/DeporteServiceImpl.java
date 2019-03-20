package com.pvv.pulbet.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pvv.pulbet.cache.Cache;
import com.pvv.pulbet.cache.CacheManager;
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
	public List<Deporte> findAll(String idioma) throws DataException {
		
		Cache<String, List<Deporte>> cache = CacheManager.getInstance().getCache(CacheNames.DEPORTES_LIST, String.class, List.class);
		
		List<Deporte> todas = cache.get(idioma);
		
		Connection connection = null;
		
		try {

			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(true);

			return deporteDAO.findAll(connection, idioma);

		} catch (SQLException e){
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
		}
	}

	@Override
	public List<Deporte> findByNombre(String nombre, String idioma) throws DataException {
		if(logger.isDebugEnabled()) {
			logger.debug("Nombre = {}", nombre);
		}

		Connection connection = null;
		
		try {

			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(true);

			return deporteDAO.findByNombre(connection, nombre, idioma);

		} catch (SQLException e){
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
		}
	}

	@Override
	public Deporte findById(Long id, String idioma) throws InstanceNotFoundException, DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Id = {}", id);
		}
		
		Cache<Long, Deporte> cache = CacheManager.getInstance().getCache(CacheNames.DEPORTES, Long.class, Deporte.class);
		
		Deporte d = cache.get(id);
		
		if(d!=null) {
			//exito ou acierto cache
			if (logger.isDebugEnabled()) {
				logger.debug("Acierto cache: {}", id);
			}
			
		} else {
			
			//fallo cache
			if (logger.isDebugEnabled()) {
				logger.debug("Fallo cache: {}", id);
			}

		Connection connection = null;
		
		try {

			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(true);

			d = deporteDAO.findById(connection, id, idioma);

		} catch (SQLException e){
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
		}
		
		cache.put(id, d);
		
	}
		return d;
	}

}

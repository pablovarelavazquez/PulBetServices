package com.pvv.pulbet.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pvv.pulbet.dao.UsuarioDAO;
import com.pvv.pulbet.dao.impl.UsuarioDAOImpl;
import com.pvv.pulbet.dao.util.ConnectionManager;
import com.pvv.pulbet.dao.util.JDBCUtils;
import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.model.Usuario;
import com.pvv.pulbet.service.BancoService;

public class BancoServiceImpl implements BancoService{
	
	private static Logger logger = LogManager.getLogger(BancoServiceImpl.class);
	private UsuarioDAO dao = null;
	
	public BancoServiceImpl() {
		dao = new UsuarioDAOImpl();
	}
	
	@Override
	public void ingresar(Long idUsuario, Double cantidad) throws DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Id = {}, cantidad = {}", idUsuario, cantidad);
		}
		
		Connection connection = null;
		Usuario u = null;
		try {
			
			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(true);
			
			u = dao.findById(connection, idUsuario);
			
			u.setBanco(u.getBanco()+cantidad);
			
			dao.update(connection, u);
			
		} catch (SQLException e){
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
		}
	}

	@Override
	public void retirar(Long idUsuario, Double cantidad) throws DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Id = {}, cantidad = {}", idUsuario, cantidad);
		}
		
		Connection connection = null;
		Usuario u = null;
		try {
			
			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(true);
			
			u = dao.findById(connection, idUsuario);
			
			if (u.getBanco() >= cantidad) {
				u.setBanco(u.getBanco()-cantidad);
			}
			
			dao.update(connection, u);
			
			
		} catch (SQLException e){
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
		}
		
	}

}

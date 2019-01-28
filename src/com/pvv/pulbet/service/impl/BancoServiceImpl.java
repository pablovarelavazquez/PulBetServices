package com.pvv.pulbet.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.pvv.pulbet.dao.UsuarioDAO;
import com.pvv.pulbet.dao.impl.UsuarioDAOImpl;
import com.pvv.pulbet.dao.util.ConnectionManager;
import com.pvv.pulbet.dao.util.JDBCUtils;
import com.pvv.pulbet.exception.DataException;
import com.pvv.pulbet.model.Usuario;
import com.pvv.pulbet.service.BancoService;

public class BancoServiceImpl implements BancoService{
	
	private UsuarioDAO dao = null;
	private static ResourceBundle dbConfiguration = ResourceBundle.getBundle("ServiceConfiguration");
	
	public BancoServiceImpl() {
		dao = new UsuarioDAOImpl();
	}
	
	@Override
	public void ingresar(Long idUsuario, Double cantidad) throws Exception {
		Connection connection = null;
		Usuario u = null;
		try {
			
			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(true);
			
			u = dao.findById(connection, idUsuario);
			
			u.setBanco(u.getBanco()+cantidad);
			
			dao.update(connection, u);
			
		} catch (SQLException e){
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
		}
	}

	@Override
	public void retirar(Long idUsuario, Double cantidad) throws Exception {
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
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
		}
		
	}

}

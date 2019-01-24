package com.pvv.pulbet.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.pvv.pulbet.dao.UsuarioDAO;
import com.pvv.pulbet.dao.impl.UsuarioDAOImpl;
import com.pvv.pulbet.dao.util.ConnectionManager;
import com.pvv.pulbet.dao.util.JDBCUtils;
import com.pvv.pulbet.model.Usuario;
import com.pvv.pulbet.service.MailService;
import com.pvv.pulbet.service.UsuarioService;

public class UsuarioServiceImpl implements UsuarioService{
	
	
	UsuarioDAO dao = null;
	
	public UsuarioServiceImpl() {
		dao = new UsuarioDAOImpl();
	}

	@Override
	public Usuario findById(Integer id) throws Exception {
		Connection c = null;
		
	try {
		
		c = ConnectionManager.getConnection();
		c.setAutoCommit(true);
		
		Usuario u = dao.findById(c,id); 
		
		return u;
		
	} catch (SQLException e){
		e.printStackTrace();
		throw e;
	} finally {
		JDBCUtils.closeConnection(c);
	}
		
	}

	@Override
	public Usuario create(Usuario u) throws Exception {
		boolean commit = false;
		Connection c = null;
		MailService mailService = null;
		
	try {
		mailService = new MailServiceImpl();
		c = ConnectionManager.getConnection();
		c.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		c.setAutoCommit(false);
		
		
		
		u = dao.create(c,u); 
		mailService.sendMail("Benvido "+u.getNome(), "Blablabla", "pulbetsoporte@gmail.com");
		
		
		commit=true;
		
		return u;
		
	} catch (SQLException e){
		e.printStackTrace();
		throw e;
	} finally {
		JDBCUtils.closeConnection(c,commit);
	}
	}
	
	@Override
	public Usuario login(String email, String password) throws Exception {
		Connection connection = null;
		
	try {
		connection = ConnectionManager.getConnection();
		connection.setAutoCommit(true);
		
		
		
		if( email == null ) {
			return null;
		}
		
		if( password == null ) {
			return null;
		}
		
		Usuario u = dao.findByEmail(connection, email);
		
		if(u == null) {
			return u;
		} 
		
		if(u.getPassword().equals(password)) {
			System.out.println("Usuario "+u.getEmail()+" autenticado!");
			return u;
		} else {
			return null;
		}
		
		
				
				
			} catch (SQLException e){
				e.printStackTrace();
				throw e;
			} finally {
				JDBCUtils.closeConnection(connection);
			}
		
	}

	@Override
	public void update(Usuario u) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(Usuario u) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}



}

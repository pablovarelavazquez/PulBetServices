package com.pvv.pulbet.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.pvv.pulbet.dao.UsuarioDAO;
import com.pvv.pulbet.dao.impl.UsuarioDAOImpl;
import com.pvv.pulbet.dao.util.ConnectionManager;
import com.pvv.pulbet.dao.util.JDBCUtils;
import com.pvv.pulbet.exception.DataException;
import com.pvv.pulbet.model.Usuario;
import com.pvv.pulbet.service.BancoService;
import com.pvv.pulbet.service.MailService;
import com.pvv.pulbet.service.UsuarioService;

public class UsuarioServiceImpl implements UsuarioService{
	
	
	UsuarioDAO dao = null;
	BancoService banco = null;
	
	public UsuarioServiceImpl() {
		dao = new UsuarioDAOImpl();
		banco = new BancoServiceImpl();
	}

	@Override
	public Usuario findById(Long id) throws Exception {
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
		mailService.sendMail("Benvido a PulBet", "Benvido "+u.getNome(), "pulbetsoporte@gmail.com");
		
		
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
	public long closeAccount(Usuario u) throws Exception {
		Connection connection = null;
        boolean commit = false;
        Long result = null;

        try {
          
            connection = ConnectionManager.getConnection();

            connection.setTransactionIsolation(
                    Connection.TRANSACTION_READ_COMMITTED);

            connection.setAutoCommit(false);
            
            if(u.getBanco()>0d) {
            	
            banco.retirar(u.getIdUsuario(), u.getBanco());
            
            result = dao.delete(connection, u.getIdUsuario());   
            
            } else if ( u.getBanco()<=0d) {
            	
            	result = dao.delete(connection, u.getIdUsuario());
            	
            }
            
            
            commit = true;            
            return result;
            
        } catch (SQLException e) {
            throw new DataException(e);

        } finally {
        	JDBCUtils.closeConnection(connection, commit);
        }	
	}



}

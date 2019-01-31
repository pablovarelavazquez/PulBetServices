package com.pvv.pulbet.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.pvv.pulbet.dao.DireccionDAO;
import com.pvv.pulbet.dao.UsuarioDAO;
import com.pvv.pulbet.dao.impl.DireccionDAOImpl;
import com.pvv.pulbet.dao.impl.UsuarioDAOImpl;
import com.pvv.pulbet.dao.util.ConnectionManager;
import com.pvv.pulbet.dao.util.JDBCUtils;
import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.DuplicateInstanceException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.exceptions.MailException;
import com.pvv.pulbet.model.Direccion;
import com.pvv.pulbet.model.Usuario;
import com.pvv.pulbet.service.BancoService;
import com.pvv.pulbet.service.MailService;
import com.pvv.pulbet.service.UsuarioService;

public class UsuarioServiceImpl implements UsuarioService{
	
	
	UsuarioDAO usuarioDAO = null;
	DireccionDAO direccionDAO = null;
	BancoService banco = null;
	
	public UsuarioServiceImpl() {
		usuarioDAO = new UsuarioDAOImpl();
		banco = new BancoServiceImpl();
		direccionDAO = new DireccionDAOImpl();
	}

	@Override
	public Usuario findById(Long id) throws InstanceNotFoundException, DataException {
		Connection c = null;
		
	try {
		
		c = ConnectionManager.getConnection();
		c.setAutoCommit(true);
		
		Usuario u = usuarioDAO.findById(c,id); 
		
		return u;
		
	} catch (SQLException e){
		throw new DataException(e);
	} finally {
		JDBCUtils.closeConnection(c);
	}
		
	}

	@Override
	public Usuario create(Usuario u)
			throws  DuplicateInstanceException, DataException, MailException {
		boolean commit = false;
		Connection c = null;
		MailService mailService = null;
		
	try {
		mailService = new MailServiceImpl();
		c = ConnectionManager.getConnection();
		c.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		c.setAutoCommit(false);
		
		
		
		u = usuarioDAO.create(c,u); 
		mailService.sendMail("Benvido a PulBet", "Benvido "+u.getNome(), "pulbetsoporte@gmail.com");
		
		
		commit=true;
		
		return u;
		
	} catch (SQLException e){
		throw new DataException(e);
	} finally {
		JDBCUtils.closeConnection(c,commit);
	}
	}
	
	@Override
	public Usuario login(String email, String password) throws DataException {
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
		
		Usuario u = usuarioDAO.findByEmail(connection, email);
		
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
				throw new DataException(e);
			} finally {
				JDBCUtils.closeConnection(connection);
			}
		
	}

	@Override
	public void update(Usuario u) 
			throws InstanceNotFoundException, DataException {
		
	    Connection connection = null;
        boolean commit = false;

        try {
          
            connection = ConnectionManager.getConnection();

            connection.setTransactionIsolation(
                    Connection.TRANSACTION_READ_COMMITTED);

            connection.setAutoCommit(false);

            usuarioDAO.update(connection,u);
            commit = true;
            
        } catch (SQLException e) {
            throw new DataException(e);

        } finally {
        	JDBCUtils.closeConnection(connection, commit);
        }
	}

	@Override
	public long delete(Long id) throws DataException {
		Connection connection = null;
        boolean commit = false;
        Long result = null;
        Usuario u = null;

        try {
          
            connection = ConnectionManager.getConnection();

            connection.setTransactionIsolation(
                    Connection.TRANSACTION_READ_COMMITTED);

            connection.setAutoCommit(false);
            
            u = usuarioDAO.findById(connection, id);
            
            if(u.getBanco()>0d) {
            	
            banco.retirar(u.getIdUsuario(), u.getBanco());
            
            result = usuarioDAO.delete(connection, id);   
            
            } else if ( u.getBanco()<=0d) {
            	
            	result = usuarioDAO.delete(connection, id);
            	
            }
            
            
            commit = true;            
            return result;
            
        } catch (SQLException e) {
            throw new DataException(e);

        } finally {
        	JDBCUtils.closeConnection(connection, commit);
        }	
	}

	@Override
	public void editDireccion(Direccion direccion, Usuario u) throws InstanceNotFoundException, DataException {
		  Connection connection = null;
	        boolean commit = false;
	        Direccion d = null;

	        try {
	          
	            connection = ConnectionManager.getConnection();

	            connection.setTransactionIsolation(
	                    Connection.TRANSACTION_READ_COMMITTED);

	            connection.setAutoCommit(false);

	            d = u.getDireccion(); 
	            
	            direccionDAO.delete(connection, d.getId());
	            commit = true;
	            
	            u.setDireccion(direccion);
	            direccionDAO.create(connection, direccion);
	            
	        } catch (SQLException e) {
	            throw new DataException(e);

	        } finally {
	        	JDBCUtils.closeConnection(connection, commit);
	        }
	}



}

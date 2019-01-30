package com.pvv.pulbet.dao;

import java.sql.Connection;
import java.util.List;

import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.DuplicateInstanceException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.Usuario;

public interface UsuarioDAO {

	public Usuario findById(Connection connection, Long id) throws InstanceNotFoundException, DataException;
	
	public Usuario findByEmail(Connection connection, String email) throws DataException;

	public List<Usuario> findAll(Connection connection) throws DataException;	
	
	public Usuario create(Connection connection, Usuario u) throws DuplicateInstanceException, DataException;

	public void update(Connection connection, Usuario u) throws InstanceNotFoundException, DataException;

	public long delete(Connection connection, Long id) throws InstanceNotFoundException, DataException;

}

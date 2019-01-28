package com.pvv.pulbet.dao;

import java.sql.Connection;
import java.util.List;

import com.pvv.pulbet.exception.DataException;
import com.pvv.pulbet.model.Usuario;

public interface UsuarioDAO {

	public Usuario findById(Connection connection, Long id) throws Exception;
	
	public Usuario findByEmail(Connection connection, String email) throws Exception;

	public List<Usuario> findAll(Connection connection) throws Exception;	
	
	public Usuario create(Connection connection, Usuario u) throws Exception;

	public boolean update(Connection connection, Usuario u) throws Exception;

	public long delete(Connection connection, Long id) throws Exception;

}

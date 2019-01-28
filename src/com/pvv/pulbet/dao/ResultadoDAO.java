package com.pvv.pulbet.dao;

import java.sql.Connection;
import java.util.List;

import com.pvv.pulbet.model.Resultado;
import com.pvv.pulbet.model.Usuario;

public interface ResultadoDAO {

	public Resultado create(Connection connection, Resultado r) throws Exception;
	
	public boolean update(Connection connection, Resultado r) throws Exception;
	
	public void delete(Connection connection, Resultado r) throws Exception;
	
	public Resultado findById(Connection connection, Integer id) throws Exception;
	
	public List<Usuario> findByTipoResultado(Connection connection, Integer id) throws Exception;

}

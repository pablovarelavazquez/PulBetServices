package com.pvv.pulbet.dao;

import java.sql.Connection;
import java.util.List;

import com.pvv.pulbet.model.Resultado;
import com.pvv.pulbet.model.Usuario;

public interface ResultadoDAO {

	public Resultado create(Connection connection, Resultado r) throws Exception;
	
	public Long delete(Connection connection, Long id) throws Exception;
	
	public Resultado findById(Connection connection, Integer id) throws Exception;
	
	public List<Resultado> findByTipoResultado(Connection connection, Integer id) throws Exception;

}

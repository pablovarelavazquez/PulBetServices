package com.pvv.pulbet.dao;

import java.sql.Connection;
import java.util.List;

import com.pvv.pulbet.model.Provincia;

public interface ProvinciaDAO {

	public Provincia findById(Connection connection, int id)
			throws Exception;

	public List<Provincia> findAll(Connection connection)
			throws Exception;

	public List<Provincia> findByNombre(Connection connection, String nome)
			throws Exception;
	
	public List<Provincia> findByPais(Connection connection, Integer id)
			throws Exception;	

}

package com.pvv.pulbet.dao;

import java.sql.Connection;
import java.util.List;

import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.Provincia;

public interface ProvinciaDAO {

	public Provincia findById(Connection connection, Long id)
			throws InstanceNotFoundException, DataException;

	public List<Provincia> findAll(Connection connection)
			throws DataException;

	public List<Provincia> findByNombre(Connection connection, String nome)
			throws DataException;
	
	public List<Provincia> findByPais(Connection connection, Integer id)
			throws DataException;	

}

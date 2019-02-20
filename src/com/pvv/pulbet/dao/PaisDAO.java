package com.pvv.pulbet.dao;

import java.sql.Connection;
import java.util.List;

import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.Pais;

public interface PaisDAO {
	
	
	public Pais findById(Connection connection, int id, String idioma)
		throws InstanceNotFoundException, DataException;

	public List<Pais> findAll(Connection connection, String idioma)
			throws DataException;
	
	public List<Pais> findByNombre(Connection connection, String nome, String idioma)
			throws DataException;
	
}

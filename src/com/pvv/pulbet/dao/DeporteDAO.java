package com.pvv.pulbet.dao;

import java.sql.Connection;
import java.util.List;

import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.DuplicateInstanceException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.Deporte;

public interface DeporteDAO {
	
//	public Deporte create(Connection connection, Deporte d) throws DuplicateInstanceException, DataException;
//
//	public Long delete(Connection connection, Long id) throws InstanceNotFoundException, DataException;
	
	public List<Deporte> findAll(Connection connection, String idioma) throws DataException;
	
	public List<Deporte> findByNombre(Connection connection, String nombre, String idioma) throws DataException;
	
	public Deporte findById(Connection connection, Long id, String idioma) throws InstanceNotFoundException, DataException;
	
}

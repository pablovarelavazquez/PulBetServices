package com.pvv.pulbet.dao;

import java.sql.Connection;
import java.util.List;

import com.pvv.pulbet.model.Competicion;
import com.pvv.pulbet.model.Deporte;

public interface DeporteDAO {
	
	public Deporte create(Connection connection, Deporte d) throws Exception;

	public boolean update(Connection connection, Deporte d) throws Exception;

	public Long delete(Connection connection, Long id) throws Exception;
	
	public List<Deporte> findAll(Connection connection) throws Exception;
	
	public List<Deporte> findByNombre(Connection connection, String nombre) throws Exception;
	
}

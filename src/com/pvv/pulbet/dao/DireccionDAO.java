package com.pvv.pulbet.dao;

import java.sql.Connection;
import java.util.List;

import com.pvv.pulbet.model.Direccion;

public interface DireccionDAO {

	public Direccion create(Connection connection, Direccion d) throws Exception;
	
	public boolean update(Connection connection, Direccion d) throws Exception;
	
	public Long delete(Connection connection, Long id) throws Exception;
	
	public List<Direccion> findByUsuario(Connection connection, Integer id) throws Exception;
	
	public List<Direccion> findAll(Connection connection) throws Exception;
	
	public Direccion findById(Connection connection, Integer id) throws Exception;
}

package com.pvv.pulbet.dao;

import java.sql.Connection;
import java.util.List;

import com.pvv.pulbet.model.Competicion;

public interface CompeticionDAO {
	
	public Competicion create(Connection connection, Competicion c) throws Exception;
	
	public boolean update(Connection connection, Competicion c) throws Exception;
	
	public long delete(Connection connection, Long id) throws Exception;
	
	public Competicion findById(Connection connection, Long id) throws Exception;
	
	public List<Competicion> findByDeporte(Connection connection, Long id) throws Exception;
	
	public List<Competicion> findAll(Connection connection) throws Exception;
	
	public List<Competicion> findByNombre(Connection connection, String nome) throws Exception;
	
		
	
}

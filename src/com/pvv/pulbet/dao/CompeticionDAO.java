package com.pvv.pulbet.dao;

import java.sql.Connection;
import java.util.List;

import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.DuplicateInstanceException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.Competicion;

public interface CompeticionDAO {
	
	public Competicion create(Connection connection, Competicion c) throws DuplicateInstanceException, DataException;
	
	public boolean update(Connection connection, Competicion c) throws InstanceNotFoundException, DataException;
	
	public long delete(Connection connection, Long id) throws InstanceNotFoundException, DataException;
	
	public Competicion findById(Connection connection, Long id) throws InstanceNotFoundException, DataException;
	
	public List<Competicion> findByDeporte(Connection connection, Long id) throws DataException;
	
	public List<Competicion> findAll(Connection connection) throws DataException;
	
	public List<Competicion> findByNombre(Connection connection, String nome) throws DataException;
	
		
	
}

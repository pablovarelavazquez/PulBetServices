package com.pvv.pulbet.dao;

import java.sql.Connection;
import java.util.List;

import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.DuplicateInstanceException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.Direccion;

public interface DireccionDAO {

	public Direccion create(Connection connection, Direccion d) throws DuplicateInstanceException, DataException;
	
	public Long delete(Connection connection, Long id) throws InstanceNotFoundException, DataException;
	
	public Direccion findByUsuario(Connection connection, Long id) throws DataException;
	
	public List<Direccion> findAll(Connection connection) throws DataException;
	
	public Direccion findById(Connection connection, Integer id) throws InstanceNotFoundException, DataException;
}

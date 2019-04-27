package com.pvv.pulbet.dao;

import java.sql.Connection;
import java.util.List;

import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.DuplicateInstanceException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.Apuesta;
import com.pvv.pulbet.service.ApuestaCriteria;
import com.pvv.pulbet.service.Results;

public interface ApuestaDAO {
	
	public Apuesta findById(Connection connection, Long id)
			throws InstanceNotFoundException, DataException;

	public Results<Apuesta> findByUsuario(Connection connection, Long id, int startIndex, int count)
			throws DataException;
	
	public List<Apuesta> findAll(Connection connection) 
			throws DataException;
	
	public Results<Apuesta> findByCriteria(Connection connection, ApuestaCriteria apuesta, Boolean history, int startIndex, int count) 
			throws DataException;
	
	public void updateEstado(Connection connection, Apuesta a) 
			throws InstanceNotFoundException, DataException;

	public Apuesta create(Connection connection, Apuesta a) 
			throws DuplicateInstanceException, DataException;
	
	public long delete(Connection connection, Long id) 
			throws InstanceNotFoundException, DataException;
	
	public void update(Connection connection, Apuesta a) 
			throws InstanceNotFoundException, DataException;
}

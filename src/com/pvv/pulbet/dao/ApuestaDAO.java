package com.pvv.pulbet.dao;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.DuplicateInstanceException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.Apuesta;
import com.pvv.pulbet.service.ApuestaCriteria;

public interface ApuestaDAO {
	
	public Apuesta findById(Connection connection, Long id)
			throws InstanceNotFoundException, DataException;

	public List<Apuesta> findByUsuario(Connection connection, Long id)
			throws DataException;
	
	public List<Apuesta> findAll(Connection connection) 
			throws DataException;
	
	public List<Apuesta> findByCriteria(Connection connection, ApuestaCriteria apuesta, Date hasta) 
			throws DataException;
	
	public Apuesta create(Connection connection, Apuesta a) 
			throws DuplicateInstanceException, DataException;
	
	public long delete(Connection connection, Long id) 
			throws InstanceNotFoundException, DataException;
	
	public void update(Connection connection, Apuesta a) 
			throws InstanceNotFoundException, DataException;

}

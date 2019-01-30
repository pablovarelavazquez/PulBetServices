package com.pvv.pulbet.dao;

import java.sql.Connection;
import java.util.List;

import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.DuplicateInstanceException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.Evento;
import com.pvv.pulbet.service.EventoCriteria;

public interface EventoDAO {
	
	public Evento create(Connection connection, Evento e) throws DuplicateInstanceException, DataException;
	
	public boolean update(Connection connection, Evento e) throws InstanceNotFoundException, DataException;
	
	public Long delete(Connection connection, Long id) throws InstanceNotFoundException, DataException;
	
	public Evento findById(Connection connection, Integer id) throws InstanceNotFoundException, DataException;
	
	public List<Evento> findByCriteria(Connection connection, EventoCriteria evento) throws DataException;

}

package com.pvv.pulbet.dao;

import java.sql.Connection;
import java.util.List;

import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.DuplicateInstanceException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.LineaApuesta;
import com.pvv.pulbet.model.LineaApuestaId;

public interface LineaApuestaDAO {
	
	public LineaApuesta create(Connection connection, LineaApuesta l) throws DuplicateInstanceException, DataException;
	
	public int delete(Connection connection, LineaApuestaId id) throws InstanceNotFoundException, DataException;
	
	public LineaApuesta findById(Connection connection, LineaApuestaId id) throws InstanceNotFoundException, DataException;
	
	public List<LineaApuesta> findAll(Connection connection) throws DataException;
	
	public List<LineaApuesta> findByApuesta(Connection connection, Long id) throws DataException;
	
	public List<LineaApuesta> findByEvento(Connection connection, Long id) throws DataException;

}

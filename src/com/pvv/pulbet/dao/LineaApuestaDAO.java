package com.pvv.pulbet.dao;

import java.sql.Connection;
import java.util.List;

import com.pvv.pulbet.model.LineaApuesta;
import com.pvv.pulbet.model.LineaApuestaId;
import com.pvv.pulbet.model.Usuario;

public interface LineaApuestaDAO {
	
	public LineaApuesta create(Connection connection, LineaApuesta l) throws Exception;
	
	public int delete(Connection connection, LineaApuestaId id) throws Exception;
	
	public LineaApuesta findById(Connection connection, LineaApuestaId id) throws Exception;
	
	public List<LineaApuesta> findAll(Connection connection) throws Exception;
	
	public List<LineaApuesta> findByApuesta(Connection connection, Long id) throws Exception;
	
	public List<LineaApuesta> findByEvento(Connection connection, Long id) throws Exception;

}

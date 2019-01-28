package com.pvv.pulbet.dao;

import java.sql.Connection;
import java.util.List;

import com.pvv.pulbet.model.Evento;
import com.pvv.pulbet.service.EventoCriteria;

public interface EventoDAO {
	
	public Evento create(Connection connection, Evento e) throws Exception;
	
	public boolean update(Connection connection, Evento e) throws Exception;
	
	public Long delete(Connection connection, Long id) throws Exception;
	
	public Evento findById(Connection connection, Integer id) throws Exception;
	
	public List<Evento> findByCriteria(Connection connection, EventoCriteria evento) throws Exception;

	// falta finder mais completo que reciba varios paremetros como os anteriores a a maior a fecha....
}

package com.pvv.pulbet.dao;

import java.sql.Connection;
import java.util.List;

import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.DuplicateInstanceException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.Evento;
import com.pvv.pulbet.model.Usuario;
import com.pvv.pulbet.service.EventoCriteria;

public interface EventoDAO {
	
	public Evento create(Connection connection, Evento e) throws DuplicateInstanceException, DataException;
	
	public Long delete(Connection connection, Long id) throws InstanceNotFoundException, DataException;
	
	public void update(Connection connection, Evento e) throws InstanceNotFoundException, DataException;
	
	public Evento findById(Connection connection, Integer id, String idioma) throws InstanceNotFoundException, DataException;
	
	public List<Evento> findByCriteria(Connection connection, EventoCriteria evento, String idioma) throws DataException;
	
	public List<Long> findResultadoFinal(Connection connection, Long id) throws DataException;

}

package com.pvv.pulbet.service;

import java.sql.Connection;
import java.util.List;

import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.Evento;

public interface EventoService {
	
	public List<Evento> findByCriteria(EventoCriteria evento) throws DataException;
	
	public Evento findById(Integer id) throws InstanceNotFoundException, DataException;

}

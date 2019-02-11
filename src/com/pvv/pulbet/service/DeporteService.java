package com.pvv.pulbet.service;

import java.sql.Connection;
import java.util.List;

import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.Deporte;

public interface DeporteService {
	
	public List<Deporte> findAll() throws DataException;

	public List<Deporte> findByNombre(String nombre) throws DataException;
	
	public Deporte findById(Long id) throws InstanceNotFoundException, DataException;
	
}

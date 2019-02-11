package com.pvv.pulbet.service;

import java.util.List;

import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.Competicion;

public interface CompeticionService {

	public Competicion findById(Long id) throws InstanceNotFoundException, DataException;
	
	public List<Competicion> findByDeporte(Long id) throws DataException;
	
	public List<Competicion> findAll() throws DataException;
	
	public List<Competicion> findByNombre(String nome) throws DataException;
	
}

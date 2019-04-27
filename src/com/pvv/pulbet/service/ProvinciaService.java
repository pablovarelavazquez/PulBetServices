package com.pvv.pulbet.service;

import java.sql.Connection;
import java.util.List;

import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.Provincia;

public interface ProvinciaService {
	
	public Provincia findById(Long id)
			throws InstanceNotFoundException, DataException;

	public List<Provincia> findAll()
			throws DataException;

	public List<Provincia> findByNombre(String nome)
			throws DataException;
	
	public List<Provincia> findByPais(Integer id)
			throws DataException;	

}

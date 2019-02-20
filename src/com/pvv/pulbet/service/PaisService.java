package com.pvv.pulbet.service;

import java.sql.Connection;
import java.util.List;

import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.Pais;

public interface PaisService {
	
	public Pais findById(int id, String idioma)
			throws InstanceNotFoundException, DataException;

		public List<Pais> findAll(String idioma)
				throws DataException;
		
		public List<Pais> findByNombre(String nome, String idioma)
				throws DataException;

}

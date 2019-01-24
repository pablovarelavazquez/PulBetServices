package com.pvv.pulbet.dao;

import java.util.List;

import com.pvv.pulbet.model.Pais;

public interface ProvinciaDAO {

	public Pais findById(int id)
			throws Exception;

	public List<Pais> findAll()
			throws Exception;

	public List<Pais> findByNombre(String nome)
			throws Exception;
	
	public List<Pais> findByPais(String nome)
			throws Exception;	

}

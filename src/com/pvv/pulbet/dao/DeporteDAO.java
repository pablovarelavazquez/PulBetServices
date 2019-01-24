package com.pvv.pulbet.dao;

import java.util.List;

import com.pvv.pulbet.model.Competicion;
import com.pvv.pulbet.model.Deporte;

public interface DeporteDAO {
	
	public Deporte create(Deporte d) throws Exception;

	public boolean update(Deporte d) throws Exception;

	public Long delete(Long id) throws Exception;
	
	public List<Deporte> findAll() throws Exception;
	
	public List<Deporte> findByNombre(String nombre) throws Exception;
	
}

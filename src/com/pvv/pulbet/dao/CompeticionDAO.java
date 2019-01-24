package com.pvv.pulbet.dao;

import java.util.List;

import com.pvv.pulbet.model.Competicion;

public interface CompeticionDAO {
	
	public Competicion create(Competicion c) throws Exception;
	
	public boolean update(Competicion c) throws Exception;
	
	public long delete(Long id) throws Exception;
	
	public Competicion findById(Long id) throws Exception;
	
	public List<Competicion> findByDeporte(Long id) throws Exception;
	
	public List<Competicion> findAll() throws Exception;
	
	public List<Competicion> findByNombre(String nome) throws Exception;
	
		
	
}

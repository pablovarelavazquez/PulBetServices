package com.pvv.pulbet.dao;

import java.util.List;

import com.pvv.pulbet.model.Direccion;

public interface DireccionDAO {

	public Direccion create(Direccion d) throws Exception;
	
	public boolean update(Direccion d) throws Exception;
	
	public Long delete(Long id) throws Exception;
	
	public List<Direccion> findByUsuario(Integer id) throws Exception;
	
	public List<Direccion> findAll() throws Exception;
	
	public Direccion findById(Integer id) throws Exception;
}

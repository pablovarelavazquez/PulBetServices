package com.pvv.pulbet.dao;

import java.util.List;

import com.pvv.pulbet.model.Apuesta;

public interface ApuestaDAO {
	


	public Apuesta findById(Integer id)
			throws Exception;

	public List<Apuesta> findByUsuario(Integer id)
			throws Exception;
	
	public List<Apuesta> findAll() throws Exception;
	
	//falta algun finder para buscar apostas deperminadas, inda non determinadas e eso...

	public Apuesta create(Apuesta a) 
			throws Exception;

}

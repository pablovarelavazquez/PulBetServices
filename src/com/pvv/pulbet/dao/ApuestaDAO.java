package com.pvv.pulbet.dao;

import java.sql.Connection;
import java.util.List;

import com.pvv.pulbet.model.Apuesta;

public interface ApuestaDAO {
	
	public Apuesta findById(Connection connection, Integer id)
			throws Exception;

	public List<Apuesta> findByUsuario(Connection connection, Integer id)
			throws Exception;
	
	public List<Apuesta> findAll(Connection connection) throws Exception;
	
	//falta algun finder para buscar apostas deperminadas, inda non determinadas e eso...

	public Apuesta create(Connection connection, Apuesta a) 
			throws Exception;
	
	public long delete(Connection connection, Long id) 
			throws Exception;
	
	public void update(Connection connection, Apuesta a) throws Exception;

}

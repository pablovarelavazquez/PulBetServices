package com.pvv.pulbet.dao;

import java.sql.Connection;
import java.util.List;

import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.DuplicateInstanceException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.Resultado;

public interface ResultadoDAO {

	public Resultado create(Connection connection, Resultado r) throws DuplicateInstanceException, DataException;
	
	public Long delete(Connection connection, Long id) throws InstanceNotFoundException, DataException;
	
	public Resultado findById(Connection connection, Integer id) throws InstanceNotFoundException, DataException;
	
	public List<Resultado> findByTipoResultado(Connection connection, Integer id) throws DataException;

}

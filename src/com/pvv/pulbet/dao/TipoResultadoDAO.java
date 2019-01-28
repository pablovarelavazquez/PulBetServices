package com.pvv.pulbet.dao;

import java.sql.Connection;
import java.util.List;

import com.pvv.pulbet.model.TipoResultado;
import com.pvv.pulbet.model.Usuario;

public interface TipoResultadoDAO {
	
	public TipoResultado create(Connection connection, TipoResultado t) throws Exception;
	
	public long delete(Connection connection, Integer id) throws Exception;
	
	public TipoResultado findById(Connection connection, Integer id) throws Exception;
	
	public List<TipoResultado> findByEvento(Connection connection, Long id) throws Exception;

}

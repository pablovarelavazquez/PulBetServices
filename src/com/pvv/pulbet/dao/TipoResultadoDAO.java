package com.pvv.pulbet.dao;

import java.sql.Connection;
import java.util.List;

import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.DuplicateInstanceException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.TipoResultado;

public interface TipoResultadoDAO {
	
//  Coa internalizacion da base de datos cambia.
//	public TipoResultado create(Connection connection, TipoResultado t) throws DuplicateInstanceException, DataException;
//	
//	public long delete(Connection connection, Integer id) throws InstanceNotFoundException, DataException;
	
	public TipoResultado findById(Connection connection, Integer id, String idioma) throws InstanceNotFoundException, DataException;
	
	public List<TipoResultado> findByEvento(Connection connection, Long id, String idioma) throws DataException;
	
	public List<TipoResultado> findByDeporte(Connection connection, Long id, String idioma) throws DataException;

}

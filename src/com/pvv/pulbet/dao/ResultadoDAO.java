package com.pvv.pulbet.dao;

import java.sql.Connection;
import java.util.List;

import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.DuplicateInstanceException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.Resultado;
import com.pvv.pulbet.model.Usuario;

public interface ResultadoDAO {

//  Coa internalizacion da base de datos cambia.
//	public Resultado create(Connection connection, Resultado r) throws DuplicateInstanceException, DataException;
//	
//	public Long delete(Connection connection, Long id) throws InstanceNotFoundException, DataException;
	
	public Resultado findById(Connection connection, Integer id, String idioma) throws InstanceNotFoundException, DataException;
	
	public List<Resultado> findByTipoResultado(Connection connection, Integer id, String idioma) throws DataException;
	
	public List<Resultado> findAll(Connection connection, String idioma)	throws DataException;
	
	public Resultado findCuota(Connection connection, Long idEvento, Long idResultado, String idioma) throws DataException;
	
}

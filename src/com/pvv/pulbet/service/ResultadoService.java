package com.pvv.pulbet.service;

import java.util.List;

import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.DuplicateInstanceException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.Resultado;

public interface ResultadoService {

//	public Resultado create( Resultado r) throws DuplicateInstanceException, DataException;
//	
//	public Long delete(Long id) throws InstanceNotFoundException, DataException;
	
	public Resultado findById(Integer id, String idioma) throws InstanceNotFoundException, DataException;
	
	public List<Resultado> findByTipoResultado(Integer id, String idioma) throws DataException;
	
	public List<Resultado> findAll( String idioma ) throws DataException;
	
	public Resultado findCuota(Long idEvento, Long idResultado, String idioma) throws DataException;
	
}

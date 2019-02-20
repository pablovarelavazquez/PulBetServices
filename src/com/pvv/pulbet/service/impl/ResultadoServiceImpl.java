package com.pvv.pulbet.service.impl;

import java.util.List;

import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.DuplicateInstanceException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.Resultado;
import com.pvv.pulbet.service.ResultadoService;

public class ResultadoServiceImpl implements ResultadoService{

//	@Override
//	public Resultado create(Resultado r) throws DuplicateInstanceException, DataException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Long delete(Long id) throws InstanceNotFoundException, DataException {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public Resultado findById(Integer id, String idioma) throws InstanceNotFoundException, DataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Resultado> findByTipoResultado(Integer id, String idioma) throws DataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Resultado> findAll(String idioma) throws DataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Resultado> findCuotas(Long idEvento) throws DataException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}

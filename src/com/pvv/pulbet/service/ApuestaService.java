package com.pvv.pulbet.service;

import java.util.List;

import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.model.Apuesta;

public interface ApuestaService {
	
	public List<Apuesta> findByUsuario(Long id) throws DataException;

}

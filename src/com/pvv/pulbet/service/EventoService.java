package com.pvv.pulbet.service;

import java.util.List;

import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.Evento;

public interface EventoService {
	
	public List<Evento> findByCriteria(EventoCriteria evento, String idioma) throws DataException;
	
	public Evento findById(Integer id, String idioma) throws InstanceNotFoundException, DataException;
	
	public List<Long> findResultadoFinal(Long id) throws DataException;

}

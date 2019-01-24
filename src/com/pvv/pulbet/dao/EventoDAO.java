package com.pvv.pulbet.dao;

import java.util.List;

import com.pvv.pulbet.model.Evento;

public interface EventoDAO {
	
	public Evento create(Evento e) throws Exception;
	
	public boolean update(Evento e) throws Exception;
	
	public Long delete(Long id) throws Exception;
	
	public Evento findById(Integer id) throws Exception;
	
	public List<Evento> findAll() throws Exception;
	
	public List<Evento> findByParticipante(Integer id) throws Exception;
	
	public List<Evento> findByCompeticion(Integer id) throws Exception;
	
	public List<Evento> findByDeporte(Integer id) throws Exception;

	// falta finder mais completo que reciba varios paremetros como os anteriores a a maior a fecha....
}

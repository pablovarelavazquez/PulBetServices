package com.pvv.pulbet.dao;

import java.util.List;

import com.pvv.pulbet.model.LineaApuesta;
import com.pvv.pulbet.model.LineaApuestaId;
import com.pvv.pulbet.model.Usuario;

public interface LineaApuestaDAO {
	
	public LineaApuesta create(LineaApuesta l) throws Exception;
	
	public int delete(LineaApuestaId id) throws Exception;
	
	public LineaApuesta findById(LineaApuestaId id) throws Exception;
	
	public List<LineaApuesta> findAll() throws Exception;
	
	public List<LineaApuesta> findByApuesta(Long id) throws Exception;
	
	public List<LineaApuesta> findByEvento(Long id) throws Exception;

}

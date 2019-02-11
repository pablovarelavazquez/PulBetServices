package com.pvv.pulbet.service;

import java.util.Date;
import java.util.List;

import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.DuplicateInstanceException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.Apuesta;

public interface ApuestaService {
	
	public long delete(Long id) throws InstanceNotFoundException, DataException;
	
	public Apuesta create(Apuesta a) throws DuplicateInstanceException, DataException;
	
	public Apuesta findById(Long id) throws InstanceNotFoundException, DataException;
	
	public List<Apuesta> findByUsuario(Long id) throws DataException;
	
	public List<Apuesta> findHistorial(ApuestaCriteria apuesta, Date hasta) throws DataException;
	
	public List<Apuesta> findOpenBets(ApuestaCriteria apuesta, Date hasta) throws DataException;
	
	public Apuesta comprobarApuesta( Apuesta apuesta) throws DataException;

}

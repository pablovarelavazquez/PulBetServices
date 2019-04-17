package com.pvv.pulbet.service;

import java.sql.Connection;
import java.util.List;

import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.DuplicateInstanceException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.Apuesta;

public interface ApuestaService {
	
	public long delete(Long id) throws InstanceNotFoundException, DataException;
	
	public Apuesta create(Apuesta a) throws DuplicateInstanceException, DataException;
	
	public Apuesta findById(Long id) throws InstanceNotFoundException, DataException;
	
	public Results<Apuesta> findByUsuario(Long id,int startIndex, int count) throws DataException;
	
	//public Results<Apuesta> findAll(int startIndex, int count) throws DataException;
		
	public Results<Apuesta> findHistorial(ApuestaCriteria apuesta,int startIndex, int count) throws DataException;
	
	public Results<Apuesta> findOpenBets(ApuestaCriteria apuesta,int startIndex, int count) throws DataException;
	
	public Apuesta comprobarApuesta(Apuesta apuesta) throws DataException;

}

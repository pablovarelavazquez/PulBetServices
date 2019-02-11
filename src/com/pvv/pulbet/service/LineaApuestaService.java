package com.pvv.pulbet.service;

import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.DuplicateInstanceException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.LineaApuesta;
import com.pvv.pulbet.model.LineaApuestaId;

public interface LineaApuestaService {
	
	public LineaApuesta comprobarLinea( LineaApuesta lineaApuesta) throws DataException;
	
	public LineaApuesta update(LineaApuesta lineaApuesta) throws InstanceNotFoundException, DataException;
	
	public LineaApuesta create(LineaApuesta lineaApuesta) throws DuplicateInstanceException, DataException; 
	
	public int delete(LineaApuestaId id) throws InstanceNotFoundException, DataException;
	
	public LineaApuesta findById(LineaApuestaId id) throws InstanceNotFoundException, DataException;

}

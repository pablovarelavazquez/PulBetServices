package com.pvv.pulbet.service;

import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.LineaApuesta;
import com.pvv.pulbet.model.LineaApuestaId;

public interface LineaApuestaService {
	
	public LineaApuesta comprobarLinea( LineaApuesta lineaApuesta) throws DataException;
	
	public LineaApuesta update(LineaApuesta lineaApuesta) throws InstanceNotFoundException, DataException;
	
	public LineaApuesta findById(LineaApuestaId id) throws InstanceNotFoundException, DataException;

}

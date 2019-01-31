package com.pvv.pulbet.service;

import com.pvv.pulbet.exceptions.DataException;

public interface BancoService {
	
	public void ingresar(Long idUsuario, Double cantidad) throws DataException;

	public void retirar(Long idUsuario, Double cantidad) throws DataException;
}

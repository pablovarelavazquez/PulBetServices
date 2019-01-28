package com.pvv.pulbet.service;

import java.sql.Connection;

public interface BancoService {
	
	public void ingresar(Long idUsuario, Double cantidad) throws Exception;

	public void retirar(Long idUsuario, Double cantidad) throws Exception;
}

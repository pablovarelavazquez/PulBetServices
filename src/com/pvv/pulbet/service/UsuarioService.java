package com.pvv.pulbet.service;

import com.pvv.pulbet.model.Usuario;

public interface UsuarioService {

	public Usuario findById(Integer id) throws Exception;
	
	public Usuario login(String email, String password) throws Exception;
	
	public Usuario create(Usuario u) throws Exception;
	
	public void update(Usuario u) throws Exception;
	
	public boolean delete(Usuario u) throws Exception;
}

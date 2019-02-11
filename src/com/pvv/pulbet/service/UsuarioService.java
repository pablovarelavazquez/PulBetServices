package com.pvv.pulbet.service;

import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.DuplicateInstanceException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.exceptions.MailException;
import com.pvv.pulbet.model.Direccion;
import com.pvv.pulbet.model.Usuario;

public interface UsuarioService {

	public Usuario findById(Long id) throws InstanceNotFoundException, DataException;
	
	public Usuario login(String email, String password) throws DataException;
	
	public void editDireccion(Direccion direccion, Usuario u) throws InstanceNotFoundException, DataException;
	
	public Usuario create(Usuario u) throws DuplicateInstanceException, DataException, MailException;
	
	public void update(Usuario u) throws InstanceNotFoundException, DataException;
	
	public long delete(Long id)  throws InstanceNotFoundException, DataException;
}

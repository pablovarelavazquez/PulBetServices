package com.pvv.pulbet.dao;

import java.util.List;

import com.pvv.pulbet.model.Resultado;
import com.pvv.pulbet.model.Usuario;

public interface ResultadoDAO {

	public Resultado create(Resultado r) throws Exception;
	
	public boolean update(Resultado r) throws Exception;
	
	public void delete(Resultado r) throws Exception;
	
	public Resultado findById(Integer id) throws Exception;
	
	public List<Usuario> findByTipoResultado(Integer id) throws Exception;

}

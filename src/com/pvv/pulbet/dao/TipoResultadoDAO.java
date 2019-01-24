package com.pvv.pulbet.dao;

import java.util.List;

import com.pvv.pulbet.model.TipoResultado;
import com.pvv.pulbet.model.Usuario;

public interface TipoResultadoDAO {
	
	public TipoResultado create(TipoResultado t) throws Exception;
	
	public boolean update(TipoResultado t) throws Exception;
	
	public void delete(TipoResultado t) throws Exception;
	
	public TipoResultado findById(Integer id) throws Exception;
	
	public List<Usuario> findByEvento(Integer id) throws Exception;
	


}

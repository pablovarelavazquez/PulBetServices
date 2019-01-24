package com.pvv.pulbet.dao;

import java.util.List;

import com.pvv.pulbet.model.Participante;
import com.pvv.pulbet.model.Usuario;

public interface ParticipanteDAO {

	public Participante create(Participante p) throws Exception;
	
	public boolean update(Participante p) throws Exception;
	
	public void delete(Participante p) throws Exception;
	
	public Usuario findById(Integer id) throws Exception;
	
	public List<Usuario> findByCompeticion(Integer id) throws Exception;
	
	public List<Usuario> findByDeporte(Integer id) throws Exception;
}

package com.pvv.pulbet.dao;

import java.sql.Connection;
import java.util.List;

import com.pvv.pulbet.model.Participante;
import com.pvv.pulbet.model.Usuario;

public interface ParticipanteDAO {

	public Participante create(Connection connection, Participante p) throws Exception;
	
	public boolean update(Connection connection, Participante p) throws Exception;
	
	public void delete(Connection connection, Participante p) throws Exception;
	
	public Usuario findById(Connection connection, Integer id) throws Exception;
	
	public List<Usuario> findByCompeticion(Connection connection, Integer id) throws Exception;
	
	public List<Usuario> findByDeporte(Connection connection, Integer id) throws Exception;
}

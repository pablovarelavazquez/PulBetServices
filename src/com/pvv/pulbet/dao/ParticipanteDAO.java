package com.pvv.pulbet.dao;

import java.sql.Connection;
import java.util.List;

import com.pvv.pulbet.model.Participante;
import com.pvv.pulbet.model.Usuario;

public interface ParticipanteDAO {


	public Participante findById(Connection connection, Long id) throws Exception;
	
	public List<Participante> findByCompeticion(Connection connection, Long id) throws Exception;
	
	public List<Participante> findByDeporte(Connection connection, Long id) throws Exception;
}

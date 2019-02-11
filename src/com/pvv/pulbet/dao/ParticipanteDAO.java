package com.pvv.pulbet.dao;

import java.sql.Connection;
import java.util.List;

import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.Participante;

public interface ParticipanteDAO {


	public Participante findById(Connection connection, Long id) throws InstanceNotFoundException, DataException;
	
	public List<Participante> findByCompeticion(Connection connection, Long id) throws DataException;
	
	public List<Participante> findByDeporte(Connection connection, Long id) throws DataException;
	
	public List<Participante> findByEvento(Connection connection, Long id) throws DataException;
}

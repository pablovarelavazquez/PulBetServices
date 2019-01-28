package com.pvv.pulbet.dao.impl;

import java.sql.Connection;
import java.util.List;

import com.pvv.pulbet.dao.ParticipanteDAO;
import com.pvv.pulbet.model.Participante;
import com.pvv.pulbet.model.Usuario;

public class ParticipanteDAOImpl implements ParticipanteDAO{

	@Override
	public Participante create(Connection connection, Participante p) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Connection connection, Participante p) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void delete(Connection connection, Participante p) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Usuario findById(Connection connection, Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> findByCompeticion(Connection connection, Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> findByDeporte(Connection connection, Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}

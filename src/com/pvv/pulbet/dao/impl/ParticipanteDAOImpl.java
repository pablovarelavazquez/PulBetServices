package com.pvv.pulbet.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pvv.pulbet.dao.ParticipanteDAO;
import com.pvv.pulbet.dao.util.ConnectionManager;
import com.pvv.pulbet.dao.util.JDBCUtils;
import com.pvv.pulbet.exception.DataException;
import com.pvv.pulbet.model.Participante;
import com.pvv.pulbet.model.Provincia;
import com.pvv.pulbet.model.Usuario;

public class ParticipanteDAOImpl implements ParticipanteDAO{

	@Override
	public Participante findById(Connection connection, Long id) throws Exception {
		Participante p = null;

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionManager.getConnection();

				
			String sql;
			sql =  "SELECT ID_PARTICIPANTE, NOMBRE, ID_DEPORTE "
					+"FROM PARTICIPANTE "
					+"WHERE ID_PARTICIPANTE = ? ";

			// Preparar a query
			System.out.println("Creating statement...");
			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Establece os parámetros
			int i = 1;
			preparedStatement.setLong(i++, id);


			resultSet = preparedStatement.executeQuery();			
			//STEP 5: Extract data from result set			

			if (resultSet.next()) {
				p =  loadNext(connection, resultSet);			
				//System.out.println("Cargado "+u);
			} else {
				throw new Exception("Non se atopou Participante con id = "+id);
			}
			if (resultSet.next()) {
				throw new Exception("Participante con id = "+id+" duplicado");
			}

		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	

		return p;
	}

	@Override
	public List<Participante> findByCompeticion(Connection connection, Long id) throws Exception {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionManager.getConnection();

				
			String sql;
			sql =  "SELECT ID_PARTICIPANTE, NOMBRE, ID_DEPORTE "
					+"FROM PARTICIPANTE P INNER JOIN PARTICIPANTE_COMPETICION PC ON PC.ID_PARTICIPANTE = P.ID_PARTICIPANTE "
					+"WHERE ID_COMPETICION = ? ";
			
			

			// Preparar a query
			System.out.println("Creating statement...");
			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Establece os parámetros
			int i = 1;
			preparedStatement.setLong(i++, id);


			resultSet = preparedStatement.executeQuery();			
			//STEP 5: Extract data from result set			

			List<Participante> results = new ArrayList<Participante>();                        
			Participante p = null;

			while(resultSet.next()) {
				p = loadNext(connection, resultSet);
				results.add(p);               	
			}
			
			return results;

		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	

		
	}

	@Override
	public List<Participante> findByDeporte(Connection connection, Long id) throws Exception {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionManager.getConnection();

				
			String sql;
			sql =  "SELECT ID_PARTICIPANTE, NOMBRE, ID_DEPORTE "
					+"FROM PARTICIPANTE "
					+"WHERE ID_DEPORTE = ? ";
			
			

			// Preparar a query
			System.out.println("Creating statement...");
			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Establece os parámetros
			int i = 1;
			preparedStatement.setLong(i++, id);


			resultSet = preparedStatement.executeQuery();			
			//STEP 5: Extract data from result set			

			List<Participante> results = new ArrayList<Participante>();                        
			Participante p = null;

			while(resultSet.next()) {
				p = loadNext(connection, resultSet);
				results.add(p);               	
			}
			
			return results;

		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		} 
	}
	
	private Participante loadNext(Connection connection, ResultSet resultSet) throws Exception{


		Participante p = new Participante();
		int i = 1;
		Long id = resultSet.getLong(i++);
		String nome = resultSet.getString(i++);
		Long idDeporte = resultSet.getLong(i++);
		
		p.setIdParticipante(id);
		p.setNome(nome);
		p.setIdDeporte(idDeporte);		
		return p;

	}

}

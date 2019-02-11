package com.pvv.pulbet.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pvv.pulbet.dao.ParticipanteDAO;
import com.pvv.pulbet.dao.util.JDBCUtils;
import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.Participante;

public class ParticipanteDAOImpl implements ParticipanteDAO{

	private static Logger logger = LogManager.getLogger(ParticipanteDAOImpl.class);
	
	@Override
	public Participante findById(Connection connection, Long id) throws InstanceNotFoundException, DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Id = {}", id);
		}
		
		Participante p = null;

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
				
			String sql;
			sql =  "SELECT ID_PARTICIPANTE, NOMBRE, ID_DEPORTE "
					+"FROM PARTICIPANTE "
					+"WHERE ID_PARTICIPANTE = ? ";

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
				throw new InstanceNotFoundException("Non se atopou Participante con id = "+id, Participante.class.getName());
			}


		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	

		return p;
	}

	@Override
	public List<Participante> findByCompeticion(Connection connection, Long id) throws DataException {

		if(logger.isDebugEnabled()) {
			logger.debug("Id = {}", id);
		}
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
				
			String sql;
			sql =  "SELECT ID_PARTICIPANTE, NOMBRE, ID_DEPORTE "
					+"FROM PARTICIPANTE P INNER JOIN PARTICIPANTE_COMPETICION PC ON PC.ID_PARTICIPANTE = P.ID_PARTICIPANTE "
					+"WHERE ID_COMPETICION = ? ";
			
			

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
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	

		
	}

	@Override
	public List<Participante> findByDeporte(Connection connection, Long id) throws DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Id = {}", id);
		}
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
				
			String sql;
			sql =  "SELECT ID_PARTICIPANTE, NOMBRE, ID_DEPORTE "
					+"FROM PARTICIPANTE "
					+"WHERE ID_DEPORTE = ? ";
			
			

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
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		} 
	}
	
	@Override
	public List<Participante> findByEvento(Connection connection, Long id) throws DataException {
		if(logger.isDebugEnabled()) {
			logger.debug("Id = {}", id);
		}
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
				
			String sql;
			sql =  "SELECT P.ID_PARTICIPANTE, P.NOMBRE, P.ID_DEPORTE "
					+"FROM EVENTO E INNER JOIN RESULTADO_PARTICIPANTE_EVENTO RP ON E.ID_EVENTO = RP.ID_EVENTO "
					+"INNER JOIN PARTICIPANTE P ON P.ID_PARTICIPANTE = RP.ID_PARTICIPANTE "
					+"WHERE E.ID_EVENTO = ? ";
			
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
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		} 
	}
	
	private Participante loadNext(Connection connection, ResultSet resultSet) throws SQLException{


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

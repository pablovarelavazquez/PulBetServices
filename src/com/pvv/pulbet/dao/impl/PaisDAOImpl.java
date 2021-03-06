package com.pvv.pulbet.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pvv.pulbet.dao.PaisDAO;
import com.pvv.pulbet.dao.util.JDBCUtils;
import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.Pais;

public class PaisDAOImpl implements PaisDAO{

	private static Logger logger = LogManager.getLogger(PaisDAOImpl.class);	
	
	@Override
	public Pais findById(Connection connection, int id, String idioma) throws InstanceNotFoundException, DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Id = {}", id);
		}
		
		Pais p = null;

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
				
			String sql;
			sql =  "SELECT P.ID_PAIS, PI.NOMBRE "
					+"FROM PAIS P INNER JOIN PAIS_IDIOMA PI ON P.ID_PAIS = PI.ID_PAIS "
					+"WHERE P.ID_PAIS = ? AND PI.COD_IDIOMA = ? ";

			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Establece os parámetros
			int i = 1;
			preparedStatement.setLong(i++, id);
			preparedStatement.setString(i++, idioma);


			resultSet = preparedStatement.executeQuery();			
			//STEP 5: Extract data from result set			

			if (resultSet.next()) {
				p =  loadNext(connection,resultSet);			
				//System.out.println("Cargado "+u);
			} else {
				throw new InstanceNotFoundException("Non se atopou PAIS con id = "+id, Pais.class.getName());
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
	public List<Pais> findAll(Connection connection, String idioma) throws DataException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT P.ID_PAIS, PI.NOMBRE "
					+"FROM PAIS P INNER JOIN PAIS_IDIOMA PI ON P.ID_PAIS = PI.ID_PAIS "
					+"WHERE PI.COD_IDIOMA = ? ";

			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			preparedStatement.setString(i++, idioma);
			
			resultSet = preparedStatement.executeQuery();			
			//STEP 5: Extract data from result set			
			
			List<Pais> results = new ArrayList<Pais>();                        
			Pais p= null;


			while(resultSet.next()) {
				p = loadNext(connection,resultSet);
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
	public List<Pais> findByNombre(Connection connection, String nome, String idioma) throws DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Nombre= {}", nome);
		}
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{

			String sql;
			sql =   "SELECT P.ID_PAIS, PI.NOMBRE "
					+"FROM PAIS P INNER JOIN PAIS_IDIOMA PI ON P.ID_PAIS = PI.ID_PAIS "
					+"WHERE PI.NOMBRE = ? AND PI.COD_IDIOMA = ? ";

			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Establece os parámetros
			int i = 1;
			preparedStatement.setString(i++, "%"+nome.toUpperCase()+"%");
			preparedStatement.setString(i++, idioma);


			resultSet = preparedStatement.executeQuery();			
			//STEP 5: Extract data from result set

			List<Pais> results = new ArrayList<Pais>();                        
			Pais p = null;


			while(resultSet.next()) {
				p = loadNext(connection,resultSet);
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


	private Pais loadNext(Connection connection,ResultSet resultSet) throws SQLException{


		Pais p = new Pais();
		int i = 1;
		Integer id = resultSet.getInt(i++);
		String nome = resultSet.getString(i++);
		
		p.setIdPais(id);
		p.setNome(nome);
		
		return p;

	}


}

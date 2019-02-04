package com.pvv.pulbet.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pvv.pulbet.dao.ProvinciaDAO;
import com.pvv.pulbet.dao.util.ConnectionManager;
import com.pvv.pulbet.dao.util.JDBCUtils;
import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.Provincia;

public class ProvinciaDAOImpl implements ProvinciaDAO{

	@Override
	public Provincia findById(Connection connection, int id) throws InstanceNotFoundException, DataException {
		Provincia p = null;

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
				
			String sql;
			sql =  "SELECT ID_PROVINCIA, NOMBRE, ID_PAIS "
					+"FROM PROVINCIA "
					+"WHERE ID_PROVINCIA = ? ";

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
				throw new InstanceNotFoundException("Non se atopou Provincia con id = "+id, Provincia.class.getName());
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
	public List<Provincia> findAll(Connection connection) throws DataException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT ID_PROVINCIA, NOMBRE, ID_PAIS "
					+"FROM PROVINCIA ";

			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			resultSet = preparedStatement.executeQuery();			
			//STEP 5: Extract data from result set			

			List<Provincia> results = new ArrayList<Provincia>();                        
			Provincia p= null;


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
	public List<Provincia> findByNombre(Connection connection, String nome) throws DataException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{

			String sql;
			sql =    "SELECT ID_PROVINCIA,NOMBRE,ID_PAIS " 
					+" FROM ID_PROVINCIA "
					+" WHERE "
					+"	UPPER(NOMBRE) LIKE ?";

			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Establece os parámetros
			int i = 1;
			preparedStatement.setString(i++, "%"+nome.toUpperCase()+"%");


			resultSet = preparedStatement.executeQuery();			
			//STEP 5: Extract data from result set

			List<Provincia> results = new ArrayList<Provincia>();                        
			Provincia p = null;


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
	public List<Provincia> findByPais(Connection connection, Integer id) throws DataException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{

			String sql;
			sql =    "SELECT ID_PROVINCIA, NOMBRE, ID_PAIS " 
					+" FROM PROVINCIA "
					+" WHERE "
					+"	ID_PAIS = ?";


			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Establece os parámetros
			int i = 1;
			preparedStatement.setInt(i++, id);

			resultSet = preparedStatement.executeQuery();			
			//STEP 5: Extract data from result set			

			List<Provincia> results = new ArrayList<Provincia>();                        
			Provincia p = null;


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
	
	private Provincia loadNext(Connection connection, ResultSet resultSet) throws SQLException{


		Provincia p = new Provincia();
		int i = 1;
		Integer id = resultSet.getInt(i++);
		String nome = resultSet.getString(i++);
		Integer idPais = resultSet.getInt(i++);
		
		p.setIdProvincia(id);
		p.setNome(nome);
		p.setIdPais(idPais);
		
		return p;

	}

}

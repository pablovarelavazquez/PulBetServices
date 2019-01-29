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
import com.pvv.pulbet.exception.DataException;
import com.pvv.pulbet.model.Pais;
import com.pvv.pulbet.model.Provincia;
import com.pvv.pulbet.model.Usuario;

public class ProvinciaDAOImpl implements ProvinciaDAO{

	@Override
	public Provincia findById(Connection connection, int id) throws Exception {
		Provincia p = null;

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionManager.getConnection();

				
			String sql;
			sql =  "SELECT ID_PROVINCIA, NOMBRE, ID_PAIS "
					+"FROM PROVINCIA "
					+"WHERE ID_PROVINCIA = ? ";

			// Preparar a query
			System.out.println("Creating statement...");
			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Establece os par�metros
			int i = 1;
			preparedStatement.setLong(i++, id);


			resultSet = preparedStatement.executeQuery();			
			//STEP 5: Extract data from result set			

			if (resultSet.next()) {
				p =  loadNext(connection, resultSet);			
				//System.out.println("Cargado "+u);
			} else {
				throw new Exception("Non se atopou Provincia con id = "+id);
			}
			if (resultSet.next()) {
				throw new Exception("Provincia con id = "+id+" duplicado");
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
	public List<Provincia> findAll(Connection connection) throws Exception {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionManager.getConnection();

			String sql;
			sql =  "SELECT ID_PROVINCIA, NOMBRE, ID_PAIS "
					+"FROM PROVINCIA ";

			// Preparar a query
			System.out.println("Creating statement...");
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
	public List<Provincia> findByNombre(Connection connection, String nome) throws Exception {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{

			connection = ConnectionManager.getConnection();

			String sql;
			sql =    "SELECT ID_PROVINCIA,NOMBRE,ID_PAIS " 
					+" FROM ID_PROVINCIA "
					+" WHERE "
					+"	UPPER(NOMBRE) LIKE ?";

			// Preparar a query
			System.out.println("Creating statement...");
			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Establece os par�metros
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
	public List<Provincia> findByPais(Connection connection, Integer id) throws Exception {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{

			connection = ConnectionManager.getConnection();

			String sql;
			sql =    "SELECT ID_PROVINCIA, NOMBRE, ID_PAIS " 
					+" FROM PROVINCIA "
					+" WHERE "
					+"	ID_PAIS = ?";


			// Preparar a query
			System.out.println("Creating statement...");
			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Establece os par�metros
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
	
	private Provincia loadNext(Connection connection, ResultSet resultSet) throws Exception{


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

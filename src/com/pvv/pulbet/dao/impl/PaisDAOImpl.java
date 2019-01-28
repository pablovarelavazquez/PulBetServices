package com.pvv.pulbet.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pvv.pulbet.dao.PaisDAO;
import com.pvv.pulbet.dao.util.ConnectionManager;
import com.pvv.pulbet.dao.util.JDBCUtils;
import com.pvv.pulbet.exception.DataException;
import com.pvv.pulbet.model.Pais;
import com.pvv.pulbet.model.Usuario;

public class PaisDAOImpl implements PaisDAO{

	@Override
	public Pais findById(Connection connection, int id) throws Exception {
		Pais p = null;

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionManager.getConnection();

				
			String sql;
			sql =  "SELECT ID_PAIS, NOMBRE "
					+"FROM PAIS "
					+"WHERE ID_PAIS = ? ";

			// Preparar a query
			System.out.println("Creating statement...");
			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Establece os par�metros
			int i = 1;
			preparedStatement.setLong(i++, id);


			resultSet = preparedStatement.executeQuery();			
			//STEP 5: Extract data from result set			

			if (resultSet.next()) {
				p =  loadNext(resultSet);			
				//System.out.println("Cargado "+u);
			} else {
				throw new Exception("Non se atopou PAIS con id = "+id);
			}
			if (resultSet.next()) {
				throw new Exception("Empleado con id = "+id+" duplicado");
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
	public List<Pais> findAll(Connection connection) throws Exception {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionManager.getConnection();

			String sql;
			sql =  "SELECT ID_PAIS,NOMBRE "
					+"FROM PAIS ";

			// Preparar a query
			System.out.println("Creating statement...");
			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			resultSet = preparedStatement.executeQuery();			
			//STEP 5: Extract data from result set			

			List<Pais> results = new ArrayList<Pais>();                        
			Pais p= null;


			while(resultSet.next()) {
				p = loadNext(resultSet);
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
	public List<Pais> findByNombre(Connection connection, String nome) throws Exception {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{

			connection = ConnectionManager.getConnection();

			String sql;
			sql =    "SELECT ID_PAIS,NOMBRE " 
					+" FROM PAIS "
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

			List<Pais> results = new ArrayList<Pais>();                        
			Pais p = null;


			while(resultSet.next()) {
				p = loadNext(resultSet);
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


	private Pais loadNext(ResultSet resultSet) throws Exception{


		Pais p = new Pais();
		int i = 1;
		Integer id = resultSet.getInt(i++);
		String nome = resultSet.getString(i++);
		
		p.setIdPais(id);
		p.setNome(nome);
		
		return p;

	}


}

package com.pvv.pulbet.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pvv.pulbet.dao.DeporteDAO;
import com.pvv.pulbet.dao.util.ConnectionManager;
import com.pvv.pulbet.dao.util.JDBCUtils;
import com.pvv.pulbet.exception.DataException;
import com.pvv.pulbet.model.Competicion;
import com.pvv.pulbet.model.Deporte;
import com.pvv.pulbet.model.Usuario;

public class DeporteDAOImpl implements DeporteDAO{
	
	@Override
	public Deporte create(Deporte d) throws Exception {
		Connection connection = null; 
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {          

			connection = ConnectionManager.getConnection();


			String queryString = "INSERT INTO DEPORTE(NOMBRE) "
					+ "VALUES (?)";

			preparedStatement = connection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);

			int i = 1;     			
			preparedStatement.setString(i++, d.getNome());

			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) {
				throw new SQLException("Can not add row to table 'DEPORTE'");
			}

			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				Long id = resultSet.getLong(1);
				d.setIdDeporte(id);				
			} else {
				throw new DataException("Unable to fetch autogenerated primary key");
			}


			//...
			return d;					

		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);			
			JDBCUtils.closeConnection(connection);
		}
	}

	@Override
	public boolean update(Deporte d) throws Exception {
		Connection connection = null; 
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {          

			connection = ConnectionManager.getConnection();


			String queryString = "UPDATE DEPORTE "
					+ "SET NOMBRE = ? "
					+ "WHERE ID_DEPORTE=?";

			preparedStatement = connection.prepareStatement(queryString);

			int i = 1;     			
			preparedStatement.setString(i++, d.getNome());
			preparedStatement.setLong(i++, d.getIdDeporte());


			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) 
			{

				throw new SQLException("Can not uppdate row to table 'DEPORTE'");

			} 
			else { return true;}

			//...


		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);			
			JDBCUtils.closeConnection(connection);
		}

	}

	@Override
	public Long delete(Long id) throws Exception {
		Connection connection = null; 
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionManager.getConnection();

			String queryString =	
					"DELETE FROM DEPORTE " 
					+ "WHERE ID_DEPORTE = ? ";


			preparedStatement = connection.prepareStatement(queryString);

			int i = 1;
			preparedStatement.setLong(i++, id);

			long removedRows = preparedStatement.executeUpdate();


			return removedRows;

		} catch (SQLException e) {
			throw new DataException(e);
		} finally {
			JDBCUtils.closeStatement(preparedStatement);
		}

	}

	@Override
	public List<Deporte> findAll() throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionManager.getConnection();

			String sql;
			sql =  "SELECT ID_DEPORTE, NOMBRE "
					+"FROM DEPORTE ";

			// Preparar a query
			System.out.println("Creating statement...");
			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			resultSet = preparedStatement.executeQuery();			
			//STEP 5: Extract data from result set			

			List<Deporte> results = new ArrayList<Deporte>();                        
			Deporte d = null;


			while(resultSet.next()) {
				d = loadNext(resultSet);
				results.add(d);               	
			}

			return results;

		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
			JDBCUtils.closeConnection(connection);
		}  	
	}
	

	@Override
	public List<Deporte> findByNombre(String nombre) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{

			connection = ConnectionManager.getConnection();

			String sql;
			sql =    "SELECT ID_DEPORTE, NOMBRE " 
					+" FROM DEPORTE "
					+" WHERE "
					+"	UPPER(NOMBRE) LIKE ?"; 

			// Preparar a query
			System.out.println("Creating statement...");
			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Establece os parámetros
			int i = 1;
			preparedStatement.setString(i++, "%"+nombre.toUpperCase()+"%");

			resultSet = preparedStatement.executeQuery();			
			//STEP 5: Extract data from result set

			List<Deporte> results = new ArrayList<Deporte>();                        
			Deporte d = null;


			while(resultSet.next()) {
				d = loadNext(resultSet);
				results.add(d);               	
			}
			
			 
			return results;
			
		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
			JDBCUtils.closeConnection(connection);
		}
	}
	
private Deporte loadNext(ResultSet resultSet) throws Exception{
		
		Deporte d = new Deporte();
		int i = 1;
		Long id = resultSet.getLong(i++);
		String nome = resultSet.getString(i++);

		d.setIdDeporte(id);
		d.setNome(nome);
		
		return d;
	}

}

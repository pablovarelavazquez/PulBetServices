package com.pvv.pulbet.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.pvv.pulbet.dao.DireccionDAO;
import com.pvv.pulbet.dao.util.ConnectionManager;
import com.pvv.pulbet.dao.util.JDBCUtils;
import com.pvv.pulbet.exception.DataException;
import com.pvv.pulbet.model.Direccion;

public class DireccionDAOImpl implements DireccionDAO{

	@Override
	public Direccion findByUsuario(Connection connection, Long id) throws Exception {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionManager.getConnection();

			String sql;
			sql =  "SELECT D.ID_DIRECCION,D.CIUDAD,D.ID_PROVINCIA,D.CALLE,D.NUMERO,D.COD_POSTAL,D.PISO,D.LETRA "
					+"FROM DIRECCION D INNER JOIN USUARIO U ON U.ID_DIRECCION = D.ID_DIRECCION"
					+"WHERE U.ID_USUARIO = ? ";
			
			// Preparar a query
			System.out.println("Creating statement...");
			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Establece os parámetros
			int i = 1;
			preparedStatement.setLong(i++, id);


			resultSet = preparedStatement.executeQuery();			
			//STEP 5: Extract data from result set			

			Direccion d = null;

			if (resultSet.next()) {
				d =  loadNext(connection, resultSet);			
	
			} 

			return d;
			
		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	
	}

	@Override
	public List<Direccion> findAll(Connection connection) throws Exception {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionManager.getConnection();

			String sql;
			sql =  "SELECT ID_DIRECCION,CIUDAD,ID_PROVINCIA,CALLE,NUMERO,COD_POSTAL,PISO,LETRA "
					+"FROM DIRECCION ";

			// Preparar a query
			System.out.println("Creating statement...");
			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			resultSet = preparedStatement.executeQuery();			
			//STEP 5: Extract data from result set			

			List<Direccion> results = new ArrayList<Direccion>();                        
			Direccion d = null;


			while(resultSet.next()) {
				d = loadNext(connection, resultSet);
				results.add(d);               	
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
	public Direccion findById(Connection connection, Integer id) throws Exception {
		Direccion d = null;

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionManager.getConnection();

				
			String sql;
			sql =  "SELECT ID_DIRECCION,CIUDAD,ID_PROVINCIA,CALLE,NUMERO,COD_POSTAL,PISO,LETRA "
					+"FROM DIRECCION "
					+"WHERE ID_DIRECCION = ? ";

			// Preparar a query
			System.out.println("Creating statement...");
			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Establece os parámetros
			int i = 1;
			preparedStatement.setLong(i++, id);


			resultSet = preparedStatement.executeQuery();			
			//STEP 5: Extract data from result set			

			if (resultSet.next()) {
				d =  loadNext(connection, resultSet);			
				//System.out.println("Cargado "+u);
			} else {
				throw new Exception("Non se atopou direccion con id = "+id);
			}
			if (resultSet.next()) {
				throw new Exception("Direccion con id = "+id+" duplicada");
			}

		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	

		return d;
	}


	private Direccion loadNext(Connection connection ,ResultSet resultSet) throws Exception{


		Direccion d = new Direccion();
		int i = 1;
		Long id = resultSet.getLong(i++);
		String ciudad = resultSet.getString(i++);
		Long idProv = resultSet.getLong(i++);
		String calle = resultSet.getString(i++);
		Integer numero = resultSet.getInt(i++); 
		Integer codPostal = resultSet.getInt(i++);
		Integer piso = resultSet.getInt(i++);
		String letra = resultSet.getString(i++);

		d.setId(id);
		d.setCiudad(ciudad);
		d.setIdProvincia(idProv);
		d.setCalle(calle);
		d.setNumero(numero);
		d.setCodPostal(codPostal);
		d.setPiso(piso);
		d.setLetra(letra);

		return d;

	}
	
	@Override
	public Direccion create(Connection connection, Direccion d) throws Exception {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {          

			connection = ConnectionManager.getConnection();


			String queryString = "INSERT INTO DIRECCION(CIUDAD,ID_PROVINCIA,CALLE,NUMERO,COD_POSTAL,PISO,LETRA) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

			preparedStatement = connection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);

			int i = 1;     			
			preparedStatement.setString(i++, d.getCiudad());
			preparedStatement.setLong(i++, d.getIdProvincia());
			preparedStatement.setString(i++, d.getCalle());
			preparedStatement.setInt(i++, d.getNumero());
			preparedStatement.setInt(i++, d.getCodPostal());
			preparedStatement.setInt(i++, d.getPiso());
			preparedStatement.setString(i++, d.getLetra());

			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) {
				throw new SQLException("Can not add row to table 'Employees'");
			}

			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				Long id = resultSet.getLong(1);
				d.setId(id);				
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
		}
	}

	@Override
	public boolean update(Connection connection, Direccion d) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Long delete(Connection connection, Long id) throws Exception {
		PreparedStatement preparedStatement = null;

		try {
			connection = ConnectionManager.getConnection();

			String queryString =	
					"DELETE FROM DIRECCION " 
							+ "WHERE ID_DIRECCION = ? ";


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


}

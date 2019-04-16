package com.pvv.pulbet.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pvv.pulbet.dao.DireccionDAO;
import com.pvv.pulbet.dao.util.ConnectionManager;
import com.pvv.pulbet.dao.util.JDBCUtils;
import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.DuplicateInstanceException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.Direccion;

public class DireccionDAOImpl implements DireccionDAO{
	
	private static Logger logger = LogManager.getLogger(DireccionDAOImpl.class);	

	@Override
	public Direccion findByUsuario(Connection connection, Long id) throws DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Id = {}",id);
		}
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT ID_DIRECCION,CIUDAD,ID_PROVINCIA,CALLE,NUMERO,COD_POSTAL,PISO,LETRA,ID_USUARIO "
					+"FROM DIRECCION "
					+"WHERE ID_USUARIO = ? ";
			
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
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	
	}

	@Override
	public List<Direccion> findAll(Connection connection) throws DataException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT ID_DIRECCION,CIUDAD,ID_PROVINCIA,CALLE,NUMERO,COD_POSTAL,PISO,LETRA,ID_USUARIO "
					+"FROM DIRECCION ";

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
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}
	}

	@Override
	public Direccion findById(Connection connection, Integer id) throws InstanceNotFoundException, DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Id = {}",id);
		}
		
		Direccion d = null;

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
				
			String sql;
			sql =  "SELECT ID_DIRECCION,CIUDAD,ID_PROVINCIA,CALLE,NUMERO,COD_POSTAL,PISO,LETRA,ID_USUARIO "
					+"FROM DIRECCION "
					+"WHERE ID_DIRECCION = ? ";

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
				throw new InstanceNotFoundException("Non se atopou direccion con id = "+id, Direccion.class.getName());
			}


		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	

		return d;
	}


	private Direccion loadNext(Connection connection ,ResultSet resultSet) throws SQLException{


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
		Long idusu = resultSet.getLong(i++);

		d.setId(id);
		d.setCiudad(ciudad);
		d.setIdProvincia(idProv);
		d.setCalle(calle);
		d.setNumero(numero);
		d.setCodPostal(codPostal);
		d.setPiso(piso);
		d.setLetra(letra);
		d.setIdUsuario(idusu);
		
		return d;

	}
	
	@Override
	public Direccion create(Connection connection, Direccion d) throws DuplicateInstanceException, DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Direccion = {}", d);
		}
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {          

			String queryString = "INSERT INTO DIRECCION(CIUDAD,ID_PROVINCIA,CALLE,NUMERO,COD_POSTAL,PISO,LETRA,ID_USUARIO) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

			preparedStatement = connection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);

			int i = 1;     			
			preparedStatement.setString(i++, d.getCiudad());
			preparedStatement.setLong(i++, d.getIdProvincia());
			preparedStatement.setString(i++, d.getCalle());
			preparedStatement.setInt(i++, d.getNumero());
			preparedStatement.setInt(i++, d.getCodPostal());
			if(d.getPiso()==null) {
				preparedStatement.setNull(i++, Types.NULL);
			} else {
				preparedStatement.setInt(i++, d.getPiso());
			}
			
			preparedStatement.setString(i++, d.getLetra());
			preparedStatement.setLong(i++, d.getIdUsuario());

			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) {
				throw new SQLException("Can not add row to table 'Direccion'");
			}

			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				Long id = resultSet.getLong(1);
				d.setId(id);				
			} else {
				throw new DataException("Unable to fetch autogenerated primary key");
			}


			return d;
			
		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);			
		}
	}


	@Override
	public Long delete(Connection connection, Long id) throws InstanceNotFoundException, DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Id = {} ", id);
		}
		
		PreparedStatement preparedStatement = null;

		try {

			String queryString ="DELETE FROM DIRECCION " 
							+ "WHERE ID_DIRECCION = ? ";


			preparedStatement = connection.prepareStatement(queryString);

			int i = 1;
			preparedStatement.setLong(i++, id);

			long removedRows = preparedStatement.executeUpdate();

			if (removedRows == 0) {
				throw new InstanceNotFoundException("Non se atopou direccion: "+id,Direccion.class.getName());
			} 
			
			return removedRows;

		} catch (SQLException e) {
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeStatement(preparedStatement);
		}

	}


}

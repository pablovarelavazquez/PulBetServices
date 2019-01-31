package com.pvv.pulbet.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pvv.pulbet.dao.LineaApuestaDAO;
import com.pvv.pulbet.dao.util.JDBCUtils;
import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.DuplicateInstanceException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.LineaApuesta;
import com.pvv.pulbet.model.LineaApuestaId;


public class LineaApuestaDAOImpl implements LineaApuestaDAO{

	@Override
	public LineaApuesta create(Connection connection, LineaApuesta l) throws DuplicateInstanceException, DataException {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {          

			String queryString = "INSERT INTO LINEA_APUESTA(NUMERO_LINEA,ID_APUESTA,ID_RESULTADO,ID_EVENTO) "
					+ "VALUES (?, ?, ?, ?)";

			preparedStatement = connection.prepareStatement(queryString);

			int i = 1;     
			preparedStatement.setInt(i++,l.getNumLinea());
			preparedStatement.setLong(i++,l.getIdApuesta());
			preparedStatement.setLong(i++,l.getIdResultado());
			preparedStatement.setLong(i++, l.getIdEvento());

			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) {
				throw new SQLException("Can not add row to table 'LINEA_APUESTA'");
			}

			return l;
		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);			
		}
	}


	@Override
	public int delete(Connection connection, LineaApuestaId id) throws InstanceNotFoundException, DataException {
		PreparedStatement preparedStatement = null;

		try {

			String queryString =	
					"DELETE FROM LINEA_APUESTA " 
							+ "WHERE ID_APUESTA = ? AND NUMERO_LINEA = ? ";


			preparedStatement = connection.prepareStatement(queryString);

			int i = 1;
			preparedStatement.setLong(i++, id.getIdApuesta());
			preparedStatement.setLong(i++,id.getNumLinea());

			int removedRows = preparedStatement.executeUpdate();

			if (removedRows == 0) {
				throw new InstanceNotFoundException("Non se atopou linea de aposta :"+id,LineaApuesta.class.getName());
			} 
			
			return removedRows;

		} catch (SQLException e) {
			throw new DataException(e);
		} finally {
			JDBCUtils.closeStatement(preparedStatement);
		}
	}

	@Override
	public LineaApuesta findById(Connection connection, LineaApuestaId id) throws InstanceNotFoundException, DataException {
		LineaApuesta l = null;

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {


			String sql;
			sql =  "SELECT NUMERO_LINEA,ID_APUESTA,ID_RESULTADO,ID_EVENTO, PROCESADO "
					+"FROM LINEA_APUESTA "
					+"WHERE ID_APUESTA = ? AND NUMERO_LINEA = ? ";

			// Preparar a query
			System.out.println("Creating statement...");
			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Establece os parámetros
			int i = 1;
			preparedStatement.setLong(i++, id.getIdApuesta());
			preparedStatement.setLong(i++, id.getNumLinea());

			resultSet = preparedStatement.executeQuery();			
			//STEP 5: Extract data from result set			

			if (resultSet.next()) {
				l =  loadNext(resultSet);			
				//System.out.println("Cargado "+u);
			} else {
				throw new InstanceNotFoundException("Non se atopou a liña de aposta: "+id, LineaApuesta.class.getName());
			}
			

		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	

		return l;
	}

	@Override
	public List<LineaApuesta> findAll(Connection connection) throws DataException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT  NUMERO_LINEA,ID_APUESTA,ID_RESULTADO,ID_EVENTO, PROCESADO "
					+"FROM LINEA_APUESTA "
					+ "ORDER BY ID_APUESTA,NUMERO_LINEA ";

			// Preparar a query
			System.out.println("Creating statement...");
			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			resultSet = preparedStatement.executeQuery();			
			//STEP 5: Extract data from result set			

			List<LineaApuesta> results = new ArrayList<LineaApuesta>();                        
			LineaApuesta l = null;


			while(resultSet.next()) {
				l = loadNext(resultSet);
				results.add(l);               	
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
	public List<LineaApuesta> findByApuesta(Connection connection, Long id) throws DataException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT  NUMERO_LINEA,ID_APUESTA,ID_RESULTADO,ID_EVENTO, PROCESADO "
					+"FROM LINEA_APUESTA "
					+ "WHERE ID_APUESTA = ? "
					+ "ORDER BY NUMERO_LINEA ";

			// Preparar a query
			System.out.println("Creating statement...");
			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Establece os parámetros
			int i = 1;
			preparedStatement.setLong(i++, id);


			resultSet = preparedStatement.executeQuery();			
			//STEP 5: Extract data from result set			

			List<LineaApuesta> results = new ArrayList<LineaApuesta>();                        
			LineaApuesta l = null;


			while(resultSet.next()) {
				l = loadNext(resultSet);
				results.add(l);               	
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
	public List<LineaApuesta> findByEvento(Connection connection, Long id) throws DataException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT  NUMERO_LINEA,ID_APUESTA,ID_RESULTADO,ID_EVENTO, PROCESADO "
					+"FROM LINEA_APUESTA "
					+ "WHERE ID_EVENTO = ? "
					+ "ORDER BY ID_APUESTA, NUMERO_LINEA ";

			// Preparar a query
			System.out.println("Creating statement...");
			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Establece os parámetros
			int i = 1;
			preparedStatement.setLong(i++, id);


			resultSet = preparedStatement.executeQuery();			
			//STEP 5: Extract data from result set			

			List<LineaApuesta> results = new ArrayList<LineaApuesta>();                        
			LineaApuesta l = null;


			while(resultSet.next()) {
				l = loadNext(resultSet);
				results.add(l);               	
			}

			return results;

		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	
	}

	private LineaApuesta loadNext(ResultSet resultSet) throws SQLException{

		LineaApuesta l = new LineaApuesta();
		int i = 1;
		Integer numeroLinea = resultSet.getInt(i++);
		Long idApuesta = resultSet.getLong(i++);
		Long idRes = resultSet.getLong(i++);
		Long idEv = resultSet.getLong(i++);
		Boolean procesado = resultSet.getBoolean(i++);

		l.setNumLinea(numeroLinea);
		l.setIdApuesta(idApuesta);
		l.setIdResultado(idRes);
		l.setIdEvento(idEv);
		l.setProcesado(procesado);

		return l;

	}
	
	
}

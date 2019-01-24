package com.pvv.pulbet.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pvv.pulbet.dao.LineaApuestaDAO;
import com.pvv.pulbet.dao.util.ConnectionManager;
import com.pvv.pulbet.dao.util.JDBCUtils;
import com.pvv.pulbet.exception.DataException;
import com.pvv.pulbet.model.Apuesta;
import com.pvv.pulbet.model.LineaApuesta;
import com.pvv.pulbet.model.LineaApuestaId;
import com.pvv.pulbet.model.Usuario;

public class LineaApuestaDAOImpl implements LineaApuestaDAO{

	@Override
	public LineaApuesta create(LineaApuesta l) throws Exception {

		Connection connection = null; 
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {          

			connection = ConnectionManager.getConnection();



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
			JDBCUtils.closeConnection(connection);
		}
	}


	@Override
	public int delete(LineaApuestaId id) throws Exception {
		Connection connection = null; 
		PreparedStatement preparedStatement = null;

		try {
			connection = ConnectionManager.getConnection();

			String queryString =	
					"DELETE FROM LINEA_APUESTA " 
							+ "WHERE ID_APUESTA = ? AND NUMERO_LINEA = ? ";


			preparedStatement = connection.prepareStatement(queryString);

			int i = 1;
			preparedStatement.setLong(i++, id.getIdApuesta());
			preparedStatement.setLong(i++,id.getNumLinea());

			int removedRows = preparedStatement.executeUpdate();

			return removedRows;

		} catch (SQLException e) {
			throw new DataException(e);
		} finally {
			JDBCUtils.closeStatement(preparedStatement);
		}
	}

	@Override
	public LineaApuesta findById(LineaApuestaId id) throws Exception {
		LineaApuesta l = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionManager.getConnection();


			String sql;
			sql =  "SELECT NUMERO_LINEA,ID_APUESTA,ID_RESULTADO,ID_EVENTO "
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
				throw new Exception("Non se atopou a liña de aposta.");
			}
			if (resultSet.next()) {
				throw new Exception("Linea de aposta duplicada.");
			}

		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
			JDBCUtils.closeConnection(connection);
		}  	

		return l;
	}

	@Override
	public List<LineaApuesta> findAll() throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionManager.getConnection();

			String sql;
			sql =  "SELECT  NUMERO_LINEA,ID_APUESTA,ID_RESULTADO,ID_EVENTO "
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
			JDBCUtils.closeConnection(connection);
		}
	}

	@Override
	public List<LineaApuesta> findByApuesta(Long id) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionManager.getConnection();

			String sql;
			sql =  "SELECT  NUMERO_LINEA,ID_APUESTA,ID_RESULTADO,ID_EVENTO "
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
			JDBCUtils.closeConnection(connection);
		}  	
	}

	@Override
	public List<LineaApuesta> findByEvento(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	private LineaApuesta loadNext(ResultSet resultSet) throws Exception{

		LineaApuesta l = new LineaApuesta();
		int i = 1;
		Integer numeroLinea = resultSet.getInt(i++);
		Long idApuesta = resultSet.getLong(i++);
		Long idRes = resultSet.getLong(i++);
		Long idEv = resultSet.getLong(i++);

		l.setNumLinea(numeroLinea);
		l.setIdApuesta(idApuesta);
		l.setIdResultado(idRes);
		l.setIdEvento(idEv);

		return l;

	}

}

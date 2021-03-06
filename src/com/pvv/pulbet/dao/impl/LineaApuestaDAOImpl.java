package com.pvv.pulbet.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pvv.pulbet.dao.LineaApuestaDAO;
import com.pvv.pulbet.dao.util.JDBCUtils;
import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.DuplicateInstanceException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.LineaApuesta;
import com.pvv.pulbet.model.LineaApuestaId;


public class LineaApuestaDAOImpl implements LineaApuestaDAO{
	
	private static Logger logger = LogManager.getLogger(LineaApuestaDAOImpl.class);

	@Override
	public LineaApuesta create(Connection connection, LineaApuesta l) throws DuplicateInstanceException, DataException {

		if(logger.isDebugEnabled()) {
			logger.debug("LineaApuesta = {}", l);
		}
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {          

			String queryString = "INSERT INTO LINEA_APUESTA(NUMERO_LINEA,ID_APUESTA,ID_RESULTADO,ID_EVENTO,PROCESADO) "
					+ "VALUES (?, ?, ?, ?, ?)";

			preparedStatement = connection.prepareStatement(queryString);

			int i = 1;     
			preparedStatement.setInt(i++,l.getNumLinea());
			preparedStatement.setLong(i++,l.getIdApuesta());
			preparedStatement.setLong(i++,l.getIdResultado());
			preparedStatement.setLong(i++, l.getIdEvento());
			preparedStatement.setInt(i++, l.getProcesado());

			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) {
				throw new SQLException("Can not add row to table 'LINEA_APUESTA'");
			}

			return l;
		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);			
		}
	}


	@Override
	public int delete(Connection connection, LineaApuestaId id) throws InstanceNotFoundException, DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Id = {}", id);
		}
		
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
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeStatement(preparedStatement);
		}
	}
	
	@Override
	public void update(Connection connection, LineaApuesta l) throws InstanceNotFoundException, DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("LineaApuesta = {}", l);
		}
		
		PreparedStatement preparedStatement = null;

		try {          

			String queryString = "UPDATE LINEA_APUESTA "
					+ "SET ID_RESULTADO = ?, "
					+ "ID_EVENTO = ?, "
					+ "PROCESADO = ? "
					+ "WHERE ID_APUESTA= ? AND NUMERO_LINEA = ? ";

			preparedStatement = connection.prepareStatement(queryString);

			int i = 1;     			
			
			preparedStatement.setLong(i++, l.getIdResultado());
			preparedStatement.setLong(i++, l.getIdEvento());
			preparedStatement.setInt(i++, l.getProcesado());
			preparedStatement.setLong(i++, l.getIdApuesta());
			preparedStatement.setInt(i++, l.getNumLinea());

			int updatedRows = preparedStatement.executeUpdate();


			if (updatedRows == 0) {
				throw new InstanceNotFoundException("Non se atopou a Linea de aposta aposta: "+l.getIdApuesta()+" li�a "+l.getNumLinea(), LineaApuesta.class.getName());
			}

			if (updatedRows > 1) {
				throw new SQLException("Duplicate row for id_apuesta = "+l.getIdApuesta()+" numero_linea "+l.getNumLinea()+" in table 'Linea_Apuesta'");
			}
			
		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		} finally {
			JDBCUtils.closeStatement(preparedStatement);			
		}
	}

	@Override
	public LineaApuesta findById(Connection connection, LineaApuestaId id) throws InstanceNotFoundException, DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("LineaApuestaId = {}", id);
		}
		
		LineaApuesta l = null;

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {


			String sql;
			sql =  "SELECT NUMERO_LINEA,ID_APUESTA,ID_RESULTADO,ID_EVENTO, PROCESADO "
					+"FROM LINEA_APUESTA "
					+"WHERE ID_APUESTA = ? AND NUMERO_LINEA = ? ";

			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Establece os par�metros
			int i = 1;
			preparedStatement.setLong(i++, id.getIdApuesta());
			preparedStatement.setLong(i++, id.getNumLinea());

			resultSet = preparedStatement.executeQuery();			
			//STEP 5: Extract data from result set			

			if (resultSet.next()) {
				l =  loadNext(connection,resultSet);			
				//System.out.println("Cargado "+u);
			} else {
				throw new InstanceNotFoundException("Non se atopou a li�a de aposta: "+id, LineaApuesta.class.getName());
			}


		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
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


			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			resultSet = preparedStatement.executeQuery();			
			//STEP 5: Extract data from result set			

			List<LineaApuesta> results = new ArrayList<LineaApuesta>();                        
			LineaApuesta l = null;


			while(resultSet.next()) {
				l = loadNext(connection,resultSet);
				results.add(l);               	
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
	public List<LineaApuesta> findByApuesta(Connection connection, Long id) throws DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Id = {}", id);
		}
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT  NUMERO_LINEA,ID_APUESTA,ID_RESULTADO,ID_EVENTO, PROCESADO "
					+"FROM LINEA_APUESTA "
					+ "WHERE ID_APUESTA = ? "
					+ "ORDER BY NUMERO_LINEA ";

			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Establece os par�metros
			int i = 1;
			preparedStatement.setLong(i++, id);


			resultSet = preparedStatement.executeQuery();			
			//STEP 5: Extract data from result set			

			List<LineaApuesta> results = new ArrayList<LineaApuesta>();                        
			LineaApuesta l = null;


			while(resultSet.next()) {
				l = loadNext(connection,resultSet);
				results.add(l);               	
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
	public List<LineaApuesta> findByEvento(Connection connection, Long id) throws DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Id = {}", id);
		}

		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT  NUMERO_LINEA,ID_APUESTA,ID_RESULTADO,ID_EVENTO, PROCESADO "
					+"FROM LINEA_APUESTA "
					+ "WHERE ID_EVENTO = ? "
					+ "ORDER BY ID_APUESTA, NUMERO_LINEA ";


			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Establece os par�metros
			int i = 1;
			preparedStatement.setLong(i++, id);


			resultSet = preparedStatement.executeQuery();			
			//STEP 5: Extract data from result set			

			List<LineaApuesta> results = new ArrayList<LineaApuesta>();                        
			LineaApuesta l = null;


			while(resultSet.next()) {
				l = loadNext(connection,resultSet);
				results.add(l);               	
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

	private LineaApuesta loadNext(Connection connection,ResultSet resultSet) throws SQLException{

		LineaApuesta l = new LineaApuesta();
		int i = 1;
		Integer numeroLinea = resultSet.getInt(i++);
		Long idApuesta = resultSet.getLong(i++);
		Long idRes = resultSet.getLong(i++);
		Long idEv = resultSet.getLong(i++);
		Integer procesado = resultSet.getInt(i++);

		l.setNumLinea(numeroLinea);
		l.setIdApuesta(idApuesta);
		l.setIdResultado(idRes);
		l.setIdEvento(idEv);
		l.setProcesado(procesado);

		return l;

	}

}



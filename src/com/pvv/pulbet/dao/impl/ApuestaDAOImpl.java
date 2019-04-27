package com.pvv.pulbet.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pvv.pulbet.dao.ApuestaDAO;
import com.pvv.pulbet.dao.LineaApuestaDAO;
import com.pvv.pulbet.dao.util.JDBCUtils;
import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.DuplicateInstanceException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.Apuesta;
import com.pvv.pulbet.model.LineaApuesta;
import com.pvv.pulbet.model.Provincia;
import com.pvv.pulbet.service.ApuestaCriteria;
import com.pvv.pulbet.service.Results;

public class ApuestaDAOImpl implements ApuestaDAO{

	private static Logger logger = LogManager.getLogger(ApuestaDAOImpl.class);
	private LineaApuestaDAO lineaApuestaDAO= null;

	public ApuestaDAOImpl() {
		lineaApuestaDAO = new LineaApuestaDAOImpl();
	}

	@Override
	public Apuesta findById(Connection connection, Long id)
			throws InstanceNotFoundException, DataException {

		if(logger.isDebugEnabled()) {
			logger.debug("Id = {}", id);
		}

		Apuesta a = null;

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT ID_APUESTA, IMPORTE, ID_USUARIO, FECHA, GANANCIAS, PROCESADO "
					+"FROM APUESTA "
					+"WHERE ID_APUESTA = ? ";

			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			preparedStatement.setLong(i++, id);


			resultSet = preparedStatement.executeQuery();			

			if (resultSet.next()) {
				a =  loadNext(connection, resultSet);			
			} else {
				throw new InstanceNotFoundException("Non se atopou apuesta con id = "+id, Apuesta.class.getName());
			}


		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	

		return a;
	}

	@Override
	public Results<Apuesta> findByUsuario(Connection connection, Long id, int startIndex, int count)
			throws DataException {

		if(logger.isDebugEnabled()) {
			logger.debug("Id = {}", id);
		}

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT ID_APUESTA, IMPORTE, ID_USUARIO, FECHA, GANANCIAS, PROCESADO "
					+"FROM APUESTA "
					+"WHERE ID_USUARIO = ? ";

			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Establece os parámetros
			int i = 1;
			preparedStatement.setLong(i++, id);


			resultSet = preparedStatement.executeQuery();

			List<Apuesta> page = new ArrayList<Apuesta>();
			Apuesta a = null;
			int currentCount = 0;

			if ((startIndex >=1) && resultSet.absolute(startIndex)) {
				do {
					a = loadNext(connection, resultSet); 
					page.add(a);               	
					currentCount++;                	
				} while ((currentCount < count) && resultSet.next()) ;
			}
			
			
			int totalRows = JDBCUtils.getTotalRows(resultSet);

			Results<Apuesta> results = new Results<Apuesta>(page, startIndex, totalRows);

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
	public Apuesta create(Connection connection, Apuesta a) 
			throws DuplicateInstanceException, DataException{

		if(logger.isDebugEnabled()) {
			logger.debug("Apuesta = {} ", a);
		}
		
		  

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {          


			String queryString = "INSERT INTO APUESTA(IMPORTE, ID_USUARIO, FECHA, GANANCIAS) "
					+ "VALUES (?, ?, ?, ?)";

			preparedStatement = connection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);

			int i = 1;     			
			preparedStatement.setDouble(i++, a.getImporte());
			preparedStatement.setLong(i++, a.getIdUsuario());
			preparedStatement.setTimestamp(i++, new Timestamp(a.getFecha().getTime()));
			preparedStatement.setDouble(i++, a.getGanancias());



			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) {
				throw new SQLException("Can not add row to table 'Apuesta'");
			}

			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				Long id = resultSet.getLong(1);
				a.setIdApuesta(id);				
			} else {
				throw new DataException("Unable to fetch autogenerated primary key");
			}


			createLineasApuesta(connection, a.getIdApuesta(), a.getLineas());

			return a;					

		} catch (SQLException ex) {

			logger.warn(ex.getMessage(), ex);

			throw new DataException(ex);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);			
		}

	}

	@Override
	public void update(Connection connection, Apuesta a) throws InstanceNotFoundException, DataException {
		PreparedStatement preparedStatement = null;
		try {

			if(logger.isDebugEnabled()) {
				logger.debug("Apuesta = {} ", a);
			}

			deleteLineasApuesta(connection, a.getIdApuesta());

			String queryString = 
					"UPDATE APUESTA " +
							"SET IMPORTE = ? , ID_USUARIO = ? , FECHA = ? , PROCESADO = ? , GANANCIAS = ? "+
							"WHERE ID_APUESTA = ? ";

			preparedStatement = connection.prepareStatement(queryString);

			int i = 1;
			preparedStatement.setDouble(i++, a.getImporte());
			preparedStatement.setLong(i++, a.getIdUsuario());
			preparedStatement.setDate(i++, new java.sql.Date(a.getFecha().getTime()));
			preparedStatement.setInt(i++, a.getProcesado());
			preparedStatement.setDouble(i++, a.getGanancias());
			preparedStatement.setLong(i++, a.getIdApuesta());

			int updatedRows = preparedStatement.executeUpdate();

			if (updatedRows == 0) {
				throw new InstanceNotFoundException("Non se atopou aposta: "+a.getIdApuesta(), Apuesta.class.getName());
			}

			if (updatedRows > 1) {
				
				throw new SQLException("Duplicate row for id = '" + 
						a.getIdApuesta() + "' in table 'Apuesta'");
			}     

			createLineasApuesta(connection, a.getIdApuesta(), a.getLineas());

			for(LineaApuesta la :  a.getLineas()) {
				lineaApuestaDAO.update(connection, la);
			}


		} catch (SQLException e) {
			logger.warn(e.getMessage(), e);
			throw new DataException(e);    
		} finally {
			JDBCUtils.closeStatement(preparedStatement);
		}

	}

	@Override
	public long delete(Connection connection, Long id) 
			throws InstanceNotFoundException, DataException {

		if(logger.isDebugEnabled()) {
			logger.debug("Id = {} ", id);
		}
		PreparedStatement preparedStatement = null;

		try {

			deleteLineasApuesta(connection, id);

			String queryString =	
					"DELETE FROM APUESTA " 
							+ "WHERE ID_APUESTA = ? ";

			preparedStatement = connection.prepareStatement(queryString);

			int i = 1;
			preparedStatement.setLong(i++, id);

			int removedRows = preparedStatement.executeUpdate();

			if (removedRows == 0) {
				throw new InstanceNotFoundException("Non se atopou aposta :"+id, Apuesta.class.getName());
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
	public List<Apuesta> findAll(Connection connection) throws DataException {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT ID_APUESTA, IMPORTE, ID_USUARIO, FECHA, GANANCIAS, PROCESADO "
					+"FROM APUESTA";

			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			resultSet = preparedStatement.executeQuery();			
			//STEP 5: Extract data from result set			

			List<Apuesta> results = new ArrayList<Apuesta>();                        
			Apuesta a= null;


			while(resultSet.next()) {
				a = loadNext(connection, resultSet);
				results.add(a);               	
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
	public Results<Apuesta> findByCriteria(Connection connection, ApuestaCriteria apuesta, Boolean history, int startIndex, int count)
			throws DataException {
		if(logger.isDebugEnabled()) {
			logger.debug("ApuestaCriteria = {}", apuesta);
		}

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder queryString = null;

		try {
			queryString = new StringBuilder(
					"select id_apuesta, importe, id_usuario, fecha, ganancias, procesado "
							+ "from apuesta ");

			boolean first = true;


			if (apuesta.getIdApuesta()!=null) {
				addClause(queryString, first, " id_apuesta = ? ");
				first = false;
			}

			if (apuesta.getImporte()!=null) {
				addClause(queryString, first, " importe = ? ");
				first = false;
			}

			if (apuesta.getIdUsuario()!=null) {
				addClause(queryString, first, " id_usuario = ? ");
				first = false;
			}

			if (apuesta.getDesde()!=null) {
				addClause(queryString, first, " fecha >= ? ");
				first = false;
			}			

			if (apuesta.getGanancias()!=null) {
				addClause(queryString, first, " ganancias = ? ");
				first = false;
			}	
			
			if (apuesta.getProcesado()!=null) {
				addClause(queryString, first, " procesado = ? ");
				first = false;
			}	

			if (apuesta.getHasta()!=null) {
				addClause(queryString, first, " fecha <= ? ");
				first = false;
			}

			queryString.append("order by fecha desc ");

			preparedStatement = connection.prepareStatement(queryString.toString(),
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			int i = 1;       

			if (apuesta.getIdApuesta()!=null)
				preparedStatement.setLong(i++,apuesta.getIdApuesta());
			if (apuesta.getImporte()!=null) 
				preparedStatement.setDouble(i++, apuesta.getImporte());
			if (apuesta.getIdUsuario()!=null) 
				preparedStatement.setLong(i++, apuesta.getIdUsuario());
			if (apuesta.getDesde()!=null)
				preparedStatement.setDate(i++, new java.sql.Date(apuesta.getDesde().getTime()));
			if (apuesta.getHasta()!=null)
				preparedStatement.setDate(i++, new java.sql.Date(apuesta.getHasta().getTime()));
			if (apuesta.getGanancias()!=null) 
				preparedStatement.setDouble(i++, apuesta.getGanancias());
			if (apuesta.getProcesado()!=null)
				preparedStatement.setInt(i++, apuesta.getProcesado());

			resultSet = preparedStatement.executeQuery();

			List<Apuesta> page = new ArrayList<Apuesta>();
			Apuesta a = null;
			int currentCount = 0;

			//Comprobaremos se estamos buscando apostas rematadas, sen rematar ou todas
			if ((startIndex >=1) && resultSet.absolute(startIndex)) {
				do {
					a = loadNext(connection, resultSet);
					if(history == null){
						page.add(a);               	
						currentCount++;
					} else if (history == false) {
						if(a.getProcesado() == 0) {
							page.add(a);               	
							currentCount++;
						}
					} else if (history == true)  {
						if(a.getProcesado() != 0) {
							page.add(a);               	
							currentCount++;
						}
					}
				} while ((currentCount < count) && resultSet.next()) ;
			}
			
			
			int totalRows = JDBCUtils.getTotalRows(resultSet);

			Results<Apuesta> results = new Results<Apuesta>(page, startIndex, totalRows);

			return results;
			
		} catch (SQLException e) {
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}
	}



	private Apuesta loadNext(Connection c, ResultSet resultSet) 
			throws SQLException, DataException{


		Apuesta a = new Apuesta();
		int i = 1;
		Long idApuesta = resultSet.getLong(i++);
		Double importe = resultSet.getDouble(i++);
		Long idUsuario = resultSet.getLong(i++); 
		Date fecha = resultSet.getTimestamp(i++);
		Double ganancias = resultSet.getDouble(i++);
		Integer procesado = resultSet.getInt(i++);

		a.setIdApuesta(idApuesta);
		a.setImporte(importe);
		a.setFecha(fecha);
		a.setIdUsuario(idUsuario);
		a.setGanancias(ganancias);
		a.setProcesado(procesado);

		List<LineaApuesta> lineas = lineaApuestaDAO.findByApuesta(c, idApuesta);
		a.setLineas(lineas);

		return a;

	}

	protected void deleteLineasApuesta(Connection c, Long idApuesta)
			throws SQLException, DataException {

		if(logger.isDebugEnabled()) {
			logger.debug("Id = {}", idApuesta);
		}

		PreparedStatement preparedStatement = null;

		try {

			String queryString =	
					"DELETE FROM LINEA_APUESTA " 
							+ "WHERE ID_APUESTA = ? ";

			preparedStatement = c.prepareStatement(queryString);

			int i = 1;
			preparedStatement.setLong(i++,idApuesta);

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeStatement(preparedStatement);
		}
	}

	protected void createLineasApuesta(Connection connection, Long idApuesta,  List<LineaApuesta> lineas)
			throws SQLException, DataException {

		if(logger.isDebugEnabled()) {
			logger.debug("Id = {}, lineas{}", idApuesta, lineas);
		}

		PreparedStatement preparedStatement = null;
		try {          
			String queryString = null;
			int i;
			for (LineaApuesta la: lineas ) {
				queryString = "INSERT INTO LINEA_APUESTA (NUMERO_LINEA, ID_APUESTA, ID_RESULTADO, ID_EVENTO) VALUES (?, ?, ?, ?)";
				preparedStatement = connection.prepareStatement(queryString);				

				i = 1;     
				preparedStatement.setLong(i++, la.getNumLinea());
				preparedStatement.setLong(i++, idApuesta);
				preparedStatement.setLong(i++, la.getIdResultado());
				preparedStatement.setLong(i++, la.getIdEvento());

				int insertedRows = preparedStatement.executeUpdate();

				if (insertedRows == 0) {
					throw new SQLException("Can not add row to table 'LineaApuesta'");
				}	

				JDBCUtils.closeStatement(preparedStatement);
			} 
		} catch (SQLException e) {
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeStatement(preparedStatement);
		}
	}

	private void addClause(StringBuilder queryString, boolean first, String clause) {
		queryString.append(first? "WHERE ": " AND ").append(clause);
	}

	@Override
	public void updateEstado(Connection connection, Apuesta a) throws InstanceNotFoundException, DataException {
		PreparedStatement preparedStatement = null;
		try {

			if(logger.isDebugEnabled()) {
				logger.debug("Apuesta = {} ", a);
			}

			String queryString = 
					"UPDATE APUESTA " +
							"SET PROCESADO = ? "+
							"WHERE ID_APUESTA = ? ";

			preparedStatement = connection.prepareStatement(queryString);

			int i = 1;
			preparedStatement.setInt(i++, a.getProcesado());
			preparedStatement.setLong(i++, a.getIdApuesta());

			int updatedRows = preparedStatement.executeUpdate();

			if (updatedRows == 0) {
				throw new InstanceNotFoundException("Non se atopou aposta: "+a.getIdApuesta(), Apuesta.class.getName());
			}

			if (updatedRows > 1) {
				throw new SQLException("Duplicate row for id = '" + 
						a.getIdApuesta() + "' in table 'Apuesta'");
			}     

		} catch (SQLException e) {
			logger.warn(e.getMessage(), e);
			throw new DataException(e);    
		} finally {
			JDBCUtils.closeStatement(preparedStatement);
		}
	}


}

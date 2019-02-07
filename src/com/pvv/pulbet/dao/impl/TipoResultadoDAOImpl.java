package com.pvv.pulbet.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pvv.pulbet.dao.ResultadoDAO;
import com.pvv.pulbet.dao.TipoResultadoDAO;
import com.pvv.pulbet.dao.util.JDBCUtils;
import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.DuplicateInstanceException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.Resultado;
import com.pvv.pulbet.model.TipoResultado;

public class TipoResultadoDAOImpl implements TipoResultadoDAO{
	
	private static Logger logger = LogManager.getLogger(TipoResultadoDAOImpl.class);
	private ResultadoDAO resultadoDAO = null;
	
	public TipoResultadoDAOImpl() {
		resultadoDAO = new ResultadoDAOImpl();
	}
	

	@Override
	public TipoResultado create(Connection connection, TipoResultado t) throws DuplicateInstanceException, DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("TipoResultado = {}", t);
		}
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {          

			String queryString = "INSERT INTO TIPO_RESULTADO(NOMBRE) "
					+ "VALUES (?)";

			preparedStatement = connection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);

			int i = 1;     			
			preparedStatement.setString(i++, t.getNome());



			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) {
				throw new SQLException("Can not add row to table 'TipoResultado'");
			}

			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				Integer id = resultSet.getInt(1);
				t.setIdTipoResultado(id);
			} else {
				throw new DataException("Unable to fetch autogenerated primary key");
			}

			return t;					

		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);			
		}

	}

	@Override
	public long delete(Connection connection, Integer id) throws InstanceNotFoundException, DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Id = {}", id);
		}

		
		PreparedStatement preparedStatement = null;

		try {
			String queryString =	
					"DELETE FROM TIPO_RESULTADO " 
							+ "WHERE ID_TIPO_RESULTADO = ? ";


			preparedStatement = connection.prepareStatement(queryString);

			int i = 1;
			preparedStatement.setLong(i++, id);

			long removedRows = preparedStatement.executeUpdate();


			return removedRows;

		} catch (SQLException e) {
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeStatement(preparedStatement);
		}


	}

	@Override
	public TipoResultado findById(Connection connection, Integer id) throws InstanceNotFoundException, DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Id = {}", id);
		}
		
		TipoResultado t = null;

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT ID_TIPO_RESULTADO, NOMBRE "
					+"FROM TIPO_RESULTADO "
					+"WHERE ID_TIPO_RESULTADO = ? ";

			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Establece os parámetros
			int i = 1;
			preparedStatement.setLong(i++, id);


			resultSet = preparedStatement.executeQuery();			
			//STEP 5: Extract data from result set			

			if (resultSet.next()) {
				t =  loadNext(connection, resultSet);			
				//System.out.println("Cargado "+u);
			} else {
				throw new InstanceNotFoundException("Non se atopou TIPO_RESULTADO con id = "+id, TipoResultado.class.getName());
			}

			return t;
		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	

		
	}

	@Override
	public List<TipoResultado> findByEvento(Connection connection, Long id) throws DataException {

		if(logger.isDebugEnabled()) {
			logger.debug("Id = {}", id);
		}
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{

			String sql;
			sql =    "SELECT T.ID_TIPO_RESULTADO, T.NOMBRE " 
					+" FROM TIPO_RESULTADO T INNER JOIN TIPO_RESULTADO_EVENTO E ON T.ID_TIPO_RESULTADO = E.ID_TIPO_RESULTADO "
					+" WHERE "
					+"	E.ID_EVENTO = ?";


			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Establece os parámetros
			int i = 1;
			preparedStatement.setLong(i++, id);

			resultSet = preparedStatement.executeQuery();			
			//STEP 5: Extract data from result set			

			List<TipoResultado> results = new ArrayList<TipoResultado>();                        
			TipoResultado t = null;


			while(resultSet.next()) {
				t = loadNext(connection, resultSet);
				results.add(t);               	
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
	public List<TipoResultado> findByDeporte(Connection connection, Long id) throws DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Id = {}", id);
		}

		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{

			String sql;
			sql =    "select tr.id_tipo_resultado, tr.nombre " 
					+" from tipo_resultado tr inner join tipo_resultado_deporte rd on rd.id_tipo_resultado = tr.id_tipo_resultado "
					+" where rd.id_deporte = ? ";

			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Establece os parámetros
			int i = 1;
			preparedStatement.setLong(i++, id);

			resultSet = preparedStatement.executeQuery();			
			//STEP 5: Extract data from result set			

			List<TipoResultado> results = new ArrayList<TipoResultado>();                        
			TipoResultado t = null;


			while(resultSet.next()) {
				t = loadNext(connection, resultSet);
				results.add(t);               	
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


	private TipoResultado loadNext(Connection connection, ResultSet resultSet) throws SQLException, DataException{


		TipoResultado t = new TipoResultado();
		int i = 1;
		Integer id = resultSet.getInt(i++);
		String nome = resultSet.getString(i++);

		t.setIdTipoResultado(id);
		t.setNome(nome);
		
		List<Resultado> resultados = resultadoDAO.findByTipoResultado(connection, id);
		t.setResultados(resultados);
		

		return t;

	}



}

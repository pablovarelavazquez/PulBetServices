package com.pvv.pulbet.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pvv.pulbet.dao.ResultadoDAO;
import com.pvv.pulbet.dao.impl.ResultadoDAOImpl;
import com.pvv.pulbet.dao.util.ConnectionManager;
import com.pvv.pulbet.dao.util.JDBCUtils;
import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.Resultado;
import com.pvv.pulbet.service.ResultadoService;

public class ResultadoServiceImpl implements ResultadoService{
	
	private ResultadoDAO resultadoDAO = null;
	private static Logger logger = LogManager.getLogger(ResultadoServiceImpl.class);
	
	public ResultadoServiceImpl() {
		resultadoDAO = new ResultadoDAOImpl();
	}

//	@Override
//	public Resultado create(Resultado r) throws DuplicateInstanceException, DataException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Long delete(Long id) throws InstanceNotFoundException, DataException {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public Resultado findById(Integer id, String idioma) throws InstanceNotFoundException, DataException {

		if(logger.isDebugEnabled()) {
			logger.debug("Id = {}", id);
		}
		
		Connection connection = null;

		try {

			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(true);

			return resultadoDAO.findById(connection, id, idioma);

		} catch (SQLException e){
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
		}
	}

	@Override
	public List<Resultado> findByTipoResultado(Integer id, String idioma) throws DataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Resultado> findAll(String idioma) throws DataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resultado findCuota( Long idEvento, Long idResultado, String idioma) throws DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("IdEvento = {} IdResultado = {}", idEvento, idResultado);
		}
		
		Connection connection = null;

		try {

			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(true);

			return resultadoDAO.findCuota(connection, idEvento, idResultado, idioma);

		} catch (SQLException e){
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
		}
		
	}

	
	
}

package com.pvv.pulbet.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.pvv.pulbet.dao.ApuestaDAO;
import com.pvv.pulbet.dao.impl.ApuestaDAOImpl;
import com.pvv.pulbet.dao.util.ConnectionManager;
import com.pvv.pulbet.dao.util.JDBCUtils;
import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.model.Apuesta;
import com.pvv.pulbet.service.ApuestaService;

public class ApuestaServiceImpl implements ApuestaService{
	
	ApuestaDAO apuestaDAO = null;
	
	public ApuestaServiceImpl() {
		apuestaDAO = new ApuestaDAOImpl();
	}
	
	@Override
	public List<Apuesta> findByUsuario(Long id) throws DataException {
		Connection connection = null;
		
		try {
			
			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(true);
			
			return apuestaDAO.findByUsuario(connection, id);
			
		} catch (SQLException e){
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
		}
	}

}

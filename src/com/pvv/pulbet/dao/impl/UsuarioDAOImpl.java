package com.pvv.pulbet.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pvv.pulbet.dao.ApuestaDAO;
import com.pvv.pulbet.dao.DireccionDAO;
import com.pvv.pulbet.dao.UsuarioDAO;
import com.pvv.pulbet.dao.util.ConnectionManager;
import com.pvv.pulbet.dao.util.JDBCUtils;
import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.DuplicateInstanceException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.Apuesta;
import com.pvv.pulbet.model.Direccion;
import com.pvv.pulbet.model.Usuario;


public class UsuarioDAOImpl implements UsuarioDAO{
	
	private DireccionDAO direccionDAO = null;
	private ApuestaDAO apuestaDAO = null;

	public UsuarioDAOImpl () {
		direccionDAO = new DireccionDAOImpl();
	}

	@Override
	public Usuario findById(Connection connection, Long id) 
			throws InstanceNotFoundException, DataException{
		Usuario u = null;

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionManager.getConnection();

				
			String sql;
			sql =  "SELECT ID_USUARIO, EMAIL, NOMBRE, APELLIDO1, APELLIDO2, PASSWORD, BANCO, TELEFONO, FECHA_NACIMIENTO, NOMBRE_USUARIO, DNI "
					+"FROM USUARIO "
					+"WHERE ID_USUARIO = ? ";

			// Preparar a query
			System.out.println("Creating statement...");
			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Establece os parámetros
			int i = 1;
			preparedStatement.setLong(i++, id);


			resultSet = preparedStatement.executeQuery();			
			//STEP 5: Extract data from result set			

			if (resultSet.next()) {
				u =  loadNext(connection, resultSet);			
				//System.out.println("Cargado "+u);
			} else {
				throw new InstanceNotFoundException("Non se atopou usuario con id = "+id, Usuario.class.getName());
			}

			return u;
			
		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	

		
	}

	@Override
	public Usuario findByEmail(Connection connection, String email) throws DataException {
		
		Usuario u = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{

			connection = ConnectionManager.getConnection();

			String sql;
			sql =    "SELECT ID_USUARIO, EMAIL, NOMBRE, APELLIDO1, APELLIDO2, PASSWORD, BANCO, TELEFONO, FECHA_NACIMIENTO, NOMBRE_USUARIO, DNI " 
					+" FROM USUARIO "
					+" WHERE "
					+"	UPPER(EMAIL) LIKE ?";


			// Preparar a query
			System.out.println("Creating statement...");
			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Establece os parámetros
			int i = 1;
			preparedStatement.setString(i++, "%"+email.toUpperCase()+"%");

			resultSet = preparedStatement.executeQuery();			
			//STEP 5: Extract data from result set			

			if (resultSet.next()) {
				u =  loadNext(connection, resultSet);			
	
			} 

			return u;
			
		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}
	}

	
	@Override
	public List<Usuario> findAll(Connection connection)
			throws DataException {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionManager.getConnection();

			String sql;
			sql =  "SELECT ID_USUARIO, EMAIL, NOMBRE, APELLIDO1, APELLIDO2, PASSWORD, BANCO, TELEFONO, FECHA_NACIMIENTO, NOMBRE_USUARIO, DNI "
					+"FROM USUARIO ";

			// Preparar a query
			System.out.println("Creating statement...");
			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			resultSet = preparedStatement.executeQuery();			
			//STEP 5: Extract data from result set			

			List<Usuario> results = new ArrayList<Usuario>();                        
			Usuario u = null;


			while(resultSet.next()) {
				u = loadNext(connection, resultSet);
				results.add(u);               	
			}

			return results;

		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	
	}


	private Usuario loadNext(Connection connection, ResultSet resultSet) throws SQLException, DataException{

		
		Usuario u = new Usuario();
		int i = 1;
		Long idu = resultSet.getLong(i++);
		String email = resultSet.getString(i++);
		String nombre = resultSet.getString(i++);
		String apellido1 = resultSet.getString(i++);
		String apellido2 = resultSet.getString(i++); 
		String pass = resultSet.getString(i++);
		Double banco = resultSet.getDouble(i++);
		String telefono = resultSet.getString(i++);
		Date fechaNac = resultSet.getDate(i++);
		String nomeUsuario = resultSet.getString(i++);
		String dni = resultSet.getString(i++);
		
		u.setIdUsuario(idu);
		u.setEmail(email);
		u.setNome(nombre);
		u.setApelido1(apellido1);
		u.setApelido2(apellido2);
		u.setPassword(pass);
		u.setBanco(banco);
		u.setTelefono(telefono);
		u.setFechaNacimiento(fechaNac);
		u.setNomeUsuario(nomeUsuario);
		u.setDNI(dni);
				
		Direccion d = direccionDAO.findByUsuario(connection, idu);
		u.setDireccion(d);

		return u;

	}

	@Override
	public Usuario create(Connection connection, Usuario u)
			throws DuplicateInstanceException, DataException{
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {          

			connection = ConnectionManager.getConnection();

			String queryString = "INSERT INTO USUARIO(EMAIL,NOMBRE,APELLIDO1,APELLIDO2,PASSWORD,BANCO,TELEFONO,FECHA_NACIMIENTO,NOMBRE_USUARIO,DNI) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			preparedStatement = connection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);

			int i = 1;     			
			preparedStatement.setString(i++, u.getEmail());
			preparedStatement.setString(i++, u.getNome());
			preparedStatement.setString(i++, u.getApelido1());
			preparedStatement.setString(i++, u.getApelido2());
			preparedStatement.setString(i++, u.getPassword());
			preparedStatement.setDouble(i++, u.getBanco());
			preparedStatement.setString(i++, u.getTelefono());
			preparedStatement.setDate(i++, new java.sql.Date(u.getFechaNacimiento().getTime()));
			preparedStatement.setString(i++, u.getNomeUsuario());
			preparedStatement.setString(i++, u.getDNI());

			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) {
				throw new SQLException("Can not add row to table 'Usuarios'");
			}

			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				Long id = resultSet.getLong(1);
				u.setIdUsuario(id);	
				
				//Creamos a direccion do usuario
				
				Direccion d = u.getDireccion();
				d.setIdUsuario(id);
				direccionDAO.create(connection, d);
				u.setDireccion(d);
				
			} else {
				throw new DataException("Unable to fetch autogenerated primary key");
			}

				
			return u;					

		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);			
		}

	}

	@Override
	public void update(Connection connection, Usuario u)
			throws InstanceNotFoundException, DataException{

		PreparedStatement preparedStatement = null;
		try {          

			connection = ConnectionManager.getConnection();
			
			//Direccion d = u.getDireccion();
			//direccionDAO.delete(connection, d.getId());

			String queryString = "UPDATE USUARIO "
					+ "SET EMAIL = ?, "
					+ "NOMBRE = ?, "
					+ "APELLIDO1 = ?, "
					+ "APELLIDO2 = ?, "
					+ "PASSWORD = ?, "
					+ "BANCO = ?, "
					+ "TELEFONO = ?, "
					+ "FECHA_NACIMIENTO = ?, "
					+ "NOMBRE_USUARIO = ?, "
					+ "DNI = ? "
					+ "WHERE ID_USUARIO= ? ";

			preparedStatement = connection.prepareStatement(queryString);

			int i = 1;     			
			preparedStatement.setString(i++, u.getEmail());
			preparedStatement.setString(i++, u.getNome());
			preparedStatement.setString(i++, u.getApelido1());
			preparedStatement.setString(i++, u.getApelido2());
			preparedStatement.setString(i++, u.getPassword());
			preparedStatement.setDouble(i++, u.getBanco());
			preparedStatement.setString(i++, u.getTelefono());
			preparedStatement.setDate(i++, new java.sql.Date(u.getFechaNacimiento().getTime()));
			preparedStatement.setString(i++, u.getNomeUsuario());
			preparedStatement.setString(i++, u.getDNI());
			preparedStatement.setLong(i++, u.getIdUsuario());


			int updatedRows = preparedStatement.executeUpdate();

			if (updatedRows == 0) {
				throw new InstanceNotFoundException("Non se atopou o usuario "+u.getIdUsuario(), Usuario.class.getName());
			}

			if (updatedRows > 1) {
				throw new SQLException("Duplicate row for id = '" + u.getIdUsuario() + "' in table 'Usuario'");
			}
			
			//d = u.getDireccion();
			//d.setIdUsuario(u.getIdUsuario());
			//d = direccionDAO.create(connection, d);
			//u.setDireccion(d);


		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {
			JDBCUtils.closeStatement(preparedStatement);			
		}


	}

	@Override
	public long delete(Connection connection, Long id)
			throws InstanceNotFoundException, DataException{

		PreparedStatement preparedStatement = null;
		List<Apuesta> apuestas = new ArrayList<Apuesta>();
		
		try {
			connection = ConnectionManager.getConnection();
			
			//Temos que borrar a direccion do usuario e as apuestas
			Direccion d = direccionDAO.findByUsuario(connection, id);
			direccionDAO.delete(connection, d.getId());
			
			apuestas = apuestaDAO.findByUsuario(connection, id);
			
			for(Apuesta a : apuestas) {
				apuestaDAO.delete(connection, a.getIdApuesta());
			}

			String queryString =	
					"DELETE FROM USUARIO " 
					+ "WHERE ID_USUARIO = ? ";


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

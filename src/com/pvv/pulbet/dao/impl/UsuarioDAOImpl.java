package com.pvv.pulbet.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pvv.pulbet.dao.DireccionDAO;
import com.pvv.pulbet.dao.UsuarioDAO;
import com.pvv.pulbet.dao.util.ConnectionManager;
import com.pvv.pulbet.dao.util.JDBCUtils;
import com.pvv.pulbet.exception.DataException;
import com.pvv.pulbet.model.Direccion;
import com.pvv.pulbet.model.Usuario;


public class UsuarioDAOImpl implements UsuarioDAO{
	
	private DireccionDAO direccionDAO = null;

	public UsuarioDAOImpl () {
		direccionDAO = new DireccionDAOImpl();
	}

	@Override
	public Usuario findById(Connection connection, Long id) 
			throws Exception{
		Usuario u = null;

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionManager.getConnection();

				
			String sql;
			sql =  "SELECT ID_USUARIO, EMAIL, NOMBRE, APELLIDO1, APELLIDO2, PASSWORD, BANCO, TELEFONO, FECHA_NACIMIENTO, NOMBRE_USUARIO, DNI, ID_DIRECCION "
					+"FROM USUARIO "
					+"WHERE ID_USUARIO = ? ";

			// Preparar a query
			System.out.println("Creating statement...");
			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Establece os par�metros
			int i = 1;
			preparedStatement.setLong(i++, id);


			resultSet = preparedStatement.executeQuery();			
			//STEP 5: Extract data from result set			

			if (resultSet.next()) {
				u =  loadNext(connection, resultSet);			
				//System.out.println("Cargado "+u);
			} else {
				throw new Exception("Non se atopou usuario con id = "+id);
			}
			if (resultSet.next()) {
				throw new Exception("Empleado con id = "+id+" duplicado");
			}

		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	

		return u;
	}

	@Override
	public Usuario findByEmail(Connection connection, String email) throws Exception {
		
		Usuario u = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{

			connection = ConnectionManager.getConnection();

			String sql;
			sql =    "SELECT ID_USUARIO, EMAIL, NOMBRE, APELLIDO1, APELLIDO2, PASSWORD, BANCO, TELEFONO, FECHA_NACIMIENTO, NOMBRE_USUARIO, DNI, ID_DIRECCION " 
					+" FROM USUARIO "
					+" WHERE "
					+"	UPPER(EMAIL) LIKE ?";


			// Preparar a query
			System.out.println("Creating statement...");
			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Establece os par�metros
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
			throws Exception {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionManager.getConnection();

			String sql;
			sql =  "SELECT ID_USUARIO, EMAIL, NOMBRE, APELLIDO1, APELLIDO2, PASSWORD, BANCO, TELEFONO, FECHA_NACIMIENTO, NOMBRE_USUARIO, DNI, ID_DIRECCION "
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


	private Usuario loadNext(Connection connection, ResultSet resultSet) throws Exception{

		
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
		Long idDireccion = resultSet.getLong(i++);

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
		u.setIdDireccion(idDireccion);
		
		Direccion d = direccionDAO.findByUsuario(connection, idu);
		u.setDireccion(d);

		return u;

	}

	@Override
	public Usuario create(Connection connection, Usuario u)
			throws Exception {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {          

			connection = ConnectionManager.getConnection();

			//Creamos a direccion que logo lle asignaremos o usuario
			direccionDAO.create(connection, u.getDireccion());
			
			String queryString = "INSERT INTO USUARIO(EMAIL,NOMBRE,APELLIDO1,APELLIDO2,PASSWORD,BANCO,TELEFONO,FECHA_NACIMIENTO,NOMBRE_USUARIO,DNI, ID_DIRECCION) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
			preparedStatement.setLong(i++, u.getIdDireccion());

			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) {
				throw new SQLException("Can not add row to table 'Usuarios'");
			}

			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				Long id = resultSet.getLong(1);
				u.setIdUsuario(id);				
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
			throws Exception{

		PreparedStatement preparedStatement = null;
		try {          

			connection = ConnectionManager.getConnection();

			String queryString = "UPDATE USUARIO "
					+ "SET EMAIL = ?, "
					+ "SET NOMBRE_USUARIO = ?, "
					+ "SET APELLIDO1 = ?, "
					+ "SET APELLIDO2 = ?, "
					+ "SET PASSWORD = ?,  "
					+ "SET BANCO = ?, "
					+ "SET TELFONO = ?, "
					+ "SET FECHA_NACIMIENTO = ?,"
					+ "SET NOMBRE_USUARIO = ?,"
					+ "SET DNI = ?"
					+ "SET ID_DIRECCION = ?"
					+ "WHERE ID_USUARIO=?";

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
			preparedStatement.setLong(i++, u.getIdDireccion());


			int updatedRows = preparedStatement.executeUpdate();

			if (updatedRows == 0) {
				throw new Exception("Non se atopou o usuario");
			}

			if (updatedRows > 1) {
				throw new SQLException("Duplicate row for id = '" + u.getIdUsuario() + "' in table 'Usuario'");
			}


		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {
			JDBCUtils.closeStatement(preparedStatement);			
		}


	}

	@Override
	public long delete(Connection connection, Long id)
			throws Exception{

		PreparedStatement preparedStatement = null;

		try {
			connection = ConnectionManager.getConnection();

			String queryString =	
					"DELETE FROM USUARIO " 
					+ "WHERE ID_USUARIO = ? ";


			preparedStatement = connection.prepareStatement(queryString);

			int i = 1;
			preparedStatement.setLong(i++, id);

			long removedRows = preparedStatement.executeUpdate();

			//Temos que borrar a direccion do usuario
			Direccion d = direccionDAO.findByUsuario(connection, id);
			direccionDAO.delete(connection, d.getId());
			
			return removedRows;

		} catch (SQLException e) {
			throw new DataException(e);
		} finally {
			JDBCUtils.closeStatement(preparedStatement);
		}


	}

}

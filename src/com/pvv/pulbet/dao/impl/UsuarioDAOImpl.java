package com.pvv.pulbet.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pvv.pulbet.dao.UsuarioDAO;
import com.pvv.pulbet.dao.util.ConnectionManager;
import com.pvv.pulbet.dao.util.JDBCUtils;
import com.pvv.pulbet.exception.DataException;
import com.pvv.pulbet.model.Usuario;


public class UsuarioDAOImpl implements UsuarioDAO{

	public UsuarioDAOImpl () {

	}

	public Usuario findById(Connection connection, Integer id) 
			throws Exception{
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
				u =  loadNext(resultSet);			
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


	public List<Usuario> findBy(Connection connection, String nome, String ap1, String user)
			throws Exception {	

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{

			connection = ConnectionManager.getConnection();

			String sql;
			sql =    "SELECT ID_USUARIO, EMAIL, NOMBRE, APELLIDO1, APELLIDO2, PASSWORD, BANCO, TELEFONO, FECHA_NACIMIENTO, NOMBRE_USUARIO, DNI " 
					+" FROM USUARIO "
					+" WHERE "
					+"	UPPER(NOMBRE) LIKE ?" 
					+"    and"
					+"    UPPER(APELLIDO1) LIKE ?"
					+"    	and"
					+"    	UPPER(NOMBRE_USUARIO) LIKE ?";

			// Preparar a query
			System.out.println("Creating statement...");
			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Establece os parámetros
			int i = 1;
			preparedStatement.setString(i++, "%"+nome.toUpperCase()+"%");
			preparedStatement.setString(i++, "%"+ap1.toUpperCase()+"%");
			preparedStatement.setString(i++, "%"+user.toUpperCase()+"%");


			resultSet = preparedStatement.executeQuery();			
			//STEP 5: Extract data from result set

			List<Usuario> results = new ArrayList<Usuario>();                        
			Usuario u = null;


			while(resultSet.next()) {
				u = loadNext(resultSet);
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
	
	@Override
	public Usuario findByEmail(Connection connection, String email) throws Exception {
		
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
				u =  loadNext(resultSet);			
	
			} 

			return u;
			
		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}
	}

	public Usuario create(Connection connection, Usuario u)
			throws Exception {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {          

			connection = ConnectionManager.getConnection();
			//Check if the primary key already exists
			//			if (exists(connection, e.getId())) {
			//				throw new Exception("Duplicate employee "+e.getId());
			//			}


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
				throw new SQLException("Can not add row to table 'Employees'");
			}

			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				Long id = resultSet.getLong(1);
				u.setIdUsuario(id);				
			} else {
				throw new DataException("Unable to fetch autogenerated primary key");
			}


			//...
			return u;					

		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);			
		}

	}

	public boolean update(Connection connection, Usuario u)
			throws Exception{

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {          

			connection = ConnectionManager.getConnection();
			//Check if the primary key already exists
			//		if (exists(connection, e.getId())) {
			//			throw new Exception("Duplicate employee "+e.getId());
			//		}


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


			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) 
			{

				throw new SQLException("Can not uppdate row to table 'USUARIO'");

			} 
			else { return true;}

			//...


		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);			
		}


	}

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


			return removedRows;

		} catch (SQLException e) {
			throw new DataException(e);
		} finally {
			JDBCUtils.closeStatement(preparedStatement);
		}


	}

	private Usuario loadNext(ResultSet resultSet) throws Exception{

		
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
		
		
		//Departamento d = departamentoDAO.findByIdEmpleado() temos que crear private departamentoDAO = new departamentoDAO() en departamentoDAO
		// u.setDeptId

		return u;

	}

	public List<Usuario> findAll(Connection connection)
			throws Exception {

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
				u = loadNext(resultSet);
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


}

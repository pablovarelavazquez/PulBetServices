package com.pvv.pulbet.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pvv.pulbet.dao.ApuestaDAO;
import com.pvv.pulbet.dao.DireccionDAO;
import com.pvv.pulbet.dao.UsuarioDAO;
import com.pvv.pulbet.dao.util.JDBCUtils;
import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.DuplicateInstanceException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.Apuesta;
import com.pvv.pulbet.model.Direccion;
import com.pvv.pulbet.model.Evento;
import com.pvv.pulbet.model.Usuario;
import com.pvv.pulbet.service.ApuestaCriteria;
import com.pvv.pulbet.service.Results;
import com.pvv.pulbet.util.PasswordEncryptionUtil;


public class UsuarioDAOImpl implements UsuarioDAO{

	private static Logger logger = LogManager.getLogger(UsuarioDAOImpl.class);
	private DireccionDAO direccionDAO = null;
	private ApuestaDAO apuestaDAO = null;

	public UsuarioDAOImpl () {
		direccionDAO = new DireccionDAOImpl();
		apuestaDAO = new ApuestaDAOImpl();
	}

	@Override
	public Usuario findById(Connection connection, Long id) 
			throws InstanceNotFoundException, DataException{

		if(logger.isDebugEnabled()) {
			logger.debug("Id = {}",id);
		}

		Usuario u = null;

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT ID_USUARIO, EMAIL, NOMBRE, APELLIDO1, APELLIDO2, PASSWORD, BANCO, TELEFONO, FECHA_NACIMIENTO, NOMBRE_USUARIO, DNI "
					+"FROM USUARIO "
					+"WHERE ID_USUARIO = ? ";

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
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	


	}

	@Override
	public Usuario findByEmail(Connection connection, String email) throws DataException {

		if(logger.isDebugEnabled()) {
			logger.debug("EMAIL = {}",email);
		}

		Usuario u = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{

			String sql;
			sql =    "SELECT ID_USUARIO, EMAIL, NOMBRE, APELLIDO1, APELLIDO2, PASSWORD, BANCO, TELEFONO, FECHA_NACIMIENTO, NOMBRE_USUARIO, DNI " 
					+" FROM USUARIO "
					+" WHERE "
					+"	UPPER(EMAIL) LIKE ?";


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
			logger.warn(ex.getMessage(), ex);
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

			String sql;
			sql =  "SELECT ID_USUARIO, EMAIL, NOMBRE, APELLIDO1, APELLIDO2, PASSWORD, BANCO, TELEFONO, FECHA_NACIMIENTO, NOMBRE_USUARIO, DNI "
					+"FROM USUARIO ";

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
			logger.warn(ex.getMessage(), ex);
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

	private void addUpdate(StringBuilder queryString, boolean first, String clause) {
		queryString.append(first?" SET ": " , ").append(clause);
	}

	@Override
	public Usuario create(Connection connection, Usuario u)
			throws DuplicateInstanceException, DataException{

		if(logger.isDebugEnabled()) {
			logger.debug("Usuario: id:{}, nome:{}, email:{}, apelidos:{} {}, banco:{}, dni:{}, fecha:{}, nomeUsuario:{}, password{}, direccion{}"
					,u.getIdUsuario(), u.getNome(), u.getEmail(), u.getApelido1(), u.getApelido2(), u.getBanco(), u.getDNI(), 
					u.getFechaNacimiento(), u.getNomeUsuario(), (u.getPassword()==null), u.getDireccion());
		}

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {          

			String queryString = "INSERT INTO USUARIO(EMAIL,NOMBRE,APELLIDO1,APELLIDO2,PASSWORD,TELEFONO,FECHA_NACIMIENTO,NOMBRE_USUARIO,DNI)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

			preparedStatement = connection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);

			int i = 1;     			
			preparedStatement.setString(i++, u.getEmail());
			preparedStatement.setString(i++, u.getNome());
			preparedStatement.setString(i++, u.getApelido1());
			preparedStatement.setString(i++, u.getApelido2());
			preparedStatement.setString(i++, PasswordEncryptionUtil.encryptPassword(u.getPassword()));
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
				d = direccionDAO.create(connection, d);
				u.setDireccion(d);

			} else {
				throw new DataException("Unable to fetch autogenerated primary key");
			}


			return u;					

		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);			
		}

	}

	@Override
	public void update(Connection connection, Usuario u)
			throws InstanceNotFoundException, DataException{

		if(logger.isDebugEnabled()) {
			logger.debug("Usuario: id:{}, nome:{}, email:{}, apelidos:{} {}, banco:{}, dni:{}, fecha:{}, nomeUsuario:{}, password{}, direccion{}"
					,u.getIdUsuario(), u.getNome(), u.getEmail(), u.getApelido1(), u.getApelido2(), u.getBanco(), u.getDNI(), 
					u.getFechaNacimiento(), u.getNomeUsuario(), (u.getPassword()==null), u.getDireccion());
		}

		PreparedStatement preparedStatement = null;
		StringBuilder queryString = null;
		try {          

			queryString = new StringBuilder(
					" UPDATE USUARIO" 
					);

			boolean first = true;

			if (u.getEmail()!=null) {
				addUpdate(queryString, first, " EMAIL = ? ");
				first = false;
			}
			
			if (u.getNome()!=null) {
				addUpdate(queryString, first, " NOMBRE = ? ");
				first = false;
			}
			
			if (u.getApelido1()!=null) {
				addUpdate(queryString, first, " APELLIDO1 = ? ");
				first = false;
			}
			
			if (u.getApelido2()!=null) {
				addUpdate(queryString, first, " APELLIDO2 = ? ");
				first = false;
			}
			
			if (u.getPassword()!=null) {
				addUpdate(queryString, first, " PASSWORD = ? ");
				first = false;
			}
			
			if (u.getBanco()!=null) {
				addUpdate(queryString, first, " BANCO = ? ");
				first = false;
			}
			
			if (u.getTelefono()!=null) {
				addUpdate(queryString, first, " TELEFONO = ? ");
				first = false;
			}
			
			if (u.getFechaNacimiento()!=null) {
				addUpdate(queryString, first, " FECHA_NACIMIENTO = ? ");
				first = false;
			}
			
			if (u.getNomeUsuario()!=null) {
				addUpdate(queryString, first, " NOMBRE_USUARIO = ? ");
				first = false;
			}
			
			if (u.getDNI()!=null) {
				addUpdate(queryString, first, " DNI = ? ");
				first = false;
			}
			
			
				queryString.append(" WHERE ID_USUARIO= ? ");

			preparedStatement = connection.prepareStatement(queryString.toString());

			int i = 1;     		
			if(u.getEmail()!=null)
					preparedStatement.setString(i++, u.getEmail());
			
			if(u.getNome()!=null)
					preparedStatement.setString(i++, u.getNome());
			
			if(u.getApelido1()!=null)
					preparedStatement.setString(i++, u.getApelido1());
			
			if(u.getApelido2()!=null)
					preparedStatement.setString(i++, u.getApelido2());
			
			if(u.getPassword()!=null)
					preparedStatement.setString(i++,PasswordEncryptionUtil.encryptPassword(u.getPassword()));
			
			if(u.getBanco()!=null)
					preparedStatement.setDouble(i++, u.getBanco());
			
			if(u.getTelefono()!=null)
					preparedStatement.setString(i++, u.getTelefono());
			
			if(u.getFechaNacimiento()!=null)
					preparedStatement.setDate(i++, new java.sql.Date(u.getFechaNacimiento().getTime()));
			
			if(u.getNomeUsuario()!=null)
					preparedStatement.setString(i++, u.getNomeUsuario());
			
			if(u.getDNI()!=null)
					preparedStatement.setString(i++, u.getDNI());
			
					preparedStatement.setLong(i++, u.getIdUsuario());


			int updatedRows = preparedStatement.executeUpdate();

			if (updatedRows == 0) {
				throw new InstanceNotFoundException("Non se atopou o usuario "+u.getIdUsuario(), Usuario.class.getName());
			}

			if (updatedRows > 1) {
				throw new SQLException("Duplicate row for id = '" + u.getIdUsuario() + "' in table 'Usuario'");
			}



		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		} finally {
			JDBCUtils.closeStatement(preparedStatement);			
		}


	}

	@Override
	public long delete(Connection connection, Long id)
			throws InstanceNotFoundException, DataException{

		if(logger.isDebugEnabled()) {
			logger.debug("ID = {}",id);
		}

		PreparedStatement preparedStatement = null;

		try {

			//Temos que borrar a direccion do usuario e as apuestas
			Direccion d = direccionDAO.findByUsuario(connection, id);
			direccionDAO.delete(connection, d.getId());
			
			Results<Apuesta> results = null;
			int startIndex = 1;
			int count = 5;
			int i = 1;
			
			do {
				results = apuestaDAO.findByUsuario(connection, id, startIndex, count);
				if (results.getPage().size()>0) {
					for (Apuesta a: results.getPage()) {
						apuestaDAO.delete(connection, a.getIdApuesta());
						i++;
					}
					startIndex = startIndex + count;
				}

			} while (!(results.getPage().size()<count));

			String queryString =	
					"DELETE FROM USUARIO " 
							+ "WHERE ID_USUARIO = ? ";


			preparedStatement = connection.prepareStatement(queryString);

			i = 1;
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

}

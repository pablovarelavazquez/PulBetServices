package com.pvv.pulbet.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsuarioDatabaseTest {
	public static void main (String[] args) {

		try {
			String jdbc = "jdbc:mysql://localhost:3306/pulbet?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

			Connection conexion = DriverManager.getConnection(jdbc,"root","abc123.");
			//			System.out.println("Estoy dentro");
			Statement statement = conexion.createStatement();
			ResultSet set = statement.executeQuery("SELECT ID_USUARIO, EMAIL, NOME, APELIDO1, APELIDO2, CONTRASINAL, BANCO, TELEFONO, FECHA_NACIMIENTO, NOME_USUARIO, DNI FROM usuario");

			while (set.next()) {
				
				Usuario u = new Usuario();
				 u.setIdUsuario(set.getLong("ID_USUARIO"));
				 u.setEmail(set.getString("EMAIL"));
				 u.setNome(set.getString("NOME"));
				 u.setApelido1(set.getString("APELIDO1"));
				 u.setApelido2(set.getString("APELIDO2"));
				 u.setPassword(set.getString("CONTRASINAL"));
				 u.setBanco(set.getDouble("BANCO"));
				 u.setTelefono(set.getString("TELEFONO"));
				 u.setFechaNacimiento(set.getDate("FECHA_NACIMIENTO"));
				 u.setNomeUsuario(set.getString("NOME_USUARIO"));
				 u.setDNI(set.getString("DNI"));

				 System.out.println(u);
			}
			set.close();
			statement.close();

			if (conexion != null) {
				conexion.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();			
		}
	}
}

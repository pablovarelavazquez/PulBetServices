package com.pvv.pulbet.model;

import java.sql.*;

public class ConnectionDatabaseTest {
	

	public static void main (String[] args) {
		
		try {
			String jdbc = "jdbc:mysql://localhost:3306/rrhh?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			
			Connection conexion = DriverManager.getConnection(jdbc,"root","abc123.");
			System.out.println("Estoy dentro");
			Statement statement = conexion.createStatement();
			ResultSet set = statement.executeQuery("SELECT * FROM empleado");
			
			while (set.next()) {
				int idEmpleado = set.getInt("idEmpleado");
				String nome = set.getString("nome");
				System.out.println("Empleado "+idEmpleado+ " nome "+nome);
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

package com.pvv.pulbet.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class ApuestaDatabaseTest {

	public static void main (String[] args) {

		try {
			String jdbc = "jdbc:mysql://localhost:3306/pulbet?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

			Connection conexion = DriverManager.getConnection(jdbc,"root","abc123.");
			//			System.out.println("Estoy dentro");
			Statement statement = conexion.createStatement();
			ResultSet set = statement.executeQuery("SELECT ID_APUESTA, IMPORTE, ID_USUARIO, FECHA FROM apuesta");

			while (set.next()) {
				
				Apuesta a = new Apuesta();
				a.setIdApuesta(set.getLong("ID_APUESTA"));
				a.setImporte(set.getDouble("IMPORTE"));
				a.setIdUsuario(set.getLong("ID_USUARIO"));
				a.setFecha(set.getDate("FECHA"));
				
				System.out.println(a);
				
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

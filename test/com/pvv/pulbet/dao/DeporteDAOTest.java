package com.pvv.pulbet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.pvv.pulbet.dao.impl.DeporteDAOImpl;
import com.pvv.pulbet.dao.util.ConnectionManager;
import com.pvv.pulbet.dao.util.JDBCUtils;
//import com.pvv.pulbet.exception.DataException;
import com.pvv.pulbet.model.Deporte;
import com.pvv.pulbet.model.Usuario;

public class DeporteDAOTest {

	private DeporteDAO dao = null;
	
	public DeporteDAOTest() {
		dao = new DeporteDAOImpl();
	}
	
	public void testCreate() 
			throws Exception{

		Deporte d = new Deporte();
		d.setNome("PRUEBA");

//		dao.create(d);
		System.out.println(d);

	}
	
	public void testDelete()
			throws Exception{
		long iddd;
		long idd = 12;
//		iddd = dao.delete(idd);
		System.out.println("Se elimino el deporte con id "+idd);

	}
	
	public void testFindAll() 
			throws Exception{
		List<Deporte> results = null;

//		results = dao.findAll();

		for (Deporte d: results) {
			System.out.println(d);
		}
	}
	

	public void testFindByNombre() 
			throws Exception {
//		List<Deporte> lista = dao.findByNombre("a");
//		for (Deporte d: lista) {
//			System.out.println(d);
//		}

	}
	
	public static void main(String[] args) {
		try {
			DeporteDAOTest test = new DeporteDAOTest();
			//test.testFindById();
			test.testFindByNombre();
			//test.testCreate();
			//falta update
			//test.testFindAll();
			//test.testDelete();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}

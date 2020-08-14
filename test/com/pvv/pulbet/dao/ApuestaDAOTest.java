package com.pvv.pulbet.dao;

import java.util.Date;
import java.util.List;

import com.pvv.pulbet.dao.impl.ApuestaDAOImpl;
import com.pvv.pulbet.model.Apuesta;
import com.pvv.pulbet.model.Usuario;


public class ApuestaDAOTest {

	private ApuestaDAO dao = null;

	public ApuestaDAOTest() {
		dao = new ApuestaDAOImpl();		
	}

	public void testFindById() 
			throws Exception {
//		Apuesta a = dao.findById(5);
//		System.out.println(a);
	}

	public void testFindByUsuario() 
			throws Exception{
		List<Apuesta> results = null;

//		results = dao.findByUsuario(2);

		for (Apuesta a: results) {
			System.out.println(a);
		}

	}


	public void testCreate() 
			throws Exception{

		Apuesta a = new Apuesta();
		a.setImporte(10.0d);
		a.setIdUsuario(2l);
		a.setFecha(new Date());

//		dao.create(a);
//		System.out.println(a);

	}

	public void testFindAll()
			throws Exception{
		List<Apuesta> results = null;

//		results = dao.findAll();

		for (Apuesta a: results) {
			System.out.println(a);
		}
	}


	public static void main(String[] args) {
		try {
			ApuestaDAOTest test = new ApuestaDAOTest();
			//test.testFindById();
			//test.testFindByUsuario();
			//test.testCreate();
			test.testFindAll();
		} catch (Exception e) {
			e.printStackTrace();
		}



	}


}

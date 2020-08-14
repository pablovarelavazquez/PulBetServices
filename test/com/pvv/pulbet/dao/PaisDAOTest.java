package com.pvv.pulbet.dao;

import java.util.List;

import com.pvv.pulbet.dao.impl.PaisDAOImpl;
import com.pvv.pulbet.model.Apuesta;
import com.pvv.pulbet.model.Pais;
import com.pvv.pulbet.model.Usuario;

public class PaisDAOTest {
	
	private PaisDAO dao = null;
	
	public PaisDAOTest() {
		dao = new PaisDAOImpl();
	}
	
	public void testFindById() 
			throws Exception {
		Pais p = dao.findById(null,34, "");
		System.out.println(p);
	}
	
	public void testFindAll() 
			throws Exception{
		List<Pais> results = null;

		results = dao.findAll(null, "");

		for (Pais p: results) {
			System.out.println(p);
		}
	}
		
	public void testFindByNombre() 
			throws Exception{
		List<Pais> results = null;

		results = dao.findByNombre(null, "pO", "");

		for (Pais p: results) {
			System.out.println(p);
		}

	}
	
	public static void main(String[] args) {
		try {
			PaisDAOTest test = new PaisDAOTest();
			//test.testFindById(); //ok
			test.testFindByNombre(); //ok
			//test.testFindAll(); //ok

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

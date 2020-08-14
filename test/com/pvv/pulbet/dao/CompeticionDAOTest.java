package com.pvv.pulbet.dao;

import java.util.Date;
import java.util.List;

import com.pvv.pulbet.dao.impl.CompeticionDAOImpl;
import com.pvv.pulbet.model.Apuesta;
import com.pvv.pulbet.model.Competicion;
import com.pvv.pulbet.model.Usuario;

public class CompeticionDAOTest {

	private CompeticionDAO dao = null;

	public CompeticionDAOTest() {
		dao = new CompeticionDAOImpl();		
	}

	public void testFindById() 
			throws Exception {
//		Competicion c = dao.findById(5l);
//		System.out.println(c);
	}

	public void testFindByDeporte() 
			throws Exception{
		List<Competicion> results = null;

//		results = dao.findByDeporte(2l);

		for (Competicion c: results) {
			System.out.println(c);
		}

	}


	public void testCreate() 
			throws Exception{

		Competicion c = new Competicion();
		c.setNome("PRUEBA");
		c.setIdDeporte(1l);
		c.setFechaInicio(new Date());
		c.setFechaFin(new Date());

//		dao.create(c);
		System.out.println(c);

	}

	public void testFindAll()
			throws Exception{
		List<Competicion> results = null;

//		results = dao.findAll();

		for (Competicion c: results) {
			System.out.println(c);
		}
	}
	
	public void testFindByNombre() 
			throws Exception {
//		List<Competicion> lista = dao.findByNombre("a");
//		for (Competicion c:lista) {
//			System.out.println(c);
//		}

	}


	public static void main(String[] args) {
		try {
			CompeticionDAOTest test = new CompeticionDAOTest();
			//test.testFindById();
			//test.testFindByDeporte();
			//test.testCreate(); falla polas fechas
			//falta update e delete.
			test.testFindAll();
			test.testFindByNombre();
		} catch (Exception e) {
			e.printStackTrace();
		}



	}

}

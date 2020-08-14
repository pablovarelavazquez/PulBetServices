package com.pvv.pulbet.dao;

import java.util.List;

import com.pvv.pulbet.dao.impl.LineaApuestaDAOImpl;
import com.pvv.pulbet.model.Apuesta;
import com.pvv.pulbet.model.LineaApuesta;
import com.pvv.pulbet.model.LineaApuestaId;
import com.pvv.pulbet.model.Usuario;

public class LineaApuestaDAOTest {

	private LineaApuestaDAO dao = null;

	public LineaApuestaDAOTest() {
		dao = new LineaApuestaDAOImpl();		
	}
	
	public void testCreate() 
			throws Exception{

		LineaApuesta l = new LineaApuesta();
		
		l.setNumLinea(2);
		l.setIdApuesta(2l);
		l.setIdResultado(8l);
		l.setIdEvento(13l);

		dao.create(null, null);
		System.out.println(l);

	}
	
	public void testDelete()
			throws Exception{
		
		LineaApuestaId id = new LineaApuestaId();
		id.setIdApuesta(2l);
		id.setNumLinea(2);
		
		dao.delete(null, id);
		System.out.println("Se elimino la linea de apuesta con id -> NumeroLinea: "+id.getNumLinea()+" y IdAPuesta: "+id.getIdApuesta());

	}
	
	public void testFindById() 
			throws Exception {
		
		LineaApuestaId id = new LineaApuestaId();
		id.setIdApuesta(1l);
		id.setNumLinea(2);	
		
		LineaApuesta l = dao.findById(null, id);
		System.out.println(l);
	}
	
	public void testFindAll() 
			throws Exception{
		List<LineaApuesta> results = null;

		results = dao.findAll(null);

		for (LineaApuesta l: results) {
			System.out.println(l);
		}
	}
	
	public void testFindByApuesta() 
			throws Exception{
		List<LineaApuesta> results = null;

		results = dao.findByApuesta(null, 1l);

		for (LineaApuesta l: results) {
			System.out.println(l);
		}

	}
	
	public static void main(String[] args) {
		try {
			LineaApuestaDAOTest test = new LineaApuestaDAOTest();
			//test.testFindById(); //ok
			//test.testFindByApuesta(); ok
			//test.testCreate(); //ok
			//test.testDelete(); //ok
			//test.testFindAll(); //ok
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}

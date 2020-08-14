package com.pvv.pulbet.dao;

import java.util.Date;
import java.util.List;

import com.pvv.pulbet.dao.impl.EventoDAOImpl;
import com.pvv.pulbet.model.Evento;
import com.pvv.pulbet.model.Usuario;

public class EventoDAOTest {

	private EventoDAO dao = null;

	public EventoDAOTest() {
		dao = new EventoDAOImpl();		
	}

	public void testCreate() 
			throws Exception{

		Evento e = new Evento();
		e.setFecha(new Date());
		e.setIdCompeticion(1l);

		dao.create(null, e);
		System.out.println(e);

	}

	public void testDelete()
			throws Exception{
		long iddd;
		long idd = 14;
		iddd = dao.delete(null, idd);
		System.out.println("Se elimino el usuario con id "+idd);

	}

	public void testFindAll() 
			throws Exception{
		List<Evento> results = null;

//		results = dao.findAll();

		for (Evento e: results) {
			System.out.println(e);
		}
	}


	public static void main(String[] args) {
		try {
			EventoDAOTest test = new EventoDAOTest();
			//test.testCreate(); ok
			//test.testDelete(); ok
			test.testFindAll();
			//falta update
		} catch (Exception e) {
			e.printStackTrace();
		}



	}
}

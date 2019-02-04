package com.pvv.pulbet.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.DuplicateInstanceException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.Apuesta;
import com.pvv.pulbet.model.LineaApuesta;
import com.pvv.pulbet.service.impl.ApuestaServiceImpl;

public class ApuestaServiceTest {

	private ApuestaService apuestaService = null;
	
	public ApuestaServiceTest() {
		apuestaService = new ApuestaServiceImpl();
	}

	public void testFindByUsuario() 
			throws Exception {
		List<Apuesta> lista = apuestaService.findByUsuario(3l);

		for (Apuesta a : lista) {
			System.out.println(a);
		}
	}


	public void testDelete() throws InstanceNotFoundException, DataException {
		apuestaService.delete(12l);
	}

	public void testHistorial() throws DataException {

		ApuestaCriteria a = new ApuestaCriteria();
		a.setIdUsuario(1l);

		List<Apuesta> result = new ArrayList<Apuesta>();
		result = apuestaService.findHistorial(a, null);

		for(Apuesta ap: result) {
			System.out.println(ap);
		}

	}

	public void testOpenBets() throws DataException {

		ApuestaCriteria a = new ApuestaCriteria();
		a.setIdUsuario(1l);

		List<Apuesta> result = new ArrayList<Apuesta>();
		result = apuestaService.findOpenBets(a, null);

		for(Apuesta ap: result) {
			System.out.println(ap);
		}

	}

	public void testCreate() throws DuplicateInstanceException, DataException {
		
		Apuesta a = new Apuesta();
		a.setIdUsuario(3l);
		a.setFecha(new Date());
		a.setImporte(10.0d);
		
		int cont = 1;
		
		LineaApuesta la = new LineaApuesta();
		la.setIdApuesta(a.getIdApuesta());
		la.setNumLinea(cont++);
		la.setIdEvento(6l);
		la.setIdResultado(5l);
		
		LineaApuesta la1 = new LineaApuesta();
		la1.setIdApuesta(a.getIdApuesta());
		la1.setNumLinea(cont++);
		la1.setIdEvento(13l);
		la1.setIdResultado(7l);
		
		List<LineaApuesta> lineas  = new ArrayList<LineaApuesta>();
		lineas.add(la);
		lineas.add(la1);
		
		a.setLineas(lineas);
		
		a = apuestaService.create(a);
		
		System.out.println(a);
				
	}
	
	public void comprobar() throws InstanceNotFoundException, DataException {
		
		Apuesta a = apuestaService.findById(14l);
		a = apuestaService.comprobarApuesta(a);
		
		System.out.println(a);
	}


	public static void main(String args[]){

		ApuestaServiceTest test = new ApuestaServiceTest();

		try {
			//test.testFindByUsuario(); //OK
			//test.testDelete();
			//test.testHistorial();; //OK
			//test.testOpenBets();; //OK
			//test.testCreate(); //OK
			//test.comprobar(); //OK

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

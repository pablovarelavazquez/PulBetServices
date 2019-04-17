package com.pvv.pulbet.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

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
		Results<Apuesta> lista = apuestaService.findByUsuario(3l,1,10);

		for (Apuesta a : lista.getPage()) {
			System.out.println(a);
		}
	}


	public void testDelete() throws InstanceNotFoundException, DataException {
		apuestaService.delete(12l);
	}

	public void testHistorial() throws DataException {

		ApuestaCriteria a = new ApuestaCriteria();
		a.setIdUsuario(11l);

		Results<Apuesta> result = apuestaService.findHistorial(a,1,10);

		for(Apuesta ap: result.getPage()) {
			System.out.println(ap);
		}

	}

	public void testOpenBets() throws DataException {

		ApuestaCriteria a = new ApuestaCriteria();
		a.setIdUsuario(1l);

		Results<Apuesta> result = apuestaService.findOpenBets(a,1,10);

		for(Apuesta ap: result.getPage()) {
			System.out.println(ap);
		}

	}

	public void testCreate() throws DuplicateInstanceException, DataException {
		
		Apuesta a = new Apuesta();
		a.setIdUsuario(3l);
		a.setFecha(new Date());
		a.setImporte(10.0d);
		a.setGanancias(10.0);
		
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
		
		Apuesta a = apuestaService.findById(16l);
		a = apuestaService.comprobarApuesta(a);
		
		System.out.println(a);
	}
	
	public void testFindAll() throws DataException {
		List<Apuesta> apuestas = apuestaService.findAll();
		
		for(Apuesta a : apuestas) {
			System.out.println(a);
		}
		
	}


	public static void main(String args[]){

		ApuestaServiceTest test = new ApuestaServiceTest();

		try {
			//test.testFindByUsuario(); //OK
			//test.testDelete();
			//test.testHistorial();; //OK
			//System.out.println("---------------------------------------------------");
			//test.testOpenBets();; //OK
			//test.testCreate(); //OK
			//test.comprobar(); //OK
			
			test.testFindAll();
			
//			Properties systemPropierties = System.getProperties();
//			String key = null;
//			for (Enumeration keys = systemPropierties.keys(); keys.hasMoreElements();) {
//				key = (String) keys.nextElement();
//				System.out.println(key+"="+System.getProperty(key));
//			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

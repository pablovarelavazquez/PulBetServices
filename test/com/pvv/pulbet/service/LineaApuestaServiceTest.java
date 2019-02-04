package com.pvv.pulbet.service;

import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.LineaApuesta;
import com.pvv.pulbet.service.impl.LineaApuestaServiceImpl;

public class LineaApuestaServiceTest {
	
	LineaApuestaService lineaService = null;
	
	public LineaApuestaServiceTest() {
		lineaService =  new LineaApuestaServiceImpl();
	}
	
	public void testUpdate() throws InstanceNotFoundException, DataException {
		LineaApuesta la = new LineaApuesta();
		la.setIdApuesta(13l);
		la.setNumLinea(1);
		la.setIdEvento(6l);
		la.setIdResultado(5l);
		la.setProcesado(1);
		
		la = lineaService.update(la);
		System.out.println(la);
	}
	
	public void testComprobar() throws DataException {
		LineaApuesta la = new LineaApuesta();
		la.setIdApuesta(13l);
		la.setNumLinea(1);
		la.setIdEvento(6l);
		la.setIdResultado(5l);
		
		la = lineaService.comprobarLinea(la);
		System.out.println(la);
	}
	
	public static void main(String[] args) {
		LineaApuestaServiceTest test = new LineaApuestaServiceTest();

		try {
			//test.testUpdate(); //OK
			//test.testComprobar(); //OK
 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

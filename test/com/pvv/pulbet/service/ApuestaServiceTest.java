package com.pvv.pulbet.service;

import java.util.List;

import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.Apuesta;
import com.pvv.pulbet.service.impl.ApuestaServiceImpl;

public class ApuestaServiceTest {
	
	private ApuestaService apuestaService = null;
	
	public ApuestaServiceTest() {
		apuestaService = new ApuestaServiceImpl();
	}

	public void testFindByUsuario() 
			throws Exception {
		List<Apuesta> lista = apuestaService.findByUsuario(1l);
		
		for (Apuesta a : lista) {
			System.out.println(a);
		}
	}

	
	public void testDelete() throws InstanceNotFoundException, DataException {
		apuestaService.delete(5l);
	}

	

	public static void main(String args[]){

		ApuestaServiceTest test = new ApuestaServiceTest();

		try {
			//test.testFindByUsuario(); //OK
			test.testDelete();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

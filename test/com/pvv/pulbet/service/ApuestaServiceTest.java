package com.pvv.pulbet.service;

import java.util.List;

import com.pvv.pulbet.model.Apuesta;
import com.pvv.pulbet.service.impl.ApuestaServiceImpl;

public class ApuestaServiceTest {
	
	private ApuestaService apuestaService = null;
	
	public ApuestaServiceTest() {
		apuestaService = new ApuestaServiceImpl();
	}

	public void testFindById() 
			throws Exception {
		List<Apuesta> lista = apuestaService.findByUsuario(1l);
		
		for (Apuesta a : lista) {
			System.out.println(a);
		}
	}

	

	public static void main(String args[]){

		ApuestaServiceTest test = new ApuestaServiceTest();

		try {
			test.testFindById();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

package com.pvv.pulbet.service;

import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.Deporte;
import com.pvv.pulbet.service.impl.DeporteServiceImpl;

public class DeporteServiceTest {
	
	private DeporteService deporteService = null;
	
	public DeporteServiceTest() {
		deporteService = new DeporteServiceImpl();
	}
	
	
	public Deporte testFindById() throws InstanceNotFoundException, DataException {
		return deporteService.findById(1l, "ESP");
	}
	
	public static void main(String args[]){

		DeporteServiceTest test = new DeporteServiceTest();

		try {
			test.testFindById();
			
			test.testFindById();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

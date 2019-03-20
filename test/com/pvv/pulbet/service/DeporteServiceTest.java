package com.pvv.pulbet.service;

import java.util.List;

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
	
	public void testFindAll() throws DataException{
		List<Deporte> deportes = deporteService.findAll("ESP");
		
		for (Deporte d : deportes) {
			System.out.println(d);
		}
	}
	
	public static void main(String args[]){

		DeporteServiceTest test = new DeporteServiceTest();

		try {
			//test.testFindById();
			test.testFindAll();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

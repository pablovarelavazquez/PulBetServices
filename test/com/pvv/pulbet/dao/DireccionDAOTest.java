package com.pvv.pulbet.dao;

import java.util.Date;
import java.util.List;

import com.pvv.pulbet.dao.impl.DireccionDAOImpl;
import com.pvv.pulbet.model.Apuesta;
import com.pvv.pulbet.model.Direccion;
import com.pvv.pulbet.model.Usuario;

public class DireccionDAOTest {

	private DireccionDAO dao = null;

	public DireccionDAOTest() {
		dao = new DireccionDAOImpl();		
	}
	
	public void testCreate() 
			throws Exception{

		Direccion d = new Direccion();
		d.setIdUsuario(1l);
		d.setCiudad("Chantada");
		d.setIdProvincia(27l);
		d.setCalle("La del al lado de aqui");
		d.setNumero(131);
		d.setCodPostal(27500);
		d.setPiso(3);
		d.setLetra("O");

//		dao.create(d);
		System.out.println(d);

	}
	
	public void testDelete()
			throws Exception{
		long iddd;
		long idd = 12;
//		iddd = dao.delete(idd);
		System.out.println("Se elimino la direccion con id "+idd);

	}
	
	public void testFindByUsuario() 
			throws Exception{
		List<Direccion> results = null;

//		results = dao.findByUsuario(2);

		for (Direccion d: results) {
			System.out.println(d);
		}

	}
	
	public void testFindAll() 
			throws Exception{
		List<Direccion> results = null;

//		results = dao.findAll();

		for (Direccion d: results) {
			System.out.println(d);
		}
	}
	
	public void testFindById() 
			throws Exception {
//		Direccion d = dao.findById(5);
//		System.out.println(d);
	}
	
	
	public static void main(String[] args) {
		try {
			DireccionDAOTest test = new DireccionDAOTest();
			//test.testFindById(); ok
			//test.testFindByUsuario(); ok
			//test.testCreate(); ok
			//test.testFindAll(); ok
			//falta update
			//test.testDelete(); ok
		} catch (Exception e) {
			e.printStackTrace();
		}



	}
}

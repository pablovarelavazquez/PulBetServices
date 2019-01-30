package com.pvv.pulbet.service;

import java.util.Date;

import com.pvv.pulbet.model.Direccion;
import com.pvv.pulbet.model.Usuario;
import com.pvv.pulbet.service.impl.UsuarioServiceImpl;

public class UsuarioServiceTest {

	private UsuarioService service = null;

	public UsuarioServiceTest() {
		service = new UsuarioServiceImpl();
	}

	public void testFindById() 
			throws Exception {
		Usuario u = service.findById(5l);
		System.out.println(u);
	}

	public void testCreate() 
			throws Exception{
		
		Direccion d = new Direccion();
		d.setCalle("Calle la prueba");
		d.setCiudad("PruebaCity");
		d.setCodPostal(27700);
		d.setIdProvincia(27l);
		d.setLetra("A");
		d.setNumero(11);
		d.setPiso(3);
		
		Usuario u = new Usuario();
		u.setEmail("prueba@estovaii.com");
		u.setNome("Pepe");
		u.setApelido1("Prueba");
		u.setApelido2("Probando");
		u.setPassword("hola");
		u.setBanco(10.0d);
		u.setTelefono("982440577");
		u.setFechaNacimiento(new Date());
		u.setNomeUsuario("pruebita1");
		u.setDNI("76192887X");
		u.setDireccion(d);
		
		service.create(u);
		System.out.println(u);

	}
	
	public void testLogin() throws Exception{
		service.login("prueba@estovaii.com", "hola");
	}

	public static void main(String args[]){
		
		UsuarioServiceTest test = new UsuarioServiceTest();

		try {
			//test.testFindById(); //ok
			test.testCreate(); //ok
			//test.testLogin(); //ok
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

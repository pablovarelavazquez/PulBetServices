package com.pvv.pulbet.service;

import java.util.Date;

import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.InstanceNotFoundException;
import com.pvv.pulbet.model.Direccion;
import com.pvv.pulbet.model.Usuario;
import com.pvv.pulbet.service.impl.BancoServiceImpl;
import com.pvv.pulbet.service.impl.UsuarioServiceImpl;

public class UsuarioServiceTest {

	private UsuarioService usuarioService = null;
	private BancoService bancoService = null;
	
	public UsuarioServiceTest() {
		usuarioService = new UsuarioServiceImpl();
		bancoService = new BancoServiceImpl();
	}

	public void testFindById() 
			throws Exception {
		Usuario u = usuarioService.findById(5l);
		System.out.println(u);
	}

	public void testCreate() 
			throws Exception{

		Direccion d = new Direccion();
		d.setCalle("Rua Xoan XXVIII");
		d.setCiudad("Chantada");
		d.setCodPostal(27500);
		d.setIdProvincia(27l);
		//d.setLetra();
		d.setNumero(35);
		//d.setPiso(4);

		Usuario u = new Usuario();
		u.setEmail("kk@gmail.com");
		u.setNome("Encriptado");
		u.setApelido1("Encriptando");
		u.setApelido2("Amoaveh");
		u.setPassword("abc123.");
		//u.setBanco(10.0d);
		u.setTelefono("670897654");
		u.setFechaNacimiento(new Date());
		u.setNomeUsuario("prueba");
		u.setDNI("11111222J");
		u.setDireccion(d);

		usuarioService.create(u);
		System.out.println(u);

	}

	public void testLogin() throws Exception{
		usuarioService.login("pruebaencriptada@vai.com", "abc123.");
	}

	public void testUpdate() throws Exception{


		Usuario u = new Usuario();
		u.setIdUsuario(26l);
		u.setEmail("prueba@foi.com");
		u.setNome("Pepee");
		u.setApelido1("Pruebaa");
		u.setApelido2("Pruebame");
		u.setPassword("aloo");
		u.setBanco(10.0d);
		u.setTelefono("982441122");
		u.setFechaNacimiento(new Date());
		u.setNomeUsuario("pruebame");
		u.setDNI("76192887W");

		usuarioService.update(u);

	}
	
	public void testUpdateDireccion() throws InstanceNotFoundException, DataException {

		Usuario u = usuarioService.findById(26l);
		
		Direccion d = new Direccion();
		d.setId(18l);
		d.setCalle("Calle foi");
		d.setCiudad("Foi");
		d.setCodPostal(27702);
		d.setIdProvincia(27l);
		d.setLetra("B");
		d.setNumero(12);
		d.setPiso(4);
		d.setIdUsuario(26l);

		usuarioService.editDireccion(d, u);
		
	}
	
	public void testIngresar() throws DataException {
		bancoService.ingresar(5l,99.99d);
	}
	
	public void testRetirar() throws DataException {
		bancoService.retirar(4l,0.99d);
	}
	
	public void testDelete() throws DataException {
		usuarioService.delete(11l);
	}
	
	public void testDeleteApuestas() {
		
	}

	public static void main(String args[]){

		UsuarioServiceTest test = new UsuarioServiceTest();

		try {

			//test.testFindById(); //ok
			test.testCreate(); //ok
			//test.testLogin(); //ok
			//test.testUpdate(); //ok --cambia datos de usuario, non direccion
			//test.testUpdateDireccion(); //ok --cambia direccion
			//test.testIngresar(); //ok
			//test.testRetirar();	//ok
			//test.testDelete(); //ok

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

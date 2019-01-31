package com.pvv.pulbet.service;

import java.util.Date;

import com.pvv.pulbet.exceptions.DataException;
import com.pvv.pulbet.exceptions.DuplicateInstanceException;
import com.pvv.pulbet.exceptions.MailException;
import com.pvv.pulbet.model.Direccion;
import com.pvv.pulbet.model.Usuario;
import com.pvv.pulbet.service.impl.UsuarioServiceImpl;

public class MultipleServiceTest {
	
	private UsuarioService usuarioService = null;

	public MultipleServiceTest() {
		usuarioService = new UsuarioServiceImpl();
	}
	
	public Usuario create() throws DuplicateInstanceException, MailException, DataException {
		Direccion d = new Direccion();
		d.setCalle("Calle la prueba2");
		d.setCiudad("PruebaCity2");
		d.setCodPostal(27702);
		d.setIdProvincia(27l);
		d.setLetra("B");
		d.setNumero(12);
		d.setPiso(4);
		
		Usuario u = new Usuario();
		u.setEmail("prueba@estovaiii.com");
		u.setNome("Pepee");
		u.setApelido1("Pruebaa");
		u.setApelido2("Probandoo");
		u.setPassword("holaa");
		u.setBanco(10.0d);
		u.setTelefono("982441122");
		u.setFechaNacimiento(new Date());
		u.setNomeUsuario("pruebita2");
		u.setDNI("76192887W");
		u.setDireccion(d);
		
		return usuarioService.create(u);
			
	}
	
	public void login(String mail, String pass) throws Exception{
		usuarioService.login(mail, pass);
	}
	
	public static void main(String args[]){
		
		MultipleServiceTest test = new MultipleServiceTest();
		Usuario u = null;

		try {
			
			//Formulario de registro --se nos enviara mail
			u = test.create(); 
			
			//Iniciamos sesion
			test.login(u.getEmail(), u.getPassword());
			
			//Editamos usuario
			
			//Editamos direccion
			
			//Ingresamos (tamen update de usuario)
			
			//Buscamos eventos
			
			//Buscamos mercados
			
			//Creamos apuestas
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		

}

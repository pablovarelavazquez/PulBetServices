package com.pvv.pulbet.dao;

import java.util.Date;
import java.util.List;

import com.pvv.pulbet.dao.impl.UsuarioDAOImpl;
import com.pvv.pulbet.model.Usuario;

public class UsuarioDAOTest {

	private UsuarioDAO dao = null;

	public UsuarioDAOTest() {
		dao = new UsuarioDAOImpl();		
	}

	//Agora como cambiamos os nosos daos e lles pasamos as conexions estes tests non funcionan
	//temos que facer os tests dos servizos
	//Se queremos facer os test aqui debemos pasar a conexion e logo cerrala para qnon petar a base de datos
	
//	public void testFindById() 
//			throws Exception {
//		Usuario u = dao.findById(5);
//		System.out.println(u);
//	}
//
//	public void testFindBy() 
//			throws Exception {
//		List<Usuario> usuarios = dao.findBy("a", "a", "g");
//		for (Usuario u:usuarios) {
//			System.out.println(u);
//		}
//
//	}
//
//	public void testCreate() 
//			throws Exception{
//
//		Usuario u = new Usuario();
//		u.setEmail("prueba@estovai.com");
//		u.setNome("Pepe");
//		u.setApelido1("Prueba");
//		u.setApelido2("Probando");
//		u.setPassword("deEaDca3wAa2cdsax");
//		u.setBanco(10.0d);
//		u.setTelefono("982440576");
//		u.setFechaNacimiento(new Date());
//		u.setNomeUsuario("pruebita1233");
//		u.setDNI("76192837X");
//
//		dao.create(u);
//		System.out.println(u);
//
//	}
//
//	public void testFindAll() 
//			throws Exception{
//		List<Usuario> results = null;
//
//		results = dao.findAll();
//
//		for (Usuario u: results) {
//			System.out.println(u);
//		}
//	}
//
//	public void testDelete()
//			throws Exception{
//		long iddd;
//		long idd = 11;
//		iddd = dao.delete(idd);
//		System.out.println("Se elimino el usuario con id "+idd);
//
//	}
//
//	public static void main(String[] args) {
//		try {
//			UsuarioDAOTest test = new UsuarioDAOTest();
//			//test.testFindById();
//			//test.testFindBy();
//			//test.testCreate();
//			test.testFindAll();
//			//falta update
//			//test.testDelete();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//
//
//	}

}
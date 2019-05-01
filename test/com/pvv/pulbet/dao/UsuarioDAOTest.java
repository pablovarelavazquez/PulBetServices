package com.pvv.pulbet.dao;

import java.util.Date;
import java.util.List;

import com.pvv.pulbet.model.Usuario;
import com.pvv.pulbet.service.UsuarioService;
import com.pvv.pulbet.service.impl.UsuarioServiceImpl;

public class UsuarioDAOTest {

	private UsuarioService usuarioService = null;

	public UsuarioDAOTest() {
		usuarioService = new UsuarioServiceImpl();		
	}

	public void testDelete()
			throws Exception{
		
		long idd = 2;
		idd = usuarioService.delete(idd);
		System.out.println("Se elimino el usuario con id "+idd);

	}

	public static void main(String[] args) {
		try {
			UsuarioDAOTest test = new UsuarioDAOTest();
			test.testDelete();
		} catch (Exception e) {
			e.printStackTrace();
		}



	}

}
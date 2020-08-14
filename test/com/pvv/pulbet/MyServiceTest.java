package com.pvv.pulbet;

import com.pvv.pulbet.dao.UsuarioDAO;
import com.pvv.pulbet.dao.impl.UsuarioDAOImpl;

public class MyServiceTest {
	
	//private MAilService mailService = null;
	
	private UsuarioDAO dao = null;
	
	public MyServiceTest() {
		//mailService = new
		dao = new UsuarioDAOImpl();
		
	}
	
	public void miMetodo() throws Exception{
		
		dao.findById(null, 1l);
	}

	public static void main(String[] args) {
		
		MyServiceTest t = new MyServiceTest();
		
		try {
		t.miMetodo();
		} catch(Exception e) {
			System.out.println("Petou: "+e.getMessage());
			e.printStackTrace();
		}
	}

}

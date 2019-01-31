package com.pvv.pulbet.service;

import com.pvv.pulbet.model.Usuario;
import com.pvv.pulbet.service.impl.MailServiceImpl;

public class MailServiceTest {

	public static void main(String[] args) throws Exception {
		
		Usuario u1 = new Usuario();
		u1.setNome("Pepe");
		u1.setApelido1("Perez");
		u1.setNomeUsuario("pepeperez1231");
		u1.setBanco(10.0d);
		u1.setEmail("varelavazquez.pablo@gmail.com");
		
		
		Usuario u2 = new Usuario();
		u2.setNome("Jose Antonio");
		u2.setApelido1("Lopez");
		u2.setNomeUsuario("jalp123");
		u2.setBanco(20.0d);
		u2.setEmail("joseantoniolp.teacher@gmail.com");
		
		
		Usuario[] usuarios = new Usuario[2];
		usuarios[0] = u1;
		usuarios[1] = u2;
		
		MailService mailService = new MailServiceImpl();
		
		for (Usuario u: usuarios) {
			
			String benvida = "Hola usuario "+ u.getNome() +" "+ u.getApelido1();
			String mensajeHTML = "<html><body><p style=\"font-size:20px\">Benvido a PulBet <b>"+u.getNomeUsuario()+"</b>. Vostede dispon agoramismo no seu banco de: "+u.getBanco()+" </p></body></html>";
			
			mailService.sendMail(mensajeHTML,benvida,u.getEmail());
		
		}
		

	}

}

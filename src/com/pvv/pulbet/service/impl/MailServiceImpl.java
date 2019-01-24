package com.pvv.pulbet.service.impl;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;

import com.pvv.pulbet.service.MailService;

public class MailServiceImpl implements MailService{
	
	public boolean sendMail(String mensajeHTMl, String subject, String... to) {

		
		try {
			HtmlEmail email = new HtmlEmail();
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("pulbetsoporte", PASSWORD));
			email.setSSLOnConnect(true);
			email.setFrom("varelavazquez.pablo@gmail.com");
			email.setSubject(subject);
			email.setHtmlMsg(mensajeHTMl);
			email.addTo(to);
			email.send();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	

	
	
	
	
	
	public static final String PASSWORD ="pulbetsoporte123.";
}

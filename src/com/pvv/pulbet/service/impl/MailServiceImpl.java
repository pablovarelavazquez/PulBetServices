package com.pvv.pulbet.service.impl;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;

import com.pvv.pulbet.exceptions.MailException;
import com.pvv.pulbet.service.MailService;

public class MailServiceImpl implements MailService{
	
	@Override
	public void sendMail(String mensajeHTMl, String subject, String... to) throws MailException{

		
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
			
		} catch (Exception e) {
			throw new MailException ("Trying to send email "
					+ " from pulbet "
					+ " to " + to, e);
		}
	}
	

	
	
	
	
	
	public static final String PASSWORD ="pulbetsoporte123.";
}

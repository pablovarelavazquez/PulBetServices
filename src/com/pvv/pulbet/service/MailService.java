package com.pvv.pulbet.service;

import com.pvv.pulbet.exceptions.MailException;

public interface MailService {

	public void sendMail(String mensajeHTMl, String subject, String... to) throws MailException;


}

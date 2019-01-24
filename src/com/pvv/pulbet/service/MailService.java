package com.pvv.pulbet.service;

public interface MailService {

	public  boolean sendMail(String mensajeHTMl, String subject, String... to) throws Exception;


}

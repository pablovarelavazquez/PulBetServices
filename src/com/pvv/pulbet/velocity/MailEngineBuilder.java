package com.pvv.pulbet.velocity;

import java.io.StringWriter;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class MailEngineBuilder {
	
	private static VelocityEngine engine = null;
	
	static {
		engine = new VelocityEngine();
		engine.setProperty("file.resource.loader.class", ClasspathResourceLoader.class.getName());
		engine.init();
	}

	public static String createMail(String template, Map<String, Object> mapa) {

		VelocityContext context = new VelocityContext(mapa);

		Template temp = engine.getTemplate(template);
		StringWriter stringWriter = new StringWriter();
		temp.merge(context, stringWriter);

		String mensaje = stringWriter.toString();

		return mensaje;
	}

}

package com.pvv.pulbet.service;

public class StringBuilderTest {
	private static final String CADENA = "lorem ipsum";
	
	private static long testPlus(int operaciones) {
		long t0 = System.currentTimeMillis();
		String s = "";		
		for (int i = 0; i<operaciones; i++) {
			s+=CADENA;
		}		
		long t1 = System.currentTimeMillis();	
		return t1 - t0; 	
	}
	
	private static long testStringBuilder(int operaciones) {
		long t0 = System.currentTimeMillis();
		StringBuilder sb = new StringBuilder("");		
		for (int i = 0; i<operaciones; i++) {
			sb.append(CADENA);
		}		
		String s = sb.toString();
		long t1 = System.currentTimeMillis();	
		return t1 - t0; 	
	}
	
	public static void main(String[] args) {
		System.out.println("Iniciando test...");
		testPlus(1);
		testStringBuilder(1);
		
		System.out.println("Iniciando prueba carga...");		
		int operaciones = 100000;
		
		long plusTime = testPlus(operaciones);
		long sbTime = testStringBuilder(operaciones); 
		
		System.out.println("Plus time: "+plusTime+" ms, StringBuilder time: "+sbTime+" ms");
	
	}
}

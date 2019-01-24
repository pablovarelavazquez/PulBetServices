package com.pvv.pulbet.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.pvv.pulbet.model.util.UsuarioByFechaComparator;
import com.pvv.pulbet.model.util.UsuarioByNombreComparator;

public class ObjectCreacionTest {

	public static void main(String[] args) {
			
		Apuesta a1 = new Apuesta();
		a1.setIdUsuario(1l);
		a1.setIdApuesta(1l);
		a1.setImporte(1.1d);
		a1.setFecha(new Date());

		Apuesta a2 = new Apuesta();
		a2.setIdUsuario(2l);
		//a2.setIdUsuario(1);
		a2.setIdApuesta(2l);
		a2.setImporte(1.0d);
		a2.setFecha(new Date());
		
		Apuesta a3 = new Apuesta(1l,3l);
		//Usuario u = new Usuario(3l,"email@gmail.com","abc123");
		
		Usuario u1 = new Usuario();
		u1.setNome("Pepe");
		u1.setFechaNacimiento(new Date(1990,12,10));
		
		Usuario u2 = new Usuario();
		u2.setNome("Antonio");
		u2.setFechaNacimiento(new Date(1997,6,5));
		
		Usuario u3 = new Usuario();
		u3.setNome("Manolo");
		u3.setFechaNacimiento(new Date(1995,8,31));
		
		
		//Xogando con listas
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(u1);
		usuarios.add(u2);
		usuarios.add(u3);
		
		
		System.out.println("Desordenada");
		for( Usuario u : usuarios) {
			System.out.println(u.getNome());
		}
		
		
		
		//Usar o comparador de usuarios:
		// comparo por nome
		Collections.sort(usuarios, new UsuarioByFechaComparator());
		System.out.println("Ordenado por fecha");
		for( Usuario u : usuarios) {
			System.out.println(u.getNome());
		}
		// comparo por fecha 
		Collections.sort(usuarios, UsuarioByNombreComparator.getInstance());
		System.out.println("Orrdenada por nome");
		for( Usuario u : usuarios) {
			System.out.println(u.getNome());
		}
		
		
		
		//analogo
//
//		for(int i=0; i<usuarios.size();i++) {
//			System.out.println(usuarios.get(i).getNome());
//		}
//		
//		if (usuarios.isEmpty()) {}
//		if (usuarios.size()==0) {}
//		
		
		
		
		
		
		List<Apuesta> apuestas = new ArrayList<Apuesta>();
		//		Apuesta[] apuestas = new Apuesta[2] {a1,a2};
		apuestas.add(a1);
		apuestas.add(a2);
		
		
		
		
		
		// para eliminar elementos dunha lista
		//apuestas.remove(a1);
		
//		for (Apuesta a: apuestas) {
//		System.out.println("Hola usuario "+a.getIdUsuario()+" !");
//		System.out.println("Datos de tu  apuesta ("+a.getIdApuesta()+"): ");
//		System.out.println("Importe: "+a.getImporte());
//		System.out.println("Ganancias: "+a.getGanancias());
//		System.out.println();
//		}

//		System.out.println(a1.toString());
//		System.out.println(a2.toString());
//		System.out.println(a3.toString());
//		System.out.println(u1.toString());

	}

}

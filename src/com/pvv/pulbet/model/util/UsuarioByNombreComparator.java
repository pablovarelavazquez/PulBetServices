package com.pvv.pulbet.model.util;

import java.util.Comparator;

import com.pvv.pulbet.model.Usuario;

public class UsuarioByNombreComparator implements Comparator<Usuario> {
	
	private static UsuarioByNombreComparator instance = null;
	
	public static final UsuarioByNombreComparator getInstance() {
		if(instance == null) {
			instance = new UsuarioByNombreComparator();
		}
		return instance;
	}
	
	private UsuarioByNombreComparator() {
		
	}

	@Override
	public int compare(Usuario u1, Usuario u2) {
		//System.out.println("Comparando "+u1.getNome()+ " e "+u2.getNome());
		return u1.getNome().compareTo(u2.getNome());
	}

}

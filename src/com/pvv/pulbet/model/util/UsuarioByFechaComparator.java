package com.pvv.pulbet.model.util;

import java.util.Comparator;

import com.pvv.pulbet.model.Usuario;

public class UsuarioByFechaComparator implements Comparator<Usuario> {

	@Override
	public int compare(Usuario u1, Usuario u2) {
		//System.out.println("Comparando "+u1.getFechaNacimiento()+ " e "+u2.getFechaNacimiento());
		return u1.getFechaNacimiento().compareTo(u2.getFechaNacimiento());
	}

}

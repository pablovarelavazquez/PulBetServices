package com.pvv.pulbet.model;

public class Resultado extends AbstractValueObject {
	
	private Long idResultado = null;
	private String nombre = null;
	private Long idTipoResulatado = null;
		
	public Resultado() {
		
	}

	public Long getIdResultado() {
		return idResultado;
	}

	public void setIdResultado(Long idResultado) {
		this.idResultado = idResultado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getIdTipoResulatado() {
		return idTipoResulatado;
	}

	public void setIdTipoResulatado(Long idTipoResulatado) {
		this.idTipoResulatado = idTipoResulatado;
	}

}

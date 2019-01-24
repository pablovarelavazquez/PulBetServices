package com.pvv.pulbet.model;

public class TipoResultado extends AbstractValueObject{
	
	private String idTipoResultado = null;
	private String nome = null;
	
	public TipoResultado() {
		
	}

	public String getIdTipoResultado() {
		return idTipoResultado;
	}

	public void setIdTipoResultado(String idTipoResultado) {
		this.idTipoResultado = idTipoResultado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}

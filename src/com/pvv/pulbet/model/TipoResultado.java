package com.pvv.pulbet.model;

public class TipoResultado extends AbstractValueObject{
	
	private Integer idTipoResultado = null;
	private String nome = null;
	
	public TipoResultado() {
		
	}

	public Integer getIdTipoResultado() {
		return idTipoResultado;
	}

	public void setIdTipoResultado(Integer idTipoResultado) {
		this.idTipoResultado = idTipoResultado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}

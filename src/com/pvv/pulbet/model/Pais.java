package com.pvv.pulbet.model;

public class Pais extends AbstractValueObject {
	
	private Integer idPais = null;
	private String nome = null;
	
	public Pais () {
		
	}

	public Integer getIdPais() {
		return idPais;
	}

	public void setIdPais(Integer idPais) {
		this.idPais = idPais;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}

package com.pvv.pulbet.model;

public class Provincia extends AbstractValueObject{
	
	private Integer idProvincia = null;
	private String nome = null;
	private Integer idPais;
	
	public Provincia() {
		
	}

	public Integer getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(Integer idProvincia) {
		this.idProvincia = idProvincia;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdPais() {
		return idPais;
	}

	public void setIdPais(Integer idPais) {
		this.idPais = idPais;
	}
}

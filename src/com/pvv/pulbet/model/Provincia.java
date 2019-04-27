package com.pvv.pulbet.model;

public class Provincia extends AbstractValueObject{
	
	private Long idProvincia = null;
	private String nome = null;
	private Integer idPais;
	
	public Provincia() {
		
	}

	public Long getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(Long idProvincia) {
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

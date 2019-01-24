package com.pvv.pulbet.model;

public class Deporte extends AbstractValueObject {
	
	private Long idDeporte = null;
	private String nome = null;

	public Deporte() {
		
	}

	public Long getIdDeporte() {
		return idDeporte;
	}

	public void setIdDeporte(Long idDeporte) {
		this.idDeporte = idDeporte;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}

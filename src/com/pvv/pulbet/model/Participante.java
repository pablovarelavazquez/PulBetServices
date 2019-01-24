package com.pvv.pulbet.model;

public abstract class Participante extends AbstractValueObject{ 	
															
	private Long idParticipante = null;
	private String nome = null;
	private Long idDeporte = null;

	public Participante(){
		
	}

	public Long getIdParticipante() {
		return idParticipante;
	}

	public void setIdParticipante(Long idParticipante) {
		this.idParticipante = idParticipante;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getIdDeporte() {
		return idDeporte;
	}

	public void setIdDeporte(Long idDeporte) {
		this.idDeporte = idDeporte;
	}


}

package com.pvv.pulbet.service;

import java.util.Date;

import com.pvv.pulbet.model.Evento;

public class EventoCriteria {

	private Long idEvento = null;
	private Date fecha = null;
	private Long idCompeticion = null;
	private Long idDeporte = null;
	private String participante = null;
	
	public EventoCriteria() {
		
	}
	

	public Long getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(Long idEvento) {
		this.idEvento = idEvento;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Long getIdCompeticion() {
		return idCompeticion;
	}

	public void setIdCompeticion(Long idCompeticion) {
		this.idCompeticion = idCompeticion;
	}

	public Long getIdDeporte() {
		return idDeporte;
	}

	public void setIdDeporte(Long idDeporte) {
		this.idDeporte = idDeporte;
	}

	public String getParticipante() {
		return participante;
	}

	public void setParticipante(String participante) {
		this.participante = participante;
	}

}

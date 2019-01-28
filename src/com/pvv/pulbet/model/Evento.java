package com.pvv.pulbet.model;

import java.util.Date;

public class Evento extends AbstractValueObject {

	private Long idEvento = null;
	private Date fecha = null;
	private Long idCompeticion = null;
	
	//Na base de datos non ten idDeporte e idParticipante pero penso que asi e mais facil traballar con el.
	private Long idDeporte = null;
	private Long idParticipante = null;
	
	public Evento() {
		
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

	public Long getIdParticipante() {
		return idParticipante;
	}

	public void setIdParticipante(Long idParticipante) {
		this.idParticipante = idParticipante;
	}


}

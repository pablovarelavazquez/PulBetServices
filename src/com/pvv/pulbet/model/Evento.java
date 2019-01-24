package com.pvv.pulbet.model;

import java.util.Date;

public class Evento extends AbstractValueObject {

	private Long idEvento = null;
	private Date fecha = null;
	private Long idCompeticion = null;

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

}

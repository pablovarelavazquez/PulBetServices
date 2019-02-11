package com.pvv.pulbet.model;

import java.util.Date;
import java.util.List;

public class Evento extends AbstractValueObject {

	private Long idEvento = null;
	private Date fecha = null;
	private Long idCompeticion = null;
	private Long idDeporte = null;
	private Long idParticipante = null;

	List<Participante> participantes = null;
	List<TipoResultado> mercados = null;
	List<Cuota> cuotas = null;
	
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

	public List<Participante> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<Participante> participantes) {
		this.participantes = participantes;
	}

	public List<TipoResultado> getMercados() {
		return mercados;
	}

	public void setMercados(List<TipoResultado> mercados) {
		this.mercados = mercados;
	}


}

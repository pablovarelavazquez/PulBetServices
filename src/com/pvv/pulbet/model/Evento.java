package com.pvv.pulbet.model;

import java.util.Date;
import java.util.List;

public class Evento extends AbstractValueObject {

	private Long idEvento = null;
	private Date fecha = null;
	private Long idCompeticion = null;
	private Long idDeporte = null;
	private String participante = null;

	private Participante local = null;
	private Participante visitante = null;
	List<TipoResultado> mercados = null;
		
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

	public String getParticipante() {
		return participante;
	}

	public void setParticipante(String participante) {
		this.participante = participante;
	}

	public Participante getLocal() {
		return local;
	}

	public void setLocal(Participante local) {
		this.local = local;
	}

	public Participante getVisitante() {
		return visitante;
	}

	public void setVisitante(Participante visitante) {
		this.visitante = visitante;
	}


	public List<TipoResultado> getMercados() {
		return mercados;
	}

	public void setMercados(List<TipoResultado> mercados) {
		this.mercados = mercados;
	}


}

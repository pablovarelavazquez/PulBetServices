package com.pvv.pulbet.model;

import java.util.Date;

public class Competicion extends AbstractValueObject{

	private Long idCompeticion = null;
	private String nome = null;
	private Long idDeporte = null;
	private Date fechaInicio = null;
	private Date fechaFin = null;
	

	public Competicion() {
		
	}


	public Long getIdCompeticion() {
		return idCompeticion;
	}


	public void setIdCompeticion(Long idCompeticion) {
		this.idCompeticion = idCompeticion;
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


	public Date getFechaInicio() {
		return fechaInicio;
	}


	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	public Date getFechaFin() {
		return fechaFin;
	}


	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	
}

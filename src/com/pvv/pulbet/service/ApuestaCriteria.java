package com.pvv.pulbet.service;

import java.util.Date;
import java.util.List;

import com.pvv.pulbet.model.LineaApuesta;

public class ApuestaCriteria{

	private Long idApuesta = null;
	private Double importe = null;
	private Long idUsuario = null;
	private Date desde = null;	
	private Date hasta = null;
	private Integer procesado = null;
	
	private List<LineaApuesta> lineas = null;
	
	public ApuestaCriteria() {
		
	}

	public Long getIdApuesta() {
		return idApuesta;
	}

	public void setIdApuesta(Long idApuesta) {
		this.idApuesta = idApuesta;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Date getDesde() {
		return desde;
	}

	public void setDesde(Date desde) {
		this.desde = desde;
	}

	public Date getHasta() {
		return hasta;
	}

	public void setHasta(Date hasta) {
		this.hasta = hasta;
	}

	public Integer getProcesado() {
		return procesado;
	}

	public void setProcesado(Integer procesado) {
		this.procesado = procesado;
	}

	public List<LineaApuesta> getLineas() {
		return lineas;
	}

	public void setLineas(List<LineaApuesta> lineas) {
		this.lineas = lineas;
	}
	
}

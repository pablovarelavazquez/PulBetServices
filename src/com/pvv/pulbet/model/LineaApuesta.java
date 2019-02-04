package com.pvv.pulbet.model;

public class LineaApuesta extends AbstractValueObject {
	
	private Integer numLinea = null;
	private Long idApuesta = null;
	private Long idResultado = null;
	private Long idEvento = null;
	private Integer procesado = null;
	
	public LineaApuesta() {
		
	}

	public Integer getNumLinea() {
		return numLinea;
	}

	public void setNumLinea(Integer numLinea) {
		this.numLinea = numLinea;
	}

	public Long getIdApuesta() {
		return idApuesta;
	}

	public void setIdApuesta(Long idApuesta) {
		this.idApuesta = idApuesta;
	}

	public Long getIdResultado() {
		return idResultado;
	}

	public void setIdResultado(Long idResultado) {
		this.idResultado = idResultado;
	}

	public Long getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(Long idEvento) {
		this.idEvento = idEvento;
	}

	public Integer getProcesado() {
		return procesado;
	}

	public void setProcesado(Integer procesado) {
		this.procesado = procesado;
	}

}

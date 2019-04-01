package com.pvv.pulbet.model;

import java.util.Date;
import java.util.List;

public class Apuesta extends AbstractValueObject{
	
	private Long idApuesta = null;
	private Double importe = null;
	private Long idUsuario = null;
	private Date fecha = null;	
	private Double ganancias = null;
	private Integer procesado = null;
	
	private List<LineaApuesta> lineas = null;
	
	
	public Apuesta() {
		
	}
	
	public Apuesta(Long idApuesta) {
		this(idApuesta, null);
		
	}

	
	public Apuesta(Long idApuesta, Long idUsuario) {
		setIdApuesta(idApuesta);
		setIdUsuario(idUsuario);
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getProcesado() {
		return procesado;
	}

	public Double getGanancias() {
		return ganancias;
	}

	public void setGanancias(Double ganancias) {
		this.ganancias = ganancias;
	}

	public void setProcesado(Integer procesado) {
		this.procesado = procesado;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}


	public List<LineaApuesta> getLineas() {
		return lineas;
	}

	public void setLineas(List<LineaApuesta> lineas) {
		this.lineas = lineas;
	}

	@Override
	public boolean equals(Object obj) {
		boolean error = false;
		if (obj == null) {
			return error;
		} else {
		Apuesta a = (Apuesta)obj;
		 
		return a.getIdApuesta().equals(this.getIdApuesta());
		}
		
	}

}

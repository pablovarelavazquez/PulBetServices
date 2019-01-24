package com.pvv.pulbet.model;

import java.util.Date;

public class Apuesta extends AbstractValueObject{
	
	private Long idApuesta = null;
	private Double importe = null;
	private Long idUsuario = null;
	private Date fecha = null;	
	
	
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

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
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
		//con "equalsIgnoreCase" podemos comparar cadenas sen importar se ten mayusculas ou minusculas
		//debemos xestionar os posibles casos para os datos que nos envian, por exemplo, neste caso, se o idApuesta e nulo
		
	}

}

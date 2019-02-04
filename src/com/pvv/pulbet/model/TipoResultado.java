package com.pvv.pulbet.model;

import java.util.List;

public class TipoResultado extends AbstractValueObject{
	
	private Integer idTipoResultado = null;
	private String nome = null;
	
	private List<Resultado> resultados = null;
	
	public TipoResultado() {
		
	}

	public Integer getIdTipoResultado() {
		return idTipoResultado;
	}

	public void setIdTipoResultado(Integer idTipoResultado) {
		this.idTipoResultado = idTipoResultado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Resultado> getResultados() {
		return resultados;
	}

	public void setResultados(List<Resultado> resultados) {
		this.resultados = resultados;
	}

}

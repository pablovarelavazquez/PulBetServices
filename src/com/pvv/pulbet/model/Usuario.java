package com.pvv.pulbet.model;

import java.util.Date;

public class Usuario extends AbstractValueObject{
	
	private Long idUsuario = null;
	private String email = null;
	private String nome = null;
	private String apelido1 = null;
	private String apelido2 = null;
	private String password = null;
	private Double banco = null;
	private String telefono = null;
	private Date fechaNacimiento = null;
	private String nomeUsuario = null;
	// private String passwordEncriptada = null; 
	private String DNI = null;
	private Long idDireccion = null;
	
	private Direccion direccion = null;
	
	public Usuario() {
		
	}
	
	public Usuario(Long idUsuario) {
		this(idUsuario,null);
		
	}
	
	public Usuario(Long idUsuario, String email) {
		this(idUsuario,email,null);

	}
	
	public Usuario(Long idUsuario, String email, String password) {
		setIdUsuario(idUsuario);
		setEmail(email);
		setPassword(password);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getApelido1() {
		return apelido1;
	}

	public void setApelido1(String apelido1) {
		this.apelido1 = apelido1;
	}

	public String getApelido2() {
		return apelido2;
	}

	public void setApelido2(String apelido2) {
		this.apelido2 = apelido2;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Double getBanco() {
		return banco;
	}

	public void setBanco(Double banco) {
		this.banco = banco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}




	public Long getIdDireccion() {
		return idDireccion;
	}

	public void setIdDireccion(Long idDireccion) {
		this.idDireccion = idDireccion;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	@Override
	public boolean equals(Object obj) {
		boolean error = false;
		if (obj == null) {
			return error;
		} else {
		Usuario u= (Usuario)obj;
		 
		return u.getIdUsuario().equals(this.getIdUsuario());
		
		}
		//con "equalsIgnoreCase" podemos comparar cadenas sen importar se ten mayusculas ou minusculas
		
	}



}

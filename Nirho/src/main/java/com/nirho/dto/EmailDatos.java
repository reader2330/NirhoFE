package com.nirho.dto;

import java.io.Serializable;

public class EmailDatos  implements Serializable {
	private static final long serialVersionUID = 1L;
	private String emailDestino;
	private String emailConsultor;
	private String nombreParticipante;
	private String nombreProyecto;
	private String token;
		
	public String getEmailDestino() {
		return emailDestino;
	}
	public void setEmailDestino(String emailDestino) {
		this.emailDestino = emailDestino;
	}
	public String getEmailConsultor() {
		return emailConsultor;
	}
	public void setEmailConsultor(String emailConsultor) {
		this.emailConsultor = emailConsultor;
	}
	public String getNombreParticipante() {
		return nombreParticipante;
	}
	public void setNombreParticipante(String nombreParticipante) {
		this.nombreParticipante = nombreParticipante;
	}
	public String getNombreProyecto() {
		return nombreProyecto;
	}
	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "EmailDatos [emailDestino=" + emailDestino + ", emailConsultor=" + emailConsultor
				+ ", nombreParticipante=" + nombreParticipante + ", nombreProyecto=" + nombreProyecto + ", token="
				+ token + "]";
	}
		
}

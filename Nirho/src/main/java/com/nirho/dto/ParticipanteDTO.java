package com.nirho.dto;

import java.io.Serializable;

public class ParticipanteDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nombre;	
	private String puesto;
	
	public ParticipanteDTO(String nombre, String puesto) {
		super();
		this.nombre = nombre;
		this.puesto = puesto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPuesto() {
		return puesto;
	}
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	@Override
	public String toString() {
		return "ParticipanteDTO [nombre=" + nombre + ", puesto=" + puesto + "]";
	}
}

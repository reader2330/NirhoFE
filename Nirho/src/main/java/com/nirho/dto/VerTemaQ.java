package com.nirho.dto;

import java.io.Serializable;
import java.util.List;


public class VerTemaQ  implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nombre;
	private List<String> preguntas;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<String> getPreguntas() {
		return preguntas;
	}
	public void setPreguntas(List<String> preguntas) {
		this.preguntas = preguntas;
	}
	
	@Override
	public String toString() {
		return "VerTemaQ [nombre=" + nombre + ", preguntas=" + preguntas + "]";
	}
}

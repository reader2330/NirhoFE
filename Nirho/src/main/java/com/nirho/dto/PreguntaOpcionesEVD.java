package com.nirho.dto;

import java.io.Serializable;
import java.util.List;

import com.nirho.model.Opcion;
import com.nirho.model.Pregunta;

public class PreguntaOpcionesEVD  implements Serializable {
	private static final long serialVersionUID = 1L;
	private Pregunta pregunta;
	private List<Opcion> opciones;
	public Pregunta getPregunta() {
		return pregunta;
	}
	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}
	public List<Opcion> getOpciones() {
		return opciones;
	}
	public void setOpciones(List<Opcion> opciones) {
		this.opciones = opciones;
	}
	@Override
	public String toString() {
		return "PreguntasOpcionesEVD [pregunta=" + pregunta + ", opciones=" + opciones + "]";
	}
}

package com.nirho.dto;

import java.io.Serializable;
import java.util.List;

import com.nirho.model.PreguntaTema;
import com.nirho.model.TemaCuestionario;

public class TemaPreguntas  implements Serializable {
	private static final long serialVersionUID = 1L;
	private TemaCuestionario tema;
	private List<PreguntaTema> preguntas;
	
	public TemaCuestionario getTema() {
		return tema;
	}
	public void setTema(TemaCuestionario tema) {
		this.tema = tema;
	}
	public List<PreguntaTema> getPreguntas() {
		return preguntas;
	}
	public void setPreguntas(List<PreguntaTema> preguntas) {
		this.preguntas = preguntas;
	}
	
	@Override
	public String toString() {
		return "TemaPreguntas [tema=" + tema + ", preguntas=" + preguntas + "]";
	}
}

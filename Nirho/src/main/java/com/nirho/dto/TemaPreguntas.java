package com.nirho.dto;

import java.io.Serializable;
import java.util.List;

import com.nirho.model.Pregunta;
import com.nirho.model.Tema;

public class TemaPreguntas  implements Serializable {
	private static final long serialVersionUID = 1L;
	private Tema tema;
	private List<Pregunta> preguntas;
			
	public Tema getTema() {
		return tema;
	}
	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public List<Pregunta> getPreguntas() {
		return preguntas;
	}



	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}



	@Override
	public String toString() {
		return "TemaPreguntas [tema=" + tema + ", preguntas=" + preguntas + "]";
	}
}

package com.nirho.dto;

import java.io.Serializable;
import java.util.List;

import com.nirho.model.Participante;

public class EvaluadorDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Participante evaluador;
	private List<Participante> evaluados;
	
	public Participante getEvaluador() {
		return evaluador;
	}
	public void setEvaluador(Participante evaluador) {
		this.evaluador = evaluador;
	}
	public List<Participante> getEvaluados() {
		return evaluados;
	}
	public void setEvaluados(List<Participante> evaluados) {
		this.evaluados = evaluados;
	}
	
	@Override
	public String toString() {
		return "EvaluadorDTO [evaluador=" + evaluador + ", evaluados=" + evaluados + "]";
	}
}

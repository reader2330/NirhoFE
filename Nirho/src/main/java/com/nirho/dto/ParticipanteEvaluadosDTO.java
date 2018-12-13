package com.nirho.dto;

import java.io.Serializable;
import java.util.List;

public class ParticipanteEvaluadosDTO  implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idParticipante;
	private List<Integer> evaluados;
	public int getIdParticipante() {
		return idParticipante;
	}
	public void setIdParticipante(int idParticipante) {
		this.idParticipante = idParticipante;
	}
	public List<Integer> getEvaluados() {
		return evaluados;
	}
	public void setEvaluados(List<Integer> evaluados) {
		this.evaluados = evaluados;
	}
	@Override
	public String toString() {
		return "ParticipanteEvaluadosDTO [idParticipante=" + idParticipante
				+ ", evaluados=" + evaluados + "]";
	}
	
}

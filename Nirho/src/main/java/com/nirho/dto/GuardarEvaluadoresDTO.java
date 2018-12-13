package com.nirho.dto;

import java.io.Serializable;
import java.util.List;

public class GuardarEvaluadoresDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer idProyecto;
	private List<ParticipanteEvaluadosDTO> evaluadores;
	public Integer getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(Integer idProyecto) {
		this.idProyecto = idProyecto;
	}
	public List<ParticipanteEvaluadosDTO> getEvaluadores() {
		return evaluadores;
	}
	public void setEvaluadores(List<ParticipanteEvaluadosDTO> evaluadores) {
		this.evaluadores = evaluadores;
	}
	@Override
	public String toString() {
		return "GuardarEvaluadoresDTO [idProyecto=" + idProyecto + ", evaluadores=" + evaluadores + "]";
	}
}

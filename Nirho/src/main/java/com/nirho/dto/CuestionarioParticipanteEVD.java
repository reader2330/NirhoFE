package com.nirho.dto;

import java.io.Serializable;
import java.util.List;

import com.nirho.model.CuetionarioParticipante;
import com.nirho.model.Opcion;
import com.nirho.model.Participante;

public class CuestionarioParticipanteEVD implements Serializable {
	private static final long serialVersionUID = 1L;
	private CuetionarioParticipante cuestionarioParticipante;
	private Participante participante;
	private List<Opcion> opciones;
	public CuetionarioParticipante getCuestionarioParticipante() {
		return cuestionarioParticipante;
	}
	public void setCuestionarioParticipante(CuetionarioParticipante cuestionarioParticipante) {
		this.cuestionarioParticipante = cuestionarioParticipante;
	}
	public Participante getParticipante() {
		return participante;
	}
	public void setParticipante(Participante participante) {
		this.participante = participante;
	}
	public List<Opcion> getOpciones() {
		return opciones;
	}
	public void setOpciones(List<Opcion> opciones) {
		this.opciones = opciones;
	}
	@Override
	public String toString() {
		return "CuestionarioParticipanteEVD [cuestionarioParticipante=" + cuestionarioParticipante + ", participante="
				+ participante + ", opciones=" + opciones + "]";
	}
}

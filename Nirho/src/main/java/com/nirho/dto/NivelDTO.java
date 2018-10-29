package com.nirho.dto;

import java.io.Serializable;
import java.util.List;

public class NivelDTO  implements Serializable {
	private static final long serialVersionUID = 1L;
	private int nivel;
	private List<ParticipanteDTO> participantes;
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public List<ParticipanteDTO> getParticipantes() {
		return participantes;
	}
	public void setParticipantes(List<ParticipanteDTO> participantes) {
		this.participantes = participantes;
	}
	@Override
	public String toString() {
		return "NivelDTO [nivel=" + nivel + ", participantes=" + participantes + "]";
	}
	
}

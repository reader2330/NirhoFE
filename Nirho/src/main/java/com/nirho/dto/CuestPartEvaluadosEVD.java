package com.nirho.dto;

import java.io.Serializable;
import java.util.List;

public class CuestPartEvaluadosEVD implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<CuestionarioParticipanteEVD> cuestionarioParticipantes;
	public CuestPartEvaluadosEVD(List<CuestionarioParticipanteEVD> cuestionarioParticipantes) {
		this.cuestionarioParticipantes = cuestionarioParticipantes;
	}
	public List<CuestionarioParticipanteEVD> getCuestionarioParticipantes() {
		return cuestionarioParticipantes;
	}
	public void setCuestionarioParticipantes(List<CuestionarioParticipanteEVD> cuestionarioParticipantes) {
		this.cuestionarioParticipantes = cuestionarioParticipantes;
	}
	@Override
	public String toString() {
		return "CuestPartEvaluadosEVD [cuestionarioParticipantes=" + cuestionarioParticipantes + "]";
	}	
}

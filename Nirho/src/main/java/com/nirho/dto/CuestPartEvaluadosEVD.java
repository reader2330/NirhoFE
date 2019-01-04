package com.nirho.dto;

import java.io.Serializable;
import java.util.List;

public class CuestPartEvaluadosEVD implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<CuestionarioParticipanteEVD> cuestionarioParticipantes;
	private String autoEval;
	
	public CuestPartEvaluadosEVD(List<CuestionarioParticipanteEVD> cuestionarioParticipantes) {
		this.cuestionarioParticipantes = cuestionarioParticipantes;
	}
	public List<CuestionarioParticipanteEVD> getCuestionarioParticipantes() {
		return cuestionarioParticipantes;
	}
	public void setCuestionarioParticipantes(List<CuestionarioParticipanteEVD> cuestionarioParticipantes) {
		this.cuestionarioParticipantes = cuestionarioParticipantes;
	}
	public String getAutoEval() {
		return autoEval;
	}
	public void setAutoEval(String autoEval) {
		this.autoEval = autoEval;
	}
	
	@Override
	public String toString() {
		return "CuestPartEvaluadosEVD [cuestionarioParticipantes=" + cuestionarioParticipantes + ", autoEval="
				+ autoEval + "]";
	}
}

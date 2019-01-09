package com.nirho.dto;

import java.io.Serializable;

public class PromPartDTO  implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idParticipante;
	private double promedio;
	public PromPartDTO() {
		
	}
	public PromPartDTO(int idParticipante, double promedio) {
		this.idParticipante = idParticipante;
		this.promedio = promedio;
	}
	public int getIdParticipante() {
		return idParticipante;
	}
	public void setIdParticipante(int idParticipante) {
		this.idParticipante = idParticipante;
	}
	public double getPromedio() {
		return promedio;
	}
	public void setPromedio(double promedio) {
		this.promedio = promedio;
	}
	@Override
	public String toString() {
		return "PromPartDTO [idParticipante=" + idParticipante + ", promedio=" + promedio + "]";
	}
}

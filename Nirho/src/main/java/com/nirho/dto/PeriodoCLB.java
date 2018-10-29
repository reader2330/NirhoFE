package com.nirho.dto;

import java.io.Serializable;

import com.nirho.model.Proyecto;

public class PeriodoCLB implements Serializable {
	private static final long serialVersionUID = 1L;
	private Proyecto proyecto;
	private String fechaRegistro;
	private String fechaFin;
	
	public Proyecto getProyecto() {
		return proyecto;
	}
	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}
	public String getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	@Override
	public String toString() {
		return "PeriodoGarantiaCLB [proyecto=" + proyecto + ", fechaRegistro=" + fechaRegistro + ", fechaFin="
				+ fechaFin + "]";
	}
}

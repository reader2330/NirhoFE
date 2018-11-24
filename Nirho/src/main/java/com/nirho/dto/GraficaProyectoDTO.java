package com.nirho.dto;

import java.io.Serializable;
import java.util.List;

import com.nirho.model.GraficaProyecto;
import com.nirho.model.Proyecto;

public class GraficaProyectoDTO  implements Serializable {
	private static final long serialVersionUID = 1L;
	private Proyecto proyecto;
	private List<List<GraficaProyecto>> datos;
	public Proyecto getProyecto() {
		return proyecto;
	}
	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}
	public List<List<GraficaProyecto>> getDatos() {
		return datos;
	}
	public void setDatos(List<List<GraficaProyecto>> datos) {
		this.datos = datos;
	}
	
	@Override
	public String toString() {
		return "GraficaProyectoDTO [proyecto=" + proyecto + ", datos=" + datos + "]";
	}
}

package com.nirho.dto;

import java.io.Serializable;
import java.util.List;

import com.nirho.model.Proyecto;

public class GraficaRespPregDTO  implements Serializable {
	private static final long serialVersionUID = 1L;
	private Proyecto proyecto;
	private List<GraficaAreaOrgDTO> areas;
	public Proyecto getProyecto() {
		return proyecto;
	}
	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}	
	public List<GraficaAreaOrgDTO> getAreas() {
		return areas;
	}
	public void setAreas(List<GraficaAreaOrgDTO> areas) {
		this.areas = areas;
	}
	@Override
	public String toString() {
		return "GraficapProyectoDTO [proyecto=" + proyecto + ", areas=" + areas + "]";
	}
}

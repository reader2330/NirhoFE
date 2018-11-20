package com.nirho.dto;

import java.io.Serializable;
import java.util.List;

public class GraficaAreaOrgDTO  implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idProyecto;
	private String areaOrg;
	private List<GraficaTemaDTO> resultados;
	public int getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(int idProyecto) {
		this.idProyecto = idProyecto;
	}
	public String getAreaOrg() {
		return areaOrg;
	}
	public void setAreaOrg(String areaOrg) {
		this.areaOrg = areaOrg;
	}
	public List<GraficaTemaDTO> getResultados() {
		return resultados;
	}
	public void setResultados(List<GraficaTemaDTO> resultados) {
		this.resultados = resultados;
	}
	@Override
	public String toString() {
		return "GraficaAreaOrgDTO [idProyecto=" + idProyecto + ", areaOrg=" + areaOrg + ", resultados=" + resultados
				+ "]";
	}
}

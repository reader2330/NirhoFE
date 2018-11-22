package com.nirho.dto;

import java.io.Serializable;
import java.util.List;

public class GraficaAreaOrgDTO  implements Serializable {
	private static final long serialVersionUID = 1L;
	private String areaOrg;
	private List<GraficaResultadoDTO> resultados;
		
	public String getAreaOrg() {
		return areaOrg;
	}
	public void setAreaOrg(String areaOrg) {
		this.areaOrg = areaOrg;
	}
	public List<GraficaResultadoDTO> getResultados() {
		return resultados;
	}
	public void setResultados(List<GraficaResultadoDTO> resultados) {
		this.resultados = resultados;
	}
	@Override
	public String toString() {
		return "GraficaAreaOrgDTO [areaOrg=" + areaOrg + ", resultados=" + resultados
				+ "]";
	}
}

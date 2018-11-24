package com.nirho.dto;

import java.io.Serializable;

public class GraficaPastelDTO  implements Serializable {
	private static final long serialVersionUID = 1L;
	private String areaOranizacional;
	private int numParticipantes;
	
	public String getAreaOranizacional() {
		return areaOranizacional;
	}
	public void setAreaOranizacional(String areaOranizacional) {
		this.areaOranizacional = areaOranizacional;
	}
	public int getNumParticipantes() {
		return numParticipantes;
	}
	public void setNumParticipantes(int numParticipantes) {
		this.numParticipantes = numParticipantes;
	}
	
	@Override
	public String toString() {
		return "GraficaPastelDTO [areaOranizacional=" + areaOranizacional + ", numParticipantes=" + numParticipantes
				+ "]";
	}
	
}

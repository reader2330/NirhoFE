package com.nirho.dto;

import java.io.Serializable;

public class AreaPromDTO  implements Serializable {
	private static final long serialVersionUID = 1L;
	private String area;
	private double prom;
	public AreaPromDTO() {
		
	}
	public AreaPromDTO(String area, double prom) {
		this.area = area;
		this.prom = prom;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public double getProm() {
		return prom;
	}
	public void setProm(double prom) {
		this.prom = prom;
	}
	@Override
	public String toString() {
		return "AreaPromDTO [area=" + area + ", prom=" + prom + "]";
	}
}

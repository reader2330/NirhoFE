package com.nirho.dto;

import java.io.Serializable;

public class AcumDTO  implements Serializable {
	private static final long serialVersionUID = 1L;
	private int suma;
	private int cont;
	public AcumDTO() {
		
	}
	public AcumDTO(int suma, int cont) {
		this.suma = suma;
		this.cont = cont;
	}
	public int getSuma() {
		return suma;
	}
	public void setSuma(int suma) {
		this.suma = suma;
	}
	public int getCont() {
		return cont;
	}
	public void setCont(int cont) {
		this.cont = cont;
	}
	@Override
	public String toString() {
		return "AcumAreaDTO [suma=" + suma + ", cont=" + cont + "]";
	}
}

package com.nirho.dto;

import java.io.Serializable;

import com.nirho.model.Tema;

public class GraficaTemaDTO  implements Serializable {
	private static final long serialVersionUID = 1L;
	private Tema tema;
	private int numResp1;
	private int numResp2;
	private int numResp3;
	private int numResp4;
	private int numResp5;
	
	public Tema getTema() {
		return tema;
	}
	public void setTema(Tema tema) {
		this.tema = tema;
	}
	public int getNumResp1() {
		return numResp1;
	}
	public void setNumResp1(int numResp1) {
		this.numResp1 = numResp1;
	}
	public int getNumResp2() {
		return numResp2;
	}
	public void setNumResp2(int numResp2) {
		this.numResp2 = numResp2;
	}
	public int getNumResp3() {
		return numResp3;
	}
	public void setNumResp3(int numResp3) {
		this.numResp3 = numResp3;
	}
	public int getNumResp4() {
		return numResp4;
	}
	public void setNumResp4(int numResp4) {
		this.numResp4 = numResp4;
	}
	public int getNumResp5() {
		return numResp5;
	}
	public void setNumResp5(int numResp5) {
		this.numResp5 = numResp5;
	}
	
	@Override
	public String toString() {
		return "GraficaAreaOrgDTO [tema=" + tema + ", numResp1=" + numResp1
				+ ", numResp2=" + numResp2 + ", numResp3=" + numResp3 + ", numResp4=" + numResp4 + ", numResp5="
				+ numResp5 + "]";
	}
}

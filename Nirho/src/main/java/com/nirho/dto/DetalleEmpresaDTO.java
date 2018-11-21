package com.nirho.dto;

import java.io.Serializable;

import com.nirho.model.Empresa;
import com.nirho.model.Entrevistado;

public class DetalleEmpresaDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Entrevistado entrevistado;
	
	private Empresa empresa;

	public Entrevistado getEntrevistado() {
		return entrevistado;
	}

	public void setEntrevistado(Entrevistado entrevistado) {
		this.entrevistado = entrevistado;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

}

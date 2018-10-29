package com.nirho.dto;

import com.nirho.model.Empresa;
import com.nirho.model.Entrevistado;

public class RegistroEmpresaDTO {
	
	private Empresa empresa;
	private Entrevistado entrevistado;
	
	/**
	 * @return the empresa
	 */
	public Empresa getEmpresa() {
		return empresa;
	}
	/**
	 * @param empresa the empresa to set
	 */
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	/**
	 * @return the entrevistado
	 */
	public Entrevistado getEntrevistado() {
		return entrevistado;
	}
	/**
	 * @param entrevistado the entrevistado to set
	 */
	public void setEntrevistado(Entrevistado entrevistado) {
		this.entrevistado = entrevistado;
	}
}

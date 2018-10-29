package com.nirho.dto;

import java.io.Serializable;

public class CuestionarioEmpresaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 242680035278756542L;
	
	private Integer idCuestionarioEmpresa;
	private Integer idTema;
	private String nombre;
	private boolean activo;

	/**
	 * @return the idCuestionarioEmpresa
	 */
	public Integer getIdCuestionarioEmpresa() {
		return idCuestionarioEmpresa;
	}

	/**
	 * @param idCuestionarioEmpresa
	 *            the idCuestionarioEmpresa to set
	 */
	public void setIdCuestionarioEmpresa(Integer idCuestionarioEmpresa) {
		this.idCuestionarioEmpresa = idCuestionarioEmpresa;
	}

	/**
	 * @return the idTema
	 */
	public Integer getIdTema() {
		return idTema;
	}

	/**
	 * @param idTema
	 *            the idTema to set
	 */
	public void setIdTema(Integer idTema) {
		this.idTema = idTema;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the activo
	 */
	public boolean isActivo() {
		return activo;
	}

	/**
	 * @param activo
	 *            the activo to set
	 */
	public void setActivo(boolean activo) {
		this.activo = activo;
	}

}

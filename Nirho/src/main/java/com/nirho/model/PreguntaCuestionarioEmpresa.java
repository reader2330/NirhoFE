/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nirho.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author eisten
 */
@Entity
@Table(name = "pregunta_cuestionario_empresa")
public class PreguntaCuestionarioEmpresa implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1396250763307809006L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pregunta_cuestionario_empresa")
    private Integer idPreguntaCuestionarioEmpresa;
    
	@ManyToOne
	@JoinColumn(name = "id_cuestionario_empresa", referencedColumnName = "id_cuestionario_empresa")
    private CuestionarioEmpresa cuestionarioEmpresa;
    
	@ManyToOne
	@JoinColumn(name = "id_pregunta", referencedColumnName = "id_pregunta")
    private PreguntaTema preguntaTema;

	/**
	 * Constructor vacio
	 */
	public PreguntaCuestionarioEmpresa() {
		super();
	}
	
	/**
	 * @param cuestionarioEmpresa
	 * @param preguntaTema
	 */
	public PreguntaCuestionarioEmpresa(CuestionarioEmpresa cuestionarioEmpresa, PreguntaTema preguntaTema) {
		super();
		this.cuestionarioEmpresa = cuestionarioEmpresa;
		this.preguntaTema = preguntaTema;
	}



	/**
	 * @return the idPreguntaCuestionarioEmpresa
	 */
	public Integer getIdPreguntaCuestionarioEmpresa() {
		return idPreguntaCuestionarioEmpresa;
	}

	/**
	 * @param idPreguntaCuestionarioEmpresa the idPreguntaCuestionarioEmpresa to set
	 */
	public void setIdPreguntaCuestionarioEmpresa(
			Integer idPreguntaCuestionarioEmpresa) {
		this.idPreguntaCuestionarioEmpresa = idPreguntaCuestionarioEmpresa;
	}

	/**
	 * @return the cuestionarioEmpresa
	 */
	public CuestionarioEmpresa getCuestionarioEmpresa() {
		return cuestionarioEmpresa;
	}

	/**
	 * @param cuestionarioEmpresa the cuestionarioEmpresa to set
	 */
	public void setCuestionarioEmpresa(CuestionarioEmpresa cuestionarioEmpresa) {
		this.cuestionarioEmpresa = cuestionarioEmpresa;
	}

	/**
	 * @return the preguntaTema
	 */
	public PreguntaTema getPreguntaTema() {
		return preguntaTema;
	}

	/**
	 * @param preguntaTema the preguntaTema to set
	 */
	public void setPreguntaTema(PreguntaTema preguntaTema) {
		this.preguntaTema = preguntaTema;
	}
}

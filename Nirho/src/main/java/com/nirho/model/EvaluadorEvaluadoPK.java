/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nirho.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author DELL
 */
@Embeddable
public class EvaluadorEvaluadoPK implements Serializable {
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
    @Column(name = "id_proyecto")
    private int idProyecto;
	@Basic(optional = false)
    @Column(name = "id_evaluador")
    private int idEvaluador;
    @Basic(optional = false)
    @Column(name = "id_evaluado")
    private int idEvaluado;
       
    public EvaluadorEvaluadoPK() {
		
	}
    
	public EvaluadorEvaluadoPK(int idProyecto, int idEvaluador, int idEvaluado) {
		this.idProyecto = idProyecto;
		this.idEvaluador = idEvaluador;
		this.idEvaluado = idEvaluado;
	}
	
	public int getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(int idProyecto) {
		this.idProyecto = idProyecto;
	}
	public int getIdEvaluador() {
		return idEvaluador;
	}
	public void setIdEvaluador(int idEvaluador) {
		this.idEvaluador = idEvaluador;
	}
	public int getIdEvaluado() {
		return idEvaluado;
	}
	public void setIdEvaluado(int idEvaluado) {
		this.idEvaluado = idEvaluado;
	}
	
	@Override
	public String toString() {
		return "EvaluadorEvaluadoPK [idProyecto=" + idProyecto + ", idEvaluador=" + idEvaluador + ", idEvaluado="
				+ idEvaluado + "]";
	}
    
}

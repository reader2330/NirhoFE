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
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author eisten
 */
@Entity
@Table(name = "estatus_proyecto")
public class EstatusProyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_estatus")
    private Integer idEstatus;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
	
    public EstatusProyecto() {
		
	}
    public EstatusProyecto(Integer idEstatus) {
		this.idEstatus = idEstatus;
	}
	public Integer getIdEstatus() {
		return idEstatus;
	}
	public void setIdEstatus(Integer idEstatus) {
		this.idEstatus = idEstatus;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Override
	public String toString() {
		return "EstatusProyecto [idEstatus=" + idEstatus + ", descripcion=" + descripcion + "]";
	}
}

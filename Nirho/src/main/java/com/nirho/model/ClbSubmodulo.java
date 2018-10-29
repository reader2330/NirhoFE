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
@Table(name = "clb_submodulo")
public class ClbSubmodulo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_submodulo")
    private Long id_submodulo;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    
	public Long getId_submodulo() {
		return id_submodulo;
	}
	public void setId_submodulo(Long id_submodulo) {
		this.id_submodulo = id_submodulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Override
	public String toString() {
		return "ClbSubmodulo [id_submodulo=" + id_submodulo + ", descripcion=" + descripcion + "]";
	}
    
}

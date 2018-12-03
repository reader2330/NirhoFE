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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author eisten
 */
@Embeddable
public class RolClbSubmoduloPK implements Serializable {
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
    @Column(name = "rol")
    private int rol;
    @JoinColumn(name = "id_submodulo", referencedColumnName = "id_submodulo")
    @ManyToOne(optional = false)
    private ClbSubmodulo submodulo;
    
	public int getRol() {
		return rol;
	}
	public void setRol(int rol) {
		this.rol = rol;
	}
	public ClbSubmodulo getSubmodulo() {
		return submodulo;
	}
	public void setSubmodulo(ClbSubmodulo submodulo) {
		this.submodulo = submodulo;
	}
	
	@Override
	public String toString() {
		return "RolClbSubmoduloPK [rol=" + rol + ", submodulo=" + submodulo + "]";
	}
	
}

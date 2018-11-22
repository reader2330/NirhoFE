/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nirho.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author eisten
 */
@Entity
@Table(name = "rol_clb_submodulo")
public class RolClbSubmodulo implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RolClbSubmoduloPK pk;
    
	public RolClbSubmoduloPK getPk() {
		return pk;
	}
	public void setPk(RolClbSubmoduloPK pk) {
		this.pk = pk;
	}
	
	@Override
	public String toString() {
		return "RolClbSubmodulo [pk=" + pk + "]";
	}
	
}

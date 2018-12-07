/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nirho.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author alfredo.ca
 */
@Entity
@Table(name = "apo_role_modulo_submodulo")
public class RoleModuloSubModuloAPO implements Serializable {

    private static final long serialVersionUID = 1213123123L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
   
    @Basic(optional = false)
    @Column(name = "id_submodulo")
    private int idSubmodulo;
    
    @Basic(optional = false)
    @Column(name = "id_modulo")
    private int idModulo;
    
    @Basic(optional = false)
    @Column(name = "id_role")
    private int idRole;

	public RoleModuloSubModuloAPO() {
		super();
	}

	public RoleModuloSubModuloAPO(Long id, int idSubmodulo, int idModulo, int idRole) {
		super();
		this.id = id;
		this.idSubmodulo = idSubmodulo;
		this.idModulo = idModulo;
		this.idRole = idRole;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getIdSubmodulo() {
		return idSubmodulo;
	}

	public void setIdSubmodulo(int idSubmodulo) {
		this.idSubmodulo = idSubmodulo;
	}

	public int getIdModulo() {
		return idModulo;
	}

	public void setIdModulo(int idModulo) {
		this.idModulo = idModulo;
	}

	public int getIdRole() {
		return idRole;
	}

	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "RoleModuloSubModuloAPO [id=" + id + ", idSubmodulo=" + idSubmodulo + ", idModulo=" + idModulo
				+ ", idRole=" + idRole + "]";
	}
    
}

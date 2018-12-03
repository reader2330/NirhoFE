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
import javax.persistence.Table;

/**
 *
 * @author alfredo.ca
 */
@Entity
@Table(name = "empleado_idioma")
public class EmpleadoIdioma implements Serializable {

    private static final long serialVersionUID = 1453345L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "idioma")
    private int idioma;
    
    @Basic(optional = false)
    @Column(name = "nivel")
    private int nivel;
    
    @Basic(optional = false)
    @Column(name = "habilidades")
    private int habilidades;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getIdioma() {
		return idioma;
	}

	public void setIdioma(int idioma) {
		this.idioma = idioma;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(int habilidades) {
		this.habilidades = habilidades;
	}

	@Override
	public String toString() {
		return "EmpleadoIdioma [id=" + id + ", idioma=" + idioma + ", nivel=" + nivel + ", habilidades=" + habilidades
				+ "]";
	}

}

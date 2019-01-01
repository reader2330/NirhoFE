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

@Entity
@Table(name = "idioma_candidato")
public class IdiomaCandidato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "id_idioma")
    private int idIdioma;
    @Basic(optional = false)
    @Column(name = "nivel")
    private int nivel;
    

    public IdiomaCandidato() {
    }

    public IdiomaCandidato(Long id) {
        this.id = id;
    }

    public IdiomaCandidato(Long id, String nombre, int idIdioma, int nivel) {
        this.id = id;
        this.nombre = nombre;
        this.idIdioma = idIdioma;
        this.nivel = nivel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescIdioma() {
        return nombre;
    }

    public void setDescIdioma(String nombre) {
        this.nombre = nombre;
    }

    public int getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(int idIdioma) {
        this.idIdioma = idIdioma;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

	@Override
	public String toString() {
		return "IdiomaCandidato [id=" + id + ", nombre=" + nombre + ", idIdioma=" + idIdioma + ", nivel=" + nivel + "]";
	}

}

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
@Table(name = "contacto_candidato")
public class ContactoCandidato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "nombre_contacto")
    private String nombreContacto;
    @Basic(optional = false)
    @Column(name = "tipo_contacto")
    private int tipoContacto;
    @JoinColumn(name = "id_candidato", referencedColumnName = "id")
    @ManyToOne
    private Candidato candidato;

    public ContactoCandidato() {
    }

    public ContactoCandidato(Long id) {
        this.id = id;
    }

    public ContactoCandidato(Long id, String nombreContacto, int tipoContacto) {
        this.id = id;
        this.nombreContacto = nombreContacto;
        this.tipoContacto = tipoContacto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public int getTipoContacto() {
        return tipoContacto;
    }

    public void setTipoContacto(int tipoContacto) {
        this.tipoContacto = tipoContacto;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }
    
	@Override
	public String toString() {
		return "ContactoCandidato [id=" + id + ", nombreContacto=" + nombreContacto + ", tipoContacto=" + tipoContacto
				+ ", candidato=" + candidato + "]";
	}
	
}

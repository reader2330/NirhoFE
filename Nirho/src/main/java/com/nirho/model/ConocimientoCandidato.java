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
@Table(name = "conocimiento_candidato")
public class ConocimientoCandidato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "descripcion_conocimiento")
    private String descripcionConocimiento;
    @Basic(optional = false)
    @Column(name = "nivel")
    private int nivel;
    @Basic(optional = false)
    @Column(name = "nombre_conocimiento")
    private String nombreConocimiento;
    @JoinColumn(name = "id_candidato", referencedColumnName = "id")
    @ManyToOne
    private Candidato candidato;

    public ConocimientoCandidato() {
    }

    public ConocimientoCandidato(Long id) {
        this.id = id;
    }

    public ConocimientoCandidato(Long id, String descripcionConocimiento, int nivel, String nombreConocimiento) {
        this.id = id;
        this.descripcionConocimiento = descripcionConocimiento;
        this.nivel = nivel;
        this.nombreConocimiento = nombreConocimiento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcionConocimiento() {
        return descripcionConocimiento;
    }

    public void setDescripcionConocimiento(String descripcionConocimiento) {
        this.descripcionConocimiento = descripcionConocimiento;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getNombreConocimiento() {
        return nombreConocimiento;
    }

    public void setNombreConocimiento(String nombreConocimiento) {
        this.nombreConocimiento = nombreConocimiento;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

	@Override
	public String toString() {
		return "ConocimientoCandidato [id=" + id + ", descripcionConocimiento=" + descripcionConocimiento + ", nivel="
				+ nivel + ", nombreConocimiento=" + nombreConocimiento + ", candidato=" + candidato + "]";
	}
	        
}

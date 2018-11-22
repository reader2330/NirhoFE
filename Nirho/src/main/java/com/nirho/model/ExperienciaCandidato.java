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
@Table(name = "experiencia_candidato")
public class ExperienciaCandidato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "desc_proyecto")
    private String descProyecto;
    @Column(name = "fecha_fin")
    private String fechaFin;
    @Basic(optional = false)
    @Column(name = "fecha_ini")
    private String fechaIni;
    @Column(name = "herramientas")
    private String herramientas;
    @Column(name = "proyecto")
    private String proyecto;
    @Column(name = "puesto")
    private String puesto;
    @Column(name = "recomendacion")
    private String recomendacion;
    @JoinColumn(name = "id_candidato", referencedColumnName = "id")
    @ManyToOne
    private Candidato candidato;

    public ExperienciaCandidato() {
    }

    public ExperienciaCandidato(Long id) {
        this.id = id;
    }

    public ExperienciaCandidato(Long id, String fechaIni) {
        this.id = id;
        this.fechaIni = fechaIni;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescProyecto() {
        return descProyecto;
    }

    public void setDescProyecto(String descProyecto) {
        this.descProyecto = descProyecto;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(String fechaIni) {
        this.fechaIni = fechaIni;
    }

    public String getHerramientas() {
        return herramientas;
    }

    public void setHerramientas(String herramientas) {
        this.herramientas = herramientas;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getRecomendacion() {
        return recomendacion;
    }

    public void setRecomendacion(String recomendacion) {
        this.recomendacion = recomendacion;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

	@Override
	public String toString() {
		return "ExperienciaCandidato [id=" + id + ", descProyecto=" + descProyecto + ", fechaFin=" + fechaFin
				+ ", fechaIni=" + fechaIni + ", herramientas=" + herramientas + ", proyecto=" + proyecto + ", puesto="
				+ puesto + ", recomendacion=" + recomendacion + ", candidato=" + candidato + "]";
	}
	
}

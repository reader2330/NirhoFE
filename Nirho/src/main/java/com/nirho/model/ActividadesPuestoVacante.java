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
@Table(name = "actividades_puesto_vacante")
public class ActividadesPuestoVacante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "descripcion_act_puesto_vacante")
    private String descripcionActPuestoVacante;
    @Basic(optional = false)
    @Column(name = "nivel")
    private int nivel;
    @Basic(optional = false)
    @Column(name = "nombre_act_puesto_vacante")
    private String nombreActPuestoVacante;
    @JoinColumn(name = "id_vacante", referencedColumnName = "id")
    @ManyToOne
    private Vacante vacante;

    public ActividadesPuestoVacante() {
    }

    public ActividadesPuestoVacante(Long id) {
        this.id = id;
    }

    public ActividadesPuestoVacante(Long id, String descripcionActPuestoVacante, int nivel, String nombreActPuestoVacante) {
        this.id = id;
        this.descripcionActPuestoVacante = descripcionActPuestoVacante;
        this.nivel = nivel;
        this.nombreActPuestoVacante = nombreActPuestoVacante;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcionActPuestoVacante() {
        return descripcionActPuestoVacante;
    }

    public void setDescripcionActPuestoVacante(String descripcionActPuestoVacante) {
        this.descripcionActPuestoVacante = descripcionActPuestoVacante;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getNombreActPuestoVacante() {
        return nombreActPuestoVacante;
    }

    public void setNombreActPuestoVacante(String nombreActPuestoVacante) {
        this.nombreActPuestoVacante = nombreActPuestoVacante;
    }

    public Vacante getVacante() {
        return vacante;
    }

    public void setVacante(Vacante vacante) {
        this.vacante = vacante;
    }

	@Override
	public String toString() {
		return "ActividadesPuestoVacante [id=" + id + ", descripcionActPuestoVacante=" + descripcionActPuestoVacante
				+ ", nivel=" + nivel + ", nombreActPuestoVacante=" + nombreActPuestoVacante + ", vacante=" + vacante
				+ "]";
	}
       
}

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
@Table(name = "conocimiento_vacante")
public class ConocimientoVacante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "descripcion_conocimiento_vacante")
    private String descripcionConocimientoVacante;
    @Basic(optional = false)
    @Column(name = "nivel")
    private int nivel;
    @Basic(optional = false)
    @Column(name = "nombre_conocimiento_vacante")
    private String nombreConocimientoVacante;
    @JoinColumn(name = "id_vacante", referencedColumnName = "id")
    @ManyToOne
    private Vacante vacante;

    public ConocimientoVacante() {
    }

    public ConocimientoVacante(Long id) {
        this.id = id;
    }

    public ConocimientoVacante(Long id, String descripcionConocimientoVacante, int nivel, String nombreConocimientoVacante) {
        this.id = id;
        this.descripcionConocimientoVacante = descripcionConocimientoVacante;
        this.nivel = nivel;
        this.nombreConocimientoVacante = nombreConocimientoVacante;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcionConocimientoVacante() {
        return descripcionConocimientoVacante;
    }

    public void setDescripcionConocimientoVacante(String descripcionConocimientoVacante) {
        this.descripcionConocimientoVacante = descripcionConocimientoVacante;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getNombreConocimientoVacante() {
        return nombreConocimientoVacante;
    }

    public void setNombreConocimientoVacante(String nombreConocimientoVacante) {
        this.nombreConocimientoVacante = nombreConocimientoVacante;
    }

    public Vacante getVacante() {
        return vacante;
    }

    public void setVacante(Vacante vacante) {
        this.vacante = vacante;
    }

	@Override
	public String toString() {
		return "ConocimientoVacante [id=" + id + ", descripcionConocimientoVacante=" + descripcionConocimientoVacante
				+ ", nivel=" + nivel + ", nombreConocimientoVacante=" + nombreConocimientoVacante + ", vacante="
				+ vacante + "]";
	}
	        
}

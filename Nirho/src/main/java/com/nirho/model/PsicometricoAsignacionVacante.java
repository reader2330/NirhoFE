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
@Table(name = "psicometrico_asignacion_vacante")
public class PsicometricoAsignacionVacante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "fecha")
    private String fecha;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "observaciones_cliente")
    private String observacionesCliente;
    @JoinColumn(name = "id_asignacion_consultor", referencedColumnName = "id")
    @ManyToOne
    private AsignacionConsultorVacante asignacionConsultorVacante;

    public PsicometricoAsignacionVacante() {
    }

    public PsicometricoAsignacionVacante(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getObservacionesCliente() {
        return observacionesCliente;
    }

    public void setObservacionesCliente(String observacionesCliente) {
        this.observacionesCliente = observacionesCliente;
    }

    public AsignacionConsultorVacante getAsignacionConsultorVacante() {
        return asignacionConsultorVacante;
    }

    public void setAsignacionConsultorVacante(AsignacionConsultorVacante asignacionConsultorVacante) {
        this.asignacionConsultorVacante = asignacionConsultorVacante;
    }

	@Override
	public String toString() {
		return "PsicometricoAsignacionVacante [id=" + id + ", fecha=" + fecha + ", nombre=" + nombre
				+ ", observacionesCliente=" + observacionesCliente + ", asignacionConsultorVacante="
				+ asignacionConsultorVacante + "]";
	}
    
}

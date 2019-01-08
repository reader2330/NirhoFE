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
@Table(name = "contratacion_vacante")
public class ContratacionVacante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "jornada")
    private String jornada;
    @Basic(optional = false)
    @Column(name = "prestaciones")
    private int prestaciones;
    @Basic(optional = false)
    @Column(name = "sueldo")
    private String sueldo;
    @Basic(optional = false)
    @Column(name = "tipo_contrato")
    private int tipoContrato;
    
    @Column(name = "idVacante")
    private long idVacante;
    @Column(name = "idCandidato")
    private long idCandidato;
    

    public ContratacionVacante() {
    }

    public ContratacionVacante(Long id) {
        this.id = id;
    }

    public ContratacionVacante(Long id, String jornada, int prestaciones, String sueldo, int tipoContrato) {
        this.id = id;
        this.jornada = jornada;
        this.prestaciones = prestaciones;
        this.sueldo = sueldo;
        this.tipoContrato = tipoContrato;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getIdVacante() {
		return idVacante;
	}

	public void setIdVacante(long idVacante) {
		this.idVacante = idVacante;
	}

	public long getIdCandidato() {
		return idCandidato;
	}

	public void setIdCandidato(long idCandidato) {
		this.idCandidato = idCandidato;
	}

	public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    public int getPrestaciones() {
        return prestaciones;
    }

    public void setPrestaciones(int prestaciones) {
        this.prestaciones = prestaciones;
    }

    public String getSueldo() {
        return sueldo;
    }

    public void setSueldo(String sueldo) {
        this.sueldo = sueldo;
    }

    public int getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(int tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

	@Override
	public String toString() {
		return "ContratacionVacante [id=" + id + ", jornada=" + jornada + ", prestaciones=" + prestaciones + ", sueldo="
				+ sueldo + ", tipoContrato=" + tipoContrato + ", idVacante=" + idVacante + ", idCandidato="
				+ idCandidato + "]";
	}
	
}

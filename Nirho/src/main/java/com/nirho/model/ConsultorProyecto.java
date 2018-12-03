/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nirho.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author eisten
 */
@Entity
@Table(name = "consultor_proyecto")
public class ConsultorProyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ConsultorProyectoPK consultorProyectoPK;
    @Column(name = "avance")
    private Integer avance;
    @Basic(optional = false)
    @Column(name = "asignado")
    private int asignado;
    @JoinColumn(name = "id_proyecto", referencedColumnName = "id_proyecto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Proyecto proyecto;

    public ConsultorProyecto() {
    }

    public ConsultorProyecto(ConsultorProyectoPK consultorProyectoPK) {
        this.consultorProyectoPK = consultorProyectoPK;
    }

    public ConsultorProyecto(ConsultorProyectoPK consultorProyectoPK, int asignado) {
        this.consultorProyectoPK = consultorProyectoPK;
        this.asignado = asignado;
    }

    public ConsultorProyecto(int idUsuario, int idProyecto) {
        this.consultorProyectoPK = new ConsultorProyectoPK(idUsuario, idProyecto);
    }

    public ConsultorProyectoPK getConsultorProyectoPK() {
        return consultorProyectoPK;
    }

    public void setConsultorProyectoPK(ConsultorProyectoPK consultorProyectoPK) {
        this.consultorProyectoPK = consultorProyectoPK;
    }

    public Integer getAvance() {
        return avance;
    }

    public void setAvance(Integer avance) {
        this.avance = avance;
    }

    public int getAsignado() {
        return asignado;
    }

    public void setAsignado(int asignado) {
        this.asignado = asignado;
    }
    
	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}
	
	@Override
	public String toString() {
		return "ConsultorProyecto [consultorProyectoPK=" + consultorProyectoPK + ", avance=" + avance + ", asignado="
				+ asignado + "]";
	}
        
}

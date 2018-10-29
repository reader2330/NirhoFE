/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nirho.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author eisten
 */
@Embeddable
public class ConsultorProyectoPK implements Serializable {
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
    @Column(name = "id_usuario")
    private int idUsuario;
    @Basic(optional = false)
    @Column(name = "id_proyecto")
    private int idProyecto;

    public ConsultorProyectoPK() {
    }

    public ConsultorProyectoPK(int idUsuario, int idProyecto) {
        this.idUsuario = idUsuario;
        this.idProyecto = idProyecto;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

	@Override
	public String toString() {
		return "ConsultorProyectoPK [idUsuario=" + idUsuario + ", idProyecto=" + idProyecto + "]";
	}
	
}

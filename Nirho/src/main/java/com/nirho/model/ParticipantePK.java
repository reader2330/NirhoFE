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
 * @author DELL
 */
@Embeddable
public class ParticipantePK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
    @Column(name = "id_participante")
    private int idParticipante;
    @Basic(optional = false)
    @Column(name = "id_proyecto")
    private int idProyecto;

    public ParticipantePK() {
    }

    public ParticipantePK(int idParticipante, int idProyecto) {
        this.idParticipante = idParticipante;
        this.idProyecto = idProyecto;
    }

    public int getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(int idParticipante) {
        this.idParticipante = idParticipante;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idParticipante;
        hash += (int) idProyecto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParticipantePK)) {
            return false;
        }
        ParticipantePK other = (ParticipantePK) object;
        if (this.idParticipante != other.idParticipante) {
            return false;
        }
        if (this.idProyecto != other.idProyecto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nirho.model.ParticipantePK[ idParticipante=" + idParticipante + ", idProyecto=" + idProyecto + " ]";
    }
    
}

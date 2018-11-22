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
public class RespuestaPreguntaPK implements Serializable {
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
    @Column(name = "id_participante")
    private int idParticipante;
    @Basic(optional = false)
    @Column(name = "id_pregunta")
    private int idPregunta;

    public RespuestaPreguntaPK() {
    }

    public RespuestaPreguntaPK(int idParticipante, int idPregunta) {
        this.idParticipante = idParticipante;
        this.idPregunta = idPregunta;
    }

    public int getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(int idParticipante) {
        this.idParticipante = idParticipante;
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

	@Override
	public String toString() {
		return "RespuestaPreguntaPK [idParticipante=" + idParticipante + ", idPregunta=" + idPregunta + "]";
	}
    
}

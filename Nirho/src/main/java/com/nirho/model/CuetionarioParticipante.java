/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nirho.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author eisten
 */
@Entity
@Table(name = "cuetionario_participante")
@NamedQueries({
    @NamedQuery(name = "CuetionarioParticipante.findAll", query = "SELECT c FROM CuetionarioParticipante c")})
public class CuetionarioParticipante implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CuetionarioParticipantePK cuetionarioParticipantePK;
    @Column(name = "respuesta")
    private Integer respuesta;
    @Column(name = "respuesta_texto")
    private String respuestaTexto;
    @JoinColumn(name = "id_participante", referencedColumnName = "id_participante", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Participante participante;
    @JoinColumn(name = "id_pregunta", referencedColumnName = "id_pregunta", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pregunta pregunta;
    @JoinColumn(name = "id_tema", referencedColumnName = "id_tema", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tema tema;

    public CuetionarioParticipante() {
    }

    public CuetionarioParticipante(CuetionarioParticipantePK cuetionarioParticipantePK) {
        this.cuetionarioParticipantePK = cuetionarioParticipantePK;
    }

    public CuetionarioParticipante(int idParticipante, int idTema, int idPregunta) {
        this.cuetionarioParticipantePK = new CuetionarioParticipantePK(idParticipante, idTema, idPregunta);
    }

    public CuetionarioParticipantePK getCuetionarioParticipantePK() {
        return cuetionarioParticipantePK;
    }

    public void setCuetionarioParticipantePK(CuetionarioParticipantePK cuetionarioParticipantePK) {
        this.cuetionarioParticipantePK = cuetionarioParticipantePK;
    }

    public Integer getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Integer respuesta) {
        this.respuesta = respuesta;
    }

    public String getRespuestaTexto() {
        return respuestaTexto;
    }

    public void setRespuestaTexto(String respuestaTexto) {
        this.respuestaTexto = respuestaTexto;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

	@Override
	public String toString() {
		return "CuetionarioParticipante [cuetionarioParticipantePK=" + cuetionarioParticipantePK + ", respuesta="
				+ respuesta + ", respuestaTexto=" + respuestaTexto + ", participante=" + participante + ", pregunta="
				+ pregunta + ", tema=" + tema + "]";
	}
}

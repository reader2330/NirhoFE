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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author DELL
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
    @Column(name = "respuesta_rh")
    private Integer respuestaRh;
    @Column(name = "respuesta_jefe")
    private Integer respuestaJefe;
    @JoinColumns({
        @JoinColumn(name = "id_participante", referencedColumnName = "id_participante", insertable = false, updatable = false)
        , @JoinColumn(name = "id_proyecto", referencedColumnName = "id_proyecto", insertable = false, updatable = false)})
    @OneToOne(optional = false)
    private Participante participante;
    @JoinColumn(name = "id_pregunta", referencedColumnName = "id_pregunta", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pregunta idPregunta;
    @JoinColumn(name = "id_tema", referencedColumnName = "id_tema", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tema idTema;
    
    public CuetionarioParticipante() {
    }

    public CuetionarioParticipante(CuetionarioParticipantePK cuetionarioParticipantePK) {
        this.cuetionarioParticipantePK = cuetionarioParticipantePK;
    }

    public CuetionarioParticipante(int idParticipante, int idProyecto, int idTema, int idPregunta) {
        this.cuetionarioParticipantePK = new CuetionarioParticipantePK(idParticipante, idProyecto, idTema, idPregunta);
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

	public Integer getRespuestaRh() {
		return respuestaRh;
	}

	public void setRespuestaRh(Integer respuestaRh) {
		this.respuestaRh = respuestaRh;
	}

	public Integer getRespuestaJefe() {
		return respuestaJefe;
	}

	public void setRespuestaJefe(Integer respuestaJefe) {
		this.respuestaJefe = respuestaJefe;
	}

	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

	public Pregunta getIdPregunta() {
		return idPregunta;
	}

	public void setIdPregunta(Pregunta idPregunta) {
		this.idPregunta = idPregunta;
	}

	public Tema getIdTema() {
		return idTema;
	}

	public void setIdTema(Tema idTema) {
		this.idTema = idTema;
	}

	@Override
	public String toString() {
		return "CuetionarioParticipante [cuetionarioParticipantePK=" + cuetionarioParticipantePK + ", respuesta="
				+ respuesta + ", respuestaRh=" + respuestaRh + ", respuestaJefe=" + respuestaJefe + ", participante="
				+ participante + ", idPregunta=" + idPregunta + ", idTema=" + idTema + "]";
	}
	
}

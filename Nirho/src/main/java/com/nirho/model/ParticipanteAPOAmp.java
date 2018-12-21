/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nirho.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "participanteAPOAmp")
@NamedQueries({
    @NamedQuery(name = "ParticipanteAPOAmp.findAll", query = "SELECT p FROM ParticipanteAPOAmp p")})
public class ParticipanteAPOAmp implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
    
    @Column(name = "id_participante")
    private Integer idParticipante;
    
    @Column(name = "objetivo_puesto", nullable=true)
    private String objetivoPuesto;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "participante_apo_amp")
	private List<ParticipanteAPOAmpFuncion> funciones = new ArrayList<>();
    
    public ParticipanteAPOAmp() {
		super();
	}


	public ParticipanteAPOAmp(Integer id, Integer idParticipante, String objetivoPuesto,
			List<ParticipanteAPOAmpFuncion> funciones) {
		super();
		this.id = id;
		this.idParticipante = idParticipante;
		this.objetivoPuesto = objetivoPuesto;
		this.funciones = funciones;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getIdParticipante() {
		return idParticipante;
	}

	public void setIdParticipante(Integer idParticipante) {
		this.idParticipante = idParticipante;
	}

	public String getObjetivoPuesto() {
        return objetivoPuesto;
    }

    public void setObjetivoPuesto(String objetivoPuesto) {
        this.objetivoPuesto = objetivoPuesto;
    }

	public List<ParticipanteAPOAmpFuncion> getFunciones() {
		return funciones;
	}

	public void setFunciones(List<ParticipanteAPOAmpFuncion> funciones) {
		this.funciones = funciones;
	}

	@Override
	public String toString() {
		return "ParticipanteAPOAmp [id=" + id + ", idParticipante=" + idParticipante + ", objetivoPuesto="
				+ objetivoPuesto + ", funciones=" + funciones + "]";
	}

    
}

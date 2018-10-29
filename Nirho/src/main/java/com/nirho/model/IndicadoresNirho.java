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
import javax.persistence.Table;

/**
 *
 * @author eisten
 */
@Entity
@Table(name = "indicadores_nirho")
public class IndicadoresNirho implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "asignada_candidato")
    private int asignadaCandidato;
    @Basic(optional = false)
    @Column(name = "asignada_consultor_nirho")
    private int asignadaConsultorNirho;
    @Basic(optional = false)
    @Column(name = "asignada_entrevista")
    private int asignadaEntrevista;
    @Basic(optional = false)
    @Column(name = "cancelada")
    private int cancelada;
    @Basic(optional = false)
    @Column(name = "en_garantia")
    private int enGarantia;
    @Basic(optional = false)
    @Column(name = "en_registro")
    private int enRegistro;
    @Basic(optional = false)
    @Column(name = "guardada")
    private int guardada;
    @Basic(optional = false)
    @Column(name = "publicada_red_social")
    private int publicadaRedSocial;
    @Basic(optional = false)
    @Column(name = "recibida")
    private int recibida;
    @Basic(optional = false)
    @Column(name = "terminada")
    private int terminada;
    @Basic(optional = false)
    @Column(name = "total_vacantes")
    private int totalVacantes;

    public IndicadoresNirho() {
    }

    public IndicadoresNirho(Long id) {
        this.id = id;
    }

    public IndicadoresNirho(Long id, int asignadaCandidato, int asignadaConsultorNirho, int asignadaEntrevista, int cancelada, int enGarantia, int enRegistro, int guardada, int publicadaRedSocial, int recibida, int terminada, int totalVacantes) {
        this.id = id;
        this.asignadaCandidato = asignadaCandidato;
        this.asignadaConsultorNirho = asignadaConsultorNirho;
        this.asignadaEntrevista = asignadaEntrevista;
        this.cancelada = cancelada;
        this.enGarantia = enGarantia;
        this.enRegistro = enRegistro;
        this.guardada = guardada;
        this.publicadaRedSocial = publicadaRedSocial;
        this.recibida = recibida;
        this.terminada = terminada;
        this.totalVacantes = totalVacantes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAsignadaCandidato() {
        return asignadaCandidato;
    }

    public void setAsignadaCandidato(int asignadaCandidato) {
        this.asignadaCandidato = asignadaCandidato;
    }

    public int getAsignadaConsultorNirho() {
        return asignadaConsultorNirho;
    }

    public void setAsignadaConsultorNirho(int asignadaConsultorNirho) {
        this.asignadaConsultorNirho = asignadaConsultorNirho;
    }

    public int getAsignadaEntrevista() {
        return asignadaEntrevista;
    }

    public void setAsignadaEntrevista(int asignadaEntrevista) {
        this.asignadaEntrevista = asignadaEntrevista;
    }

    public int getCancelada() {
        return cancelada;
    }

    public void setCancelada(int cancelada) {
        this.cancelada = cancelada;
    }

    public int getEnGarantia() {
        return enGarantia;
    }

    public void setEnGarantia(int enGarantia) {
        this.enGarantia = enGarantia;
    }

    public int getEnRegistro() {
        return enRegistro;
    }

    public void setEnRegistro(int enRegistro) {
        this.enRegistro = enRegistro;
    }

    public int getGuardada() {
        return guardada;
    }

    public void setGuardada(int guardada) {
        this.guardada = guardada;
    }

    public int getPublicadaRedSocial() {
        return publicadaRedSocial;
    }

    public void setPublicadaRedSocial(int publicadaRedSocial) {
        this.publicadaRedSocial = publicadaRedSocial;
    }

    public int getRecibida() {
        return recibida;
    }

    public void setRecibida(int recibida) {
        this.recibida = recibida;
    }

    public int getTerminada() {
        return terminada;
    }

    public void setTerminada(int terminada) {
        this.terminada = terminada;
    }

    public int getTotalVacantes() {
        return totalVacantes;
    }

    public void setTotalVacantes(int totalVacantes) {
        this.totalVacantes = totalVacantes;
    }

	@Override
	public String toString() {
		return "IndicadoresNirho [id=" + id + ", asignadaCandidato=" + asignadaCandidato + ", asignadaConsultorNirho="
				+ asignadaConsultorNirho + ", asignadaEntrevista=" + asignadaEntrevista + ", cancelada=" + cancelada
				+ ", enGarantia=" + enGarantia + ", enRegistro=" + enRegistro + ", guardada=" + guardada
				+ ", publicadaRedSocial=" + publicadaRedSocial + ", recibida=" + recibida + ", terminada=" + terminada
				+ ", totalVacantes=" + totalVacantes + "]";
	}
    
}

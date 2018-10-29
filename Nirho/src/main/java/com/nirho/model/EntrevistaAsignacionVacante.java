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
@Table(name = "entrevista_asignacion_vacante")
public class EntrevistaAsignacionVacante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "encargado_entrevista")
    private String encargadoEntrevista;
    @Column(name = "fecha_entrevista")
    private String fechaEntrevista;
    @Column(name = "hora_final")
    private String horaFinal;
    @Column(name = "hora_inicial")
    private String horaInicial;
    @Column(name = "observaciones_cliente")
    private String observacionesCliente;
    @Basic(optional = false)
    @Column(name = "tipo_entrevista")
    private int tipoEntrevista;
    @Column(name = "titulo")
    private String titulo;
    @JoinColumn(name = "id_asignacion_consultor", referencedColumnName = "id")
    @ManyToOne
    private AsignacionConsultorVacante asignacionConsultorVacante;

    public EntrevistaAsignacionVacante() {
    }

    public EntrevistaAsignacionVacante(Long id) {
        this.id = id;
    }

    public EntrevistaAsignacionVacante(Long id, int tipoEntrevista) {
        this.id = id;
        this.tipoEntrevista = tipoEntrevista;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEncargadoEntrevista() {
        return encargadoEntrevista;
    }

    public void setEncargadoEntrevista(String encargadoEntrevista) {
        this.encargadoEntrevista = encargadoEntrevista;
    }

    public String getFechaEntrevista() {
        return fechaEntrevista;
    }

    public void setFechaEntrevista(String fechaEntrevista) {
        this.fechaEntrevista = fechaEntrevista;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }

    public String getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(String horaInicial) {
        this.horaInicial = horaInicial;
    }

    public String getObservacionesCliente() {
        return observacionesCliente;
    }

    public void setObservacionesCliente(String observacionesCliente) {
        this.observacionesCliente = observacionesCliente;
    }

    public int getTipoEntrevista() {
        return tipoEntrevista;
    }

    public void setTipoEntrevista(int tipoEntrevista) {
        this.tipoEntrevista = tipoEntrevista;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public AsignacionConsultorVacante getAsignacionConsultorVacante() {
        return asignacionConsultorVacante;
    }

    public void setAsignacionConsultorVacante(AsignacionConsultorVacante asignacionConsultorVacante) {
        this.asignacionConsultorVacante = asignacionConsultorVacante;
    }

	@Override
	public String toString() {
		return "EntrevistaAsignacionVacante [id=" + id + ", direccion=" + direccion + ", encargadoEntrevista="
				+ encargadoEntrevista + ", fechaEntrevista=" + fechaEntrevista + ", horaFinal=" + horaFinal
				+ ", horaInicial=" + horaInicial + ", observacionesCliente=" + observacionesCliente
				+ ", tipoEntrevista=" + tipoEntrevista + ", titulo=" + titulo + ", asignacionConsultorVacante="
				+ asignacionConsultorVacante + "]";
	}
	    
}

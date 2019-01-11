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

@Entity
@Table(name = "entrevista_asignacion_vacante")
public class EntrevistaVacante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private long id;
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
    @Column(name = "observaciones_consultor")
    private String observacionesConsultor;
    @Column(name = "observaciones_solicitante")
    private String observacionesSolicitante;    
    @Basic(optional = false)
    @Column(name = "tipo_entrevista")
    private String tipoEntrevista;
    @Column(name = "titulo")
    private String titulo;
    
    @Column(name = "idSolicitante")
    private long idSolicitante;
    
    @Column(name = "idConsultor")
    private long idConsultor;
    
    @Column(name = "idCandidato")
    private long idCandidato;
    
    public EntrevistaVacante() {
    }

    public EntrevistaVacante(Long id) {
        this.id = id;
    }

    public EntrevistaVacante(Long id, String tipoEntrevista) {
        this.id = id;
        this.tipoEntrevista = tipoEntrevista;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getObservacionesConsultor() {
		return observacionesConsultor;
	}

	public void setObservacionesConsultor(String observacionesConsultor) {
		this.observacionesConsultor = observacionesConsultor;
	}

	public String getObservacionesSolicitante() {
		return observacionesSolicitante;
	}

	public void setObservacionesSolicitante(String observacionesSolicitante) {
		this.observacionesSolicitante = observacionesSolicitante;
	}

	public String getTipoEntrevista() {
        return tipoEntrevista;
    }

    public void setTipoEntrevista(String tipoEntrevista) {
        this.tipoEntrevista = tipoEntrevista;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

	public long getIdSolicitante() {
		return idSolicitante;
	}

	public void setIdSolicitante(long idSolicitante) {
		this.idSolicitante = idSolicitante;
	}

	public long getIdConsultor() {
		return idConsultor;
	}

	public void setIdConsultor(long idConsultor) {
		this.idConsultor = idConsultor;
	}

	public long getIdCandidato() {
		return idCandidato;
	}

	public void setIdCandidato(long idCandidato) {
		this.idCandidato = idCandidato;
	}

	@Override
	public String toString() {
		return "EntrevistaVacante [id=" + id + ", direccion=" + direccion + ", encargadoEntrevista="
				+ encargadoEntrevista + ", fechaEntrevista=" + fechaEntrevista + ", horaFinal=" + horaFinal
				+ ", horaInicial=" + horaInicial + ", observacionesCliente=" + observacionesCliente
				+ ", observacionesConsultor=" + observacionesConsultor + ", observacionesSolicitante="
				+ observacionesSolicitante + ", tipoEntrevista=" + tipoEntrevista + ", titulo=" + titulo
				+ ", idSolicitante=" + idSolicitante + ", idConsultor=" + idConsultor + ", idCandidato=" + idCandidato
				+ "]";
	}
    
}

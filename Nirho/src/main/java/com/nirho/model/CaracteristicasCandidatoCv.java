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
@Table(name = "caracteristicas_candidato_cv")
public class CaracteristicasCandidatoCv implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "cambio_residencia")
    private String cambioResidencia;
    @Basic(optional = false)
    @Column(name = "caract_adicionales")
    private String caractAdicionales;
    @Basic(optional = false)
    @Column(name = "carrera")
    private String carrera;
    @Basic(optional = false)
    @Column(name = "certificaciones")
    private String certificaciones;
    @Basic(optional = false)
    @Column(name = "cursos")
    private String cursos;
    @Basic(optional = false)
    @Column(name = "discapacitados")
    private String discapacitados;
    @Basic(optional = false)
    @Column(name = "dispo_viajar")
    private String dispoViajar;
    @Basic(optional = false)
    @Column(name = "edad_rango")
    private int edadRango;
    @Basic(optional = false)
    @Column(name = "especialidad")
    private String especialidad;
    @Basic(optional = false)
    @Column(name = "estado_civil")
    private String estadoCivil;
    @Basic(optional = false)
    @Column(name = "genero")
    private String genero;
    @Basic(optional = false)
    @Column(name = "nivel_estudios")
    private int nivelEstudios;
    @Basic(optional = false)
    @Column(name = "o_capacidades")
    private String oCapacidades;
    @Basic(optional = false)
    @Column(name = "oficios")
    private String oficios;
    @Basic(optional = false)
    @Column(name = "titulo")
    private String titulo;
    @JoinColumn(name = "id_candidato", referencedColumnName = "id")
    @ManyToOne
    private Candidato candidato;

    public CaracteristicasCandidatoCv() {
    }

    public CaracteristicasCandidatoCv(Long id) {
        this.id = id;
    }

    public CaracteristicasCandidatoCv(Long id, String cambioResidencia, String caractAdicionales, String carrera, String certificaciones, String cursos, String discapacitados, String dispoViajar, int edadRango, String especialidad, String estadoCivil, String genero, int nivelEstudios, String oCapacidades, String oficios, String titulo) {
        this.id = id;
        this.cambioResidencia = cambioResidencia;
        this.caractAdicionales = caractAdicionales;
        this.carrera = carrera;
        this.certificaciones = certificaciones;
        this.cursos = cursos;
        this.discapacitados = discapacitados;
        this.dispoViajar = dispoViajar;
        this.edadRango = edadRango;
        this.especialidad = especialidad;
        this.estadoCivil = estadoCivil;
        this.genero = genero;
        this.nivelEstudios = nivelEstudios;
        this.oCapacidades = oCapacidades;
        this.oficios = oficios;
        this.titulo = titulo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCambioResidencia() {
        return cambioResidencia;
    }

    public void setCambioResidencia(String cambioResidencia) {
        this.cambioResidencia = cambioResidencia;
    }

    public String getCaractAdicionales() {
        return caractAdicionales;
    }

    public void setCaractAdicionales(String caractAdicionales) {
        this.caractAdicionales = caractAdicionales;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getCertificaciones() {
        return certificaciones;
    }

    public void setCertificaciones(String certificaciones) {
        this.certificaciones = certificaciones;
    }

    public String getCursos() {
        return cursos;
    }

    public void setCursos(String cursos) {
        this.cursos = cursos;
    }

    public String getDiscapacitados() {
        return discapacitados;
    }

    public void setDiscapacitados(String discapacitados) {
        this.discapacitados = discapacitados;
    }

    public String getDispoViajar() {
        return dispoViajar;
    }

    public void setDispoViajar(String dispoViajar) {
        this.dispoViajar = dispoViajar;
    }

    public int getEdadRango() {
        return edadRango;
    }

    public void setEdadRango(int edadRango) {
        this.edadRango = edadRango;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getNivelEstudios() {
        return nivelEstudios;
    }

    public void setNivelEstudios(int nivelEstudios) {
        this.nivelEstudios = nivelEstudios;
    }

    public String getOCapacidades() {
        return oCapacidades;
    }

    public void setOCapacidades(String oCapacidades) {
        this.oCapacidades = oCapacidades;
    }

    public String getOficios() {
        return oficios;
    }

    public void setOficios(String oficios) {
        this.oficios = oficios;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

	@Override
	public String toString() {
		return "CaracteristicasCandidatoCv [id=" + id + ", cambioResidencia=" + cambioResidencia
				+ ", caractAdicionales=" + caractAdicionales + ", carrera=" + carrera + ", certificaciones="
				+ certificaciones + ", cursos=" + cursos + ", discapacitados=" + discapacitados + ", dispoViajar="
				+ dispoViajar + ", edadRango=" + edadRango + ", especialidad=" + especialidad + ", estadoCivil="
				+ estadoCivil + ", genero=" + genero + ", nivelEstudios=" + nivelEstudios + ", oCapacidades="
				+ oCapacidades + ", oficios=" + oficios + ", titulo=" + titulo + ", candidato=" + candidato + "]";
	}
    
}

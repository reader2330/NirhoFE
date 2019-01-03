/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nirho.model;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;


@Entity
@Table(name = "caracteristicas_candidato_cv")
public class CaracteristicasCandidatoCv implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private long id;
    
    @Column(name = "genero")
    private String genero;
    
    @Column(name = "estado_civil")
    private String estadoCivil;
    
    @Column(name = "dispo_viajar")
    private String dispoViajar;
    
    @Column(name = "cambio_residencia")
    private String cambioResidencia;
    
    @Column(name = "necesidades_especiales")
    private String necesidadesEspeciales;
    
    @Column(name = "min_edad")
    private int minEdad;
    
    @Column(name = "max_edad")
    private int maxEdad;
    
    @Column(name = "caract_adicionales")
    private String caractAdicionales;
    
 
    @Column(name = "grado_estudios")
    private String gradoEstudios;
    
    @Column(name = "institucion")
    private String institucion;
    
    @Basic(optional = false)
    @Column(name = "titulo")
    private boolean titulo;

    @Column(name = "carrera")
    private String carrera;
    
    @Column(name = "especialidad")
    private String especialidad;
    
    @Column(name = "certificaciones")
    private String certificaciones;
    
    @Column(name = "cursos")
    private String cursos;
    
    @Column(name = "oficios")
    private String oficios;
    
    @Column(name = "o_capacidades")
    private String oCapacidades;

	public CaracteristicasCandidatoCv() {
		super();
	}

	public CaracteristicasCandidatoCv(long id, String genero, String estadoCivil, String dispoViajar,
			String cambioResidencia, String necesidadesEspeciales, int minEdad, int maxEdad, String caractAdicionales,
			String gradoEstudios, String institucion, boolean titulo, String carrera, String especialidad,
			String certificaciones, String cursos, String oficios, String oCapacidades) {
		super();
		this.id = id;
		this.genero = genero;
		this.estadoCivil = estadoCivil;
		this.dispoViajar = dispoViajar;
		this.cambioResidencia = cambioResidencia;
		this.necesidadesEspeciales = necesidadesEspeciales;
		this.minEdad = minEdad;
		this.maxEdad = maxEdad;
		this.caractAdicionales = caractAdicionales;
		this.gradoEstudios = gradoEstudios;
		this.institucion = institucion;
		this.titulo = titulo;
		this.carrera = carrera;
		this.especialidad = especialidad;
		this.certificaciones = certificaciones;
		this.cursos = cursos;
		this.oficios = oficios;
		this.oCapacidades = oCapacidades;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getDispoViajar() {
		return dispoViajar;
	}

	public void setDispoViajar(String dispoViajar) {
		this.dispoViajar = dispoViajar;
	}

	public String getCambioResidencia() {
		return cambioResidencia;
	}

	public void setCambioResidencia(String cambioResidencia) {
		this.cambioResidencia = cambioResidencia;
	}

	public String getNecesidadesEspeciales() {
		return necesidadesEspeciales;
	}

	public void setNecesidadesEspeciales(String necesidadesEspeciales) {
		this.necesidadesEspeciales = necesidadesEspeciales;
	}

	

	public int getMinEdad() {
		return minEdad;
	}



	public void setMinEdad(int minEdad) {
		this.minEdad = minEdad;
	}



	public int getMaxEdad() {
		return maxEdad;
	}



	public void setMaxEdad(int maxEdad) {
		this.maxEdad = maxEdad;
	}


	public String getCaractAdicionales() {
		return caractAdicionales;
	}

	public void setCaractAdicionales(String caractAdicionales) {
		this.caractAdicionales = caractAdicionales;
	}

	public String getGradoEstudios() {
		return gradoEstudios;
	}

	public void setGradoEstudios(String gradoEstudios) {
		this.gradoEstudios = gradoEstudios;
	}

	public String getInstitucion() {
		return institucion;
	}

	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}

	public boolean isTitulo() {
		return titulo;
	}

	public void setTitulo(boolean titulo) {
		this.titulo = titulo;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
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

	public String getOficios() {
		return oficios;
	}

	public void setOficios(String oficios) {
		this.oficios = oficios;
	}

	public String getoCapacidades() {
		return oCapacidades;
	}

	public void setoCapacidades(String oCapacidades) {
		this.oCapacidades = oCapacidades;
	}



	@Override
	public String toString() {
		return "CaracteristicasCandidatoVacante [id=" + id + ", genero=" + genero + ", estadoCivil=" + estadoCivil
				+ ", dispoViajar=" + dispoViajar + ", cambioResidencia=" + cambioResidencia + ", necesidadesEspeciales="
				+ necesidadesEspeciales + ", minEdad=" + minEdad + ", maxEdad=" + maxEdad + ", caractAdicionales="
				+ caractAdicionales + ", gradoEstudios=" + gradoEstudios + ", institucion=" + institucion + ", titulo="
				+ titulo + ", carrera=" + carrera + ", especialidad=" + especialidad + ", certificaciones="
				+ certificaciones + ", cursos=" + cursos + ", oficios=" + oficios + ", oCapacidades=" + oCapacidades
				+ "]";
	}

	

}
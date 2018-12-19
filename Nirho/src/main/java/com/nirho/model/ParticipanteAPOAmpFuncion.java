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
@Table(name = "participanteAPOAmpFuncion")
@NamedQueries({
    @NamedQuery(name = "ParticipanteAPOAmpFuncion.findAll", query = "SELECT p FROM ParticipanteAPOAmpFuncion p")})
public class ParticipanteAPOAmpFuncion implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
    
    @Column(name = "funcion", nullable=true)
    private String funcion;
    
    @Column(name = "meta_kpi", nullable=true)
    private String metaKpi;
    
    @Column(name = "cantidad_meta", nullable=true)
    private String cantidadMeta;
    
    @Column(name = "tiempo", nullable=true)
    private String tiempo;
    
    @Column(name = "frecuencia_eval", nullable=true)
    private String frecuenciaEval; 
    
    @Column(name = "id_evaluador")
    private Integer idEvaluador;
    
    @Column(name = "calificacion")
    private Integer calificacion;
        
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "funcion")
	private List<ParticipanteAPOAmpActividad> actividades = new ArrayList<>();
    
	public ParticipanteAPOAmpFuncion() {
		super();
	}

	public ParticipanteAPOAmpFuncion(Integer id, String funcion, String metaKpi, String cantidadMeta, String tiempo,
			String frecuenciaEval, Integer idEvaluador, Integer calificacion,
			List<ParticipanteAPOAmpActividad> actividades) {
		super();
		this.id = id;
		this.funcion = funcion;
		this.metaKpi = metaKpi;
		this.cantidadMeta = cantidadMeta;
		this.tiempo = tiempo;
		this.frecuenciaEval = frecuenciaEval;
		this.idEvaluador = idEvaluador;
		this.calificacion = calificacion;
		this.actividades = actividades;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getFuncion() {
		return funcion;
	}

	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

	public String getMetaKpi() {
		return metaKpi;
	}
	
	
	public void setMetaKpi(String metaKpi) {
		this.metaKpi = metaKpi;
	}
	
	public String getCantidadMeta() {
		return cantidadMeta;
	}
	
	public void setCantidadMeta(String cantidadMeta) {
		this.cantidadMeta = cantidadMeta;
	}
	
	public String getTiempo() {
		return tiempo;
	}
	
	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}
	
	public String getFrecuenciaEval() {
		return frecuenciaEval;
	}
	
	public void setFrecuenciaEval(String frecuenciaEval) {
		this.frecuenciaEval = frecuenciaEval;
	}
	
	public Integer getIdEvaluador() {
		return idEvaluador;
	}
	
	public void setIdEvaluador(Integer idEvaluador) {
		this.idEvaluador = idEvaluador;
	}
	
	public Integer getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Integer calificacion) {
		this.calificacion = calificacion;
	}

	public List<ParticipanteAPOAmpActividad> getActividades() {
		return actividades;
	}

	public void setActividades(List<ParticipanteAPOAmpActividad> actividades) {
		this.actividades = actividades;
	}

	@Override
	public String toString() {
		return "ParticipanteAPOAmpFunciones [id=" + id + ", funcion=" + funcion + ", metaKpi=" + metaKpi
				+ ", cantidadMeta=" + cantidadMeta + ", tiempo=" + tiempo + ", frecuenciaEval=" + frecuenciaEval
				+ ", idEvaluador=" + idEvaluador + ", calificacion=" + calificacion + ", actividades=" + actividades
				+ "]";
	}

	
}

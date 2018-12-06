/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nirho.dto;

import java.io.Serializable;

/**
 *
 * @author eisten
 */
public class ParticipanteHCA implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Integer idParticipante;
    private String objetivoPuesto;
    private String funciones;
    private String actividades;
    private String metaKpi;
    private String cantidadMeta;
    private String unidadMedida;
    private String tiempo;
    private String frecuenciaEval;
    private String idEvaluador;
    
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
	public String getFunciones() {
		return funciones;
	}
	public void setFunciones(String funciones) {
		this.funciones = funciones;
	}
	public String getActividades() {
		return actividades;
	}
	public void setActividades(String actividades) {
		this.actividades = actividades;
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
	public String getUnidadMedida() {
		return unidadMedida;
	}
	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
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
	public String getIdEvaluador() {
		return idEvaluador;
	}
	public void setIdEvaluador(String idEvaluador) {
		this.idEvaluador = idEvaluador;
	}
	 
	@Override
	public String toString() {
		return "ParticipanteHCA [idParticipante=" + idParticipante + ", objetivoPuesto=" + objetivoPuesto
				+ ", funciones=" + funciones + ", actividades=" + actividades + ", metaKpi=" + metaKpi
				+ ", cantidadMeta=" + cantidadMeta + ", unidadMedida=" + unidadMedida + ", tiempo=" + tiempo
				+ ", frecuenciaEval=" + frecuenciaEval + ", idEvaluador=" + idEvaluador + "]";
	}
    
}

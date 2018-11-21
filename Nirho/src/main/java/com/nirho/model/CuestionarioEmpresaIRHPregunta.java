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
@Table(name = "cuestionario_empresa_irh_pregunta")
public class CuestionarioEmpresaIRHPregunta implements Serializable {

    private static final long serialVersionUID = 123423423L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional=false)
    @Column(name = "id_pregunta")
    private Long id;
    @Basic(optional = false)
    @Column(name = "enunciado")
    private String enunciado;
    @Basic(optional = false)
    @Column(name = "tipo")
    private int tipo;
    @Column(name = "respuesta")
    private int respuesta;
    
	public CuestionarioEmpresaIRHPregunta() {
		super();
	}
	
	public CuestionarioEmpresaIRHPregunta(Long id, String enunciado, int tipo, int respuesta) {
		super();
		this.id = id;
		this.enunciado = enunciado;
		this.tipo = tipo;
		this.respuesta = respuesta;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEnunciado() {
		return enunciado;
	}
	
	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}
	
	public int getTipo() {
		return tipo;
	}
	
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	public int getRespuesta() {
		return respuesta;
	}
	
	public void setRespuesta(int respuesta) {
		this.respuesta = respuesta;
	}    
     
}

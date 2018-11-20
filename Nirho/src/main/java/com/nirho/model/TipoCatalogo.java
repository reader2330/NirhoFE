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
@Table(name = "tipo_catalogo")
public class TipoCatalogo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @Column(name = "nombre_tipo_catalogo")
    private String nombreTipoCatalogo;
    
    public TipoCatalogo() {
    }

    public TipoCatalogo(Long id) {
        this.id = id;
    }

    public TipoCatalogo(Long id, int estado, String nombreTipoCatalogo) {
        this.id = id;
        this.estado = estado;
        this.nombreTipoCatalogo = nombreTipoCatalogo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getNombreTipoCatalogo() {
        return nombreTipoCatalogo;
    }

    public void setNombreTipoCatalogo(String nombreTipoCatalogo) {
        this.nombreTipoCatalogo = nombreTipoCatalogo;
    }

	@Override
	public String toString() {
		return "TipoCatalogo [id=" + id + ", estado=" + estado + ", nombreTipoCatalogo=" + nombreTipoCatalogo + "]";
	}
        
}

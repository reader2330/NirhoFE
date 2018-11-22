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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author eisten
 */
@Entity
@Table(name = "catalogo")
@NamedQueries({
	@NamedQuery(name = "Catalogo.findPaisesActivos",
				query = "select p from Catalogo p " +
	    	"where p.tipoCatalogo.id=1 and p.estado=1"),
	@NamedQuery(name = "Catalogo.findGirosEmpresasActivos",
				query = "select p from Catalogo p " +
			"where p.tipoCatalogo.id=9 and p.estado=1")
})
public class Catalogo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "descripcion_catalogo")
    private String descripcionCatalogo;
    @Basic(optional = false)
    @Column(name = "estado")
    private int estado;
    @Column(name = "narrativa_catalogo")
    private String narrativaCatalogo;
    @JoinColumn(name = "id_tipo_catalogo", referencedColumnName = "id")
    @ManyToOne
    private TipoCatalogo tipoCatalogo;

    public Catalogo() {
    }

    public Catalogo(Long id) {
        this.id = id;
    }

    public Catalogo(Long id, String descripcionCatalogo, int estado) {
        this.id = id;
        this.descripcionCatalogo = descripcionCatalogo;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcionCatalogo() {
        return descripcionCatalogo;
    }

    public void setDescripcionCatalogo(String descripcionCatalogo) {
        this.descripcionCatalogo = descripcionCatalogo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getNarrativaCatalogo() {
        return narrativaCatalogo;
    }

    public void setNarrativaCatalogo(String narrativaCatalogo) {
        this.narrativaCatalogo = narrativaCatalogo;
    }

    public TipoCatalogo getTipoCatalogo() {
        return tipoCatalogo;
    }

    public void setTipoCatalogo(TipoCatalogo tipoCatalogo) {
        this.tipoCatalogo = tipoCatalogo;
    }

	@Override
	public String toString() {
		return "Catalogo [id=" + id + ", descripcionCatalogo=" + descripcionCatalogo + ", estado=" + estado
				+ ", narrativaCatalogo=" + narrativaCatalogo + ", tipoCatalogo=" + tipoCatalogo + "]";
	}
	
}

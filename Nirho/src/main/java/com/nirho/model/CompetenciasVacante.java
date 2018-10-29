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
@Table(name = "competencias_vacante")
public class CompetenciasVacante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "desc_competencia")
    private String descCompetencia;
    @Basic(optional = false)
    @Column(name = "id_competencia")
    private int idCompetencia;
    @Basic(optional = false)
    @Column(name = "nivel")
    private int nivel;
    @Basic(optional = false)
    @Column(name = "tipo")
    private int tipo;
    @JoinColumn(name = "id_vacante", referencedColumnName = "id")
    @ManyToOne
    private Vacante vacante;

    public CompetenciasVacante() {
    }

    public CompetenciasVacante(Long id) {
        this.id = id;
    }

    public CompetenciasVacante(Long id, String descCompetencia, int idCompetencia, int nivel, int tipo) {
        this.id = id;
        this.descCompetencia = descCompetencia;
        this.idCompetencia = idCompetencia;
        this.nivel = nivel;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescCompetencia() {
        return descCompetencia;
    }

    public void setDescCompetencia(String descCompetencia) {
        this.descCompetencia = descCompetencia;
    }

    public int getIdCompetencia() {
        return idCompetencia;
    }

    public void setIdCompetencia(int idCompetencia) {
        this.idCompetencia = idCompetencia;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Vacante getVacante() {
        return vacante;
    }

    public void setVacante(Vacante vacante) {
        this.vacante = vacante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

	@Override
	public String toString() {
		return "CompetenciasVacante [id=" + id + ", descCompetencia=" + descCompetencia + ", idCompetencia="
				+ idCompetencia + ", nivel=" + nivel + ", tipo=" + tipo + ", vacante=" + vacante + "]";
	}
	
}

package com.nirho.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cuestionario_empresa_irh")
public class CuestionarioEmpresaIRH implements Serializable {
	
	private static final long serialVersionUID = 7990136299678391403L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id_cuestionario_empresa")
	private Long id;

	@Basic(optional = false)
	private Long idEmpresa;
	
	@Column(name = "finalizado")
	private boolean finalizado;
	
	@Column(name = "tema")
	private String tema;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cuestionarioEmpresaIRH")
   	private Set<CuestionarioEmpresaIRHPregunta> preguntas;
	
	public CuestionarioEmpresaIRH() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public Set<CuestionarioEmpresaIRHPregunta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(Set<CuestionarioEmpresaIRHPregunta> preguntas) {
		this.preguntas = preguntas;
	}

	@Override
	public String toString() {
		return "CuestionarioEmpresaIRH [id=" + id + ", idEmpresa=" + idEmpresa + ", finalizado=" + finalizado
				+ ", tema=" + tema + ", preguntas=" + preguntas + "]";
	}
	
}

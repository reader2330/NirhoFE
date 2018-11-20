package com.nirho.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cuestionario_empresa")
@NamedQueries({
	@NamedQuery(name = "CuestionarioEmpresa.findByIdTema", 
			query = "SELECT c FROM CuestionarioEmpresa c "
			+ " WHERE c.temaCuestionario.idTema =:idTema"
			+ " ORDER BY c.idCuestionarioEmpresa ASC") })
public class CuestionarioEmpresa implements Serializable {
	private static final long serialVersionUID = 7990136299678391403L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id_cuestionario_empresa")
	private Integer idCuestionarioEmpresa;
	@JoinColumn(name = "id_tema", referencedColumnName = "id_tema")
	@OneToOne(optional = false)
	private TemaCuestionario temaCuestionario;
	@JoinColumn(name = "id_empresa", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Empresa empresa;
	@Column(name = "finalizado")
	private boolean finalizado;
	@OneToMany(mappedBy = "cuestionarioEmpresa", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE )
	private List<PreguntaCuestionarioEmpresa> preguntas;

	public CuestionarioEmpresa() {
	}

	public CuestionarioEmpresa(Empresa empresa,
			TemaCuestionario temaCuestionario, boolean finalizado) {
		this.empresa = empresa;
		this.temaCuestionario = temaCuestionario;
		this.finalizado = finalizado;
	}

	public Integer getIdCuestionarioEmpresa() {
		return idCuestionarioEmpresa;
	}

	public void setIdCuestionarioEmpresa(Integer idCuestionarioEmpresa) {
		this.idCuestionarioEmpresa = idCuestionarioEmpresa;
	}

	public TemaCuestionario getTemaCuestionario() {
		return temaCuestionario;
	}

	public void setTemaCuestionario(TemaCuestionario temaCuestionario) {
		this.temaCuestionario = temaCuestionario;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	public List<PreguntaCuestionarioEmpresa> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(List<PreguntaCuestionarioEmpresa> preguntas) {
		this.preguntas = preguntas;
	}

	public String toString() {
		return "CuestionarioEmpresa [idCuestionarioEmpresa="
				+ idCuestionarioEmpresa + ", temaCuestionario="
				+ temaCuestionario + ", empresa=" + empresa + ", preguntas="
				+ preguntas + "]";
	}
}

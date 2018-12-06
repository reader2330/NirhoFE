/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nirho.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author alfredo.ca
 */
@Entity
@Table(name = "empleado")
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1213123123L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id_empleado")
    private Long id;
    
    //Datos de empleado
    
    @Column(name = "nombre_completo")
    private String nombreCompleto;
    
    @Basic(optional = false)
    @Column(name = "nacionalidad")
    private int nacionalidad;
    
    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;
    
    @Column(name = "edad")
    private int edad;
    
    @Column(name = "rfc")
    private String rfc;
    
    @Column(name = "curp")
    private String curp;
    
    @Column(name = "nss")
    private String nss;
    
    @Column(name = "direccion")
    private String direccion;
    
    @Column(name = "estado_civil")
    private int estadoCivil;
    
    //Contacto
  
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "empleado")
	private Set<EmpleadoContacto> contactos = new HashSet<>();
    
    //Datos de pago
    
    @Basic(optional = false)
    @Column(name = "banco")
    private int banco;
    
    @Column(name = "credito_hipotecario")
    private boolean creditoHipotecario;
    
    @Column(name = "tipo_credito_hipotecario")
    private int tipoCreditoHipotecario;
    
    @Column(name = "pensionAlimenticia")
    private boolean pensionAlimenticia;
    
    @Column(name = "banco_cuenta")
    private String bancoCuenta;

    @Column(name = "banco_cinterbancaria")
    private String bancoClaveInterbancaria;
    
    //Datos de escolaridad
    
    @Basic(optional = false)
    @Column(name = "escolaridad")
    private int escolaridad;
    
    @Column(name = "institucion")
    private String institucion;
    
    @Column(name = "escolaridad_carrera")
    private String escolaridadCarrera;
    
    @Column(name = "escolaridad_especialidad")
    private String escolaridadEspecialidad;
    
    @Column(name = "escolaridad_capacidades")
    private String escolaridadCapacidades;
    
    @ElementCollection
    @Column(name = "escolaridad_certificaciones")
    private Set<String> escolaridadCertificaciones;
    
    @ElementCollection
    @Column(name = "escolaridad_cursos")
    private Set<String> escolaridadCursos;
    
    @ElementCollection
    @Column(name = "escolaridad_oficios")
    private Set<String> escolaridadOficios;
    
    @Column(name = "titulo")
    private boolean titulo;
    
    //Datos de idiomas
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "empleado")
   	private Set<EmpleadoIdioma> idiomas;
    
	//Datos de nivel laboral
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "empleado")
   	private Set<EmpleadoLaboral> laboral = new HashSet<>();
    
    //Datos de documentos
    
    @Column(name = "documento_curp")
    private String documentoCurp;
    
    @Column(name = "documento_ine")
    private String documentoIne;
    
    @Column(name = "documento_cv")
    private String documentoCv;
    
    @Column(name = "documento_comprobante")
    private String documentoComprobanteDomicilio;
     
    public Empleado() {
    }

    public Empleado(Long id) {
        this.id = id;
    }
    
	public Empleado(Long id, String nombreCompleto, int nacionalidad, Date fechaNacimiento, int edad, String rfc,
			String curp, String nss, String direccion, int estadoCivil, Set<EmpleadoContacto> contactos, int banco,
			boolean creditoHipotecario, int tipoCreditoHipotecario, boolean pensionAlimenticia, String bancoCuenta,
			String bancoClaveInterbancaria, int escolaridad, String escolaridadCarrera, String escolaridadEspecialidad,
			String escolaridadCapacidades, Set<String> escolaridadCertificaciones, Set<String> escolaridadCursos,
			Set<String> escolaridadOficios, boolean titulo, Set<EmpleadoIdioma> idiomas,
			Set<EmpleadoLaboral> laboral, String documentoCurp, String documentoIne, String documentoCv,
			String documentoComprobanteDomicilio) {
		super();
		this.id = id;
		this.nombreCompleto = nombreCompleto;
		this.nacionalidad = nacionalidad;
		this.fechaNacimiento = fechaNacimiento;
		this.edad = edad;
		this.rfc = rfc;
		this.curp = curp;
		this.nss = nss;
		this.direccion = direccion;
		this.estadoCivil = estadoCivil;
		this.contactos = contactos;
		this.banco = banco;
		this.creditoHipotecario = creditoHipotecario;
		this.tipoCreditoHipotecario = tipoCreditoHipotecario;
		this.pensionAlimenticia = pensionAlimenticia;
		this.bancoCuenta = bancoCuenta;
		this.bancoClaveInterbancaria = bancoClaveInterbancaria;
		this.escolaridad = escolaridad;
		this.escolaridadCarrera = escolaridadCarrera;
		this.escolaridadEspecialidad = escolaridadEspecialidad;
		this.escolaridadCapacidades = escolaridadCapacidades;
		this.escolaridadCertificaciones = escolaridadCertificaciones;
		this.escolaridadCursos = escolaridadCursos;
		this.escolaridadOficios = escolaridadOficios;
		this.titulo = titulo;
		this.idiomas = idiomas;
		this.laboral = laboral;
		this.documentoCurp = documentoCurp;
		this.documentoIne = documentoIne;
		this.documentoCv = documentoCv;
		this.documentoComprobanteDomicilio = documentoComprobanteDomicilio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public int getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(int nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public String getNss() {
		return nss;
	}

	public void setNss(String nss) {
		this.nss = nss;
	}

	public String getDireccion() {
		return direccion;
	}

	public int getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(int estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Set<EmpleadoContacto> getContactos() {
		return contactos;
	}

	public void setContactos(Set<EmpleadoContacto> contactos) {
		this.contactos = contactos;
	}

	public int getBanco() {
		return banco;
	}

	public void setBanco(int banco) {
		this.banco = banco;
	}

	public String getBancoCuenta() {
		return bancoCuenta;
	}

	public void setBancoCuenta(String bancoCuenta) {
		this.bancoCuenta = bancoCuenta;
	}

	public String getBancoClaveInterbancaria() {
		return bancoClaveInterbancaria;
	}

	public void setBancoClaveInterbancaria(String bancoClaveInterbancaria) {
		this.bancoClaveInterbancaria = bancoClaveInterbancaria;
	}

	public int getEscolaridad() {
		return escolaridad;
	}

	public void setEscolaridad(int escolaridad) {
		this.escolaridad = escolaridad;
	}

	public String getInstitucion() {
		return institucion;
	}

	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}

	public String getEscolaridadCarrera() {
		return escolaridadCarrera;
	}

	public void setEscolaridadCarrera(String escolaridadCarrera) {
		this.escolaridadCarrera = escolaridadCarrera;
	}

	public String getEscolaridadEspecialidad() {
		return escolaridadEspecialidad;
	}

	public void setEscolaridadEspecialidad(String escolaridadEspecialidad) {
		this.escolaridadEspecialidad = escolaridadEspecialidad;
	}

	public String getEscolaridadCapacidades() {
		return escolaridadCapacidades;
	}

	public void setEscolaridadCapacidades(String escolaridadCapacidades) {
		this.escolaridadCapacidades = escolaridadCapacidades;
	}

	public boolean isCreditoHipotecario() {
		return creditoHipotecario;
	}

	public void setCreditoHipotecario(boolean creditoHipotecario) {
		this.creditoHipotecario = creditoHipotecario;
	}

	public int getTipoCreditoHipotecario() {
		return tipoCreditoHipotecario;
	}

	public void setTipoCreditoHipotecario(int tipoCreditoHipotecario) {
		this.tipoCreditoHipotecario = tipoCreditoHipotecario;
	}

	public boolean isPensionAlimenticia() {
		return pensionAlimenticia;
	}

	public void setPensionAlimenticia(boolean pensionAlimenticia) {
		this.pensionAlimenticia = pensionAlimenticia;
	}

	public Set<String> getEscolaridadCertificaciones() {
		return escolaridadCertificaciones;
	}

	public void setEscolaridadCertificaciones(Set<String> escolaridadCertificaciones) {
		this.escolaridadCertificaciones = escolaridadCertificaciones;
	}

	public Set<String> getEscolaridadCursos() {
		return escolaridadCursos;
	}

	public void setEscolaridadCursos(Set<String> escolaridadCursos) {
		this.escolaridadCursos = escolaridadCursos;
	}

	public Set<String> getEscolaridadOficios() {
		return escolaridadOficios;
	}

	public void setEscolaridadOficios(Set<String> escolaridadOficios) {
		this.escolaridadOficios = escolaridadOficios;
	}

	public Set<EmpleadoLaboral> getLaboral() {
		return laboral;
	}

	public void setLaboral(Set<EmpleadoLaboral> laboral) {
		this.laboral = laboral;
	}

	public boolean isTitulo() {
		return titulo;
	}

	public void setTitulo(boolean titulo) {
		this.titulo = titulo;
	}
	
	public Set<EmpleadoIdioma> getIdiomas() {
		return idiomas;
	}

	public void setIdiomas(Set<EmpleadoIdioma> idiomas) {
		this.idiomas = idiomas;
	}

	public String getDocumentoCurp() {
		return documentoCurp;
	}

	public void setDocumentoCurp(String documentoCurp) {
		this.documentoCurp = documentoCurp;
	}

	public String getDocumentoIne() {
		return documentoIne;
	}

	public void setDocumentoIne(String documentoIne) {
		this.documentoIne = documentoIne;
	}

	public String getDocumentoCv() {
		return documentoCv;
	}

	public void setDocumentoCv(String documentoCv) {
		this.documentoCv = documentoCv;
	}

	public String getDocumentoComprobanteDomicilio() {
		return documentoComprobanteDomicilio;
	}

	public void setDocumentoComprobanteDomicilio(String documentoComprobanteDomicilio) {
		this.documentoComprobanteDomicilio = documentoComprobanteDomicilio;
	}

	@Override
	public String toString() {
		return "Empleado [id=" + id + ", nombreCompleto=" + nombreCompleto + ", nacionalidad=" + nacionalidad
				+ ", fechaNacimiento=" + fechaNacimiento + ", edad=" + edad + ", rfc=" + rfc + ", curp=" + curp
				+ ", nss=" + nss + ", direccion=" + direccion + ", estadoCivil=" + estadoCivil + ", contactos="
				+ contactos + ", banco=" + banco + ", creditoHipotecario=" + creditoHipotecario
				+ ", tipoCreditoHipotecario=" + tipoCreditoHipotecario + ", pensionAlimenticia=" + pensionAlimenticia
				+ ", bancoCuenta=" + bancoCuenta + ", bancoClaveInterbancaria=" + bancoClaveInterbancaria
				+ ", escolaridad=" + escolaridad + ", escolaridadCarrera=" + escolaridadCarrera
				+ ", escolaridadEspecialidad=" + escolaridadEspecialidad + ", escolaridadCapacidades="
				+ escolaridadCapacidades + ", escolaridadCertificaciones=" + escolaridadCertificaciones
				+ ", escolaridadCursos=" + escolaridadCursos + ", escolaridadOficios=" + escolaridadOficios
				+ ", titulo=" + titulo + ", idiomas=" + idiomas + ", laboral=" + laboral + ", documentoCurp="
				+ documentoCurp + ", documentoIne=" + documentoIne + ", documentoCv=" + documentoCv
				+ ", documentoComprobanteDomicilio=" + documentoComprobanteDomicilio + "]";
	}
   
}

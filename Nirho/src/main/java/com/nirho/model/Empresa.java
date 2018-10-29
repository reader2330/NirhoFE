package com.nirho.model;

import java.io.Serializable;

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
@Table(name = "EMPRESA")
public class Empresa implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "direccion")
	private String direccion;
	@Column(name = "empresa")
	private String empresa;
	@Column(name = "giro")
	private int giro;
	@Column(name = "pais")
	private int pais;
	@Column(name = "rfc", nullable = false)
	private String rfc;
	
	@Column(name = "anio_inicio_operaciones", nullable = false)
	private int anioInicioOperaciones;
	
	@Column(name = "facturacion_anual", nullable = false)
	private double facturacionAnual;
	
	@Column(name = "producto_servicio_estrella", nullable = false)
	private String productoServicioEstrella;
	
	@Column(name = "principales_productos_servicios", nullable = false)
	private String principalesProductosServicios;
	
	@Column(name = "no_empleados_administrativo", nullable = false)
	private int noEmpleadosAdministrativo;
	
	@Column(name = "no_empleados_operarativo", nullable = false)
	private int noEmpleadosOperativo;	

	@Column(name = "tipo_contratacion_empleados")
	private String tipoContratacionEmpleados;	
		
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the empresa
	 */
	public String getEmpresa() {
		return empresa;
	}

	/**
	 * @param empresa the empresa to set
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/**
	 * @return the giro
	 */
	public int getGiro() {
		return giro;
	}

	/**
	 * @param giro the giro to set
	 */
	public void setGiro(int giro) {
		this.giro = giro;
	}

	/**
	 * @return the pais
	 */
	public int getPais() {
		return pais;
	}

	/**
	 * @param pais the pais to set
	 */
	public void setPais(int pais) {
		this.pais = pais;
	}

	/**
	 * @return the rfc
	 */
	public String getRfc() {
		return rfc;
	}

	/**
	 * @param rfc the rfc to set
	 */
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	/**
	 * @return the anioInicioOperaciones
	 */
	public int getAnioInicioOperaciones() {
		return anioInicioOperaciones;
	}

	/**
	 * @param anioInicioOperaciones the anioInicioOperaciones to set
	 */
	public void setAnioInicioOperaciones(int anioInicioOperaciones) {
		this.anioInicioOperaciones = anioInicioOperaciones;
	}

	/**
	 * @return the facturacionAnual
	 */
	public double getFacturacionAnual() {
		return facturacionAnual;
	}

	/**
	 * @param facturacionAnual the facturacionAnual to set
	 */
	public void setFacturacionAnual(double facturacionAnual) {
		this.facturacionAnual = facturacionAnual;
	}

	/**
	 * @return the productoServicioEstrella
	 */
	public String getProductoServicioEstrella() {
		return productoServicioEstrella;
	}

	/**
	 * @param productoServicioEstrella the productoServicioEstrella to set
	 */
	public void setProductoServicioEstrella(String productoServicioEstrella) {
		this.productoServicioEstrella = productoServicioEstrella;
	}

	/**
	 * @return the principalesProductosServicios
	 */
	public String getPrincipalesProductosServicios() {
		return principalesProductosServicios;
	}

	/**
	 * @param principalesProductosServicios the principalesProductosServicios to set
	 */
	public void setPrincipalesProductosServicios(String principalesProductosServicios) {
		this.principalesProductosServicios = principalesProductosServicios;
	}

	/**
	 * @return the noEmpleadosAdministrativo
	 */
	public int getNoEmpleadosAdministrativo() {
		return noEmpleadosAdministrativo;
	}

	/**
	 * @param noEmpleadosAdministrativo the noEmpleadosAdministrativo to set
	 */
	public void setNoEmpleadosAdministrativo(int noEmpleadosAdministrativo) {
		this.noEmpleadosAdministrativo = noEmpleadosAdministrativo;
	}

	/**
	 * @return the noEmpleadosOperativo
	 */
	public int getNoEmpleadosOperativo() {
		return noEmpleadosOperativo;
	}

	/**
	 * @param noEmpleadosOperativo the noEmpleadosOperativo to set
	 */
	public void setNoEmpleadosOperativo(int noEmpleadosOperativo) {
		this.noEmpleadosOperativo = noEmpleadosOperativo;
	}

	/**
	 * @return the tipoContratacionEmpleados
	 */
	public String getTipoContratacionEmpleados() {
		return tipoContratacionEmpleados;
	}

	/**
	 * @param tipoContratacionEmpleados the tipoContratacionEmpleados to set
	 */
	public void setTipoContratacionEmpleados(String tipoContratacionEmpleados) {
		this.tipoContratacionEmpleados = tipoContratacionEmpleados;
	}

	@Override
	public String toString() {
		return "Empresa [id=" + id + ", direccion=" + direccion + ", empresa=" + empresa + ", giro=" + giro + ", pais="
				+ pais + ", rfc=" + rfc + "]";
	}	
}

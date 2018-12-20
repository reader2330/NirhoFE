package com.nirho.service;

import java.util.List;

import com.nirho.dto.RegistroEmpresaDTO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.Empresa;
import com.nirho.model.Entrevistado;
import com.nirho.model.view.VwEmpresasSolicitudCuestionario;

public interface EmpresaService {
	List<Empresa> obtenerEmpresasTodas() throws NirhoServiceException;
	Empresa obtenerEmpresaPorRfc(String rfc) throws NirhoServiceException;
	public Empresa obtenerEmpresaPorId(long id) throws NirhoServiceException;
	void registraEmpresa(Empresa empresa) throws NirhoServiceException;
	
	/**
	 * Metodo para registrar una empresa completa en IRH
	 * @throws NirhoServiceException
	 */
	public void registrarEmpresaIRH(RegistroEmpresaDTO registroEmpresa) throws NirhoServiceException;
	
	/**
	 * Metodo para consultar una empresa con datos completos para IRH
	 * @param rfc
	 * @return
	 * @throws NirhoServiceException
	 */
	public Entrevistado consultarEmpresaIRH(String rfc) throws NirhoServiceException;
	
	void eliminarEmpresa(Empresa empresa) throws NirhoServiceException;
	List<VwEmpresasSolicitudCuestionario> getEmpresasSolicitadoEvaluacion() throws NirhoServiceException;
}

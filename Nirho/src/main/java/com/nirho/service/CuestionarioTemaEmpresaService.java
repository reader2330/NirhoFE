package com.nirho.service;

import java.util.List;
import com.nirho.dto.CuestionarioEmpresaDTO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.CuestionarioEmpresa;
import com.nirho.model.PreguntaCuestionarioEmpresa;
import com.nirho.model.PreguntaTema;
import com.nirho.model.RespuestaPreguntaIRH;
import com.nirho.model.TemaCuestionario;
import com.nirho.model.view.VwCuestionarioPreguntasTemas;
import com.nirho.model.view.VwCuestionarioRespuestas;
import com.nirho.model.view.VwTemaCuestionario;

public interface CuestionarioTemaEmpresaService {
	
	public void agregarPregunta(PreguntaTema paramPreguntaTema) throws NirhoServiceException;

	public void editarPregunta(PreguntaTema paramPreguntaTema) throws NirhoServiceException;

	public void agregarPreguntaCuestionario(PreguntaCuestionarioEmpresa paramPreguntaCuestionarioEmpresa) throws NirhoServiceException;

	public List<CuestionarioEmpresa> consultarCuestionariosActivosEmpresa(String paramString) throws NirhoServiceException;

	public List<CuestionarioEmpresaDTO> consultarCuestionariosTemaEmpresa(String paramString) throws NirhoServiceException;

	public CuestionarioEmpresa consultarPreguntasTemaEmpresa(Integer paramInteger) throws NirhoServiceException;

	public void agregarEditarRespuestasCuestionarioActivoEmpresa(List<RespuestaPreguntaIRH> paramList) throws NirhoServiceException;
	
	public void agregarEditarRespuestasCuestionarioActivoEmpresa(RespuestaPreguntaIRH param) throws NirhoServiceException;

	/**
	 * Metodo para eliminar respuestas
	 * @param respuestas
	 * @throws NirhoServiceException
	 */
	public void eliminarRespuestaCuestionarioActivoEmpresa(List<RespuestaPreguntaIRH> respuestas) throws NirhoServiceException;

	public List<RespuestaPreguntaIRH> consultarRespuestasTemaEmpresa(Integer paramInteger) throws NirhoServiceException;

	public CuestionarioEmpresa agregarCuestionarioEmpresa(String paramString, Integer paramInteger) throws NirhoServiceException;

	public TemaCuestionario agregarTema(TemaCuestionario paramTemaCuestionario) throws NirhoServiceException;

	public TemaCuestionario updateTema(TemaCuestionario paramTemaCuestionario) throws NirhoServiceException;
	
	public List<VwCuestionarioPreguntasTemas> consultarPreguntasTemaCuestionario(int idTema) throws NirhoServiceException;
	
	public List<VwCuestionarioPreguntasTemas> consultarPreguntasTemaCuestionarioConRespuesta(int idTema, int idParticipante) throws NirhoServiceException;
	
	public List<VwCuestionarioRespuestas> consultarRespuestaCuestionario(int idParticipante) throws NirhoServiceException;
	
	public VwCuestionarioRespuestas consultarRespuestaCuestionario(int idParticipante, int idPregunta) throws NirhoServiceException;

	public List<VwTemaCuestionario> consultarTemasCuestionario()  throws NirhoServiceException;

}

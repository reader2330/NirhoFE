package com.nirho.service.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.dao.CuestionarioEmpresaDAO;
import com.nirho.dao.EmpresaDAO;
import com.nirho.dao.PreguntaCuestionarioEmpresaDAO;
import com.nirho.dao.PreguntaTemaDAO;
import com.nirho.dao.RespuestaPreguntaDAO;
import com.nirho.dao.TemaCuestionarioDAO;
import com.nirho.dto.CuestionarioEmpresaDTO;
import com.nirho.dto.transformer.CuestionarioDTOTransformer;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.CuestionarioEmpresa;
import com.nirho.model.PreguntaCuestionarioEmpresa;
import com.nirho.model.PreguntaTema;
import com.nirho.model.RespuestaPreguntaIRH;
import com.nirho.model.TemaCuestionario;
import com.nirho.model.view.VwCuestionarioPreguntasTemas;
import com.nirho.model.view.VwCuestionarioRespuestas;
import com.nirho.model.view.VwTemaCuestionario;
import com.nirho.service.CuestionarioTemaEmpresaService;

@Service
public class CuestionarioServiceImpl implements CuestionarioTemaEmpresaService {
	public final static Logger logger = Logger.getLogger(CuestionarioServiceImpl.class);
	
	@Autowired
	private PreguntaTemaDAO preguntaTemaDAO;
	
	@Autowired
	private PreguntaCuestionarioEmpresaDAO preguntaCuestionarioEmpresaDAO;
	
	@Autowired
	private CuestionarioEmpresaDAO cuestionarioEmpresaDAO;
	
	@Autowired
	private RespuestaPreguntaDAO respuestaPreguntaDAO;
	
	@Autowired
	private TemaCuestionarioDAO temaCuestionarioDAO;
	
	@Autowired
	private EmpresaDAO empresaDAO;
	
	@Override
	public void agregarPregunta(PreguntaTema preguntaTema) throws NirhoServiceException {
		preguntaTemaDAO.save(preguntaTema);
	}
	
	@Override
	public void editarPregunta(PreguntaTema preguntaTema) throws NirhoServiceException {
		PreguntaTema preguntaTemaEditar = preguntaTemaDAO.getOne(preguntaTema.getIdPregunta());
		preguntaTemaEditar.setDocumentoReferencia(preguntaTema.getDocumentoReferencia());
		preguntaTemaEditar.setEnunciado(preguntaTema.getEnunciado() != null ? preguntaTema.getEnunciado() : preguntaTemaEditar.getEnunciado());
		preguntaTemaDAO.update(preguntaTemaEditar);
	}
	
	@Override
	public void agregarPreguntaCuestionario(PreguntaCuestionarioEmpresa preguntaCuestionarioEmpresa) throws NirhoServiceException {
		preguntaCuestionarioEmpresaDAO.save(preguntaCuestionarioEmpresa);
	}
	
	@Override
	public List<CuestionarioEmpresa> consultarCuestionariosActivosEmpresa(String rfc) throws NirhoServiceException {
		return cuestionarioEmpresaDAO.consultarPorRFC(rfc);
	}
	
	@Override
	public List<CuestionarioEmpresaDTO> consultarCuestionariosTemaEmpresa(String rfc) throws NirhoServiceException {
		List<TemaCuestionario> temasCuestionario = temaCuestionarioDAO.findAll();
		List<CuestionarioEmpresa> cuestionariosEmpresa = cuestionarioEmpresaDAO.consultarPorRFC(rfc);
		return CuestionarioDTOTransformer.entitiesToDTOs(temasCuestionario, cuestionariosEmpresa);
	}

	@Override
	public CuestionarioEmpresa consultarPreguntasTemaEmpresa(Integer idCuestionarioEmpresa) throws NirhoServiceException {
		return cuestionarioEmpresaDAO.consultarPreguntasTemaEmpresa(idCuestionarioEmpresa);
	}
	
	@Override
	public List<RespuestaPreguntaIRH> consultarRespuestasTemaEmpresa(Integer idCuestionarioEmpresa) throws NirhoServiceException {
		return respuestaPreguntaDAO.consultarRespuestasCuestionarioIRH(idCuestionarioEmpresa);
	}
	
	@Override
	public void agregarEditarRespuestasCuestionarioActivoEmpresa (List<RespuestaPreguntaIRH> respuestas) throws NirhoServiceException {
		respuestaPreguntaDAO.agregarEditarRespuestasCuestionarioActivoEmpresa(respuestas);
	}
	
	@Override
	public void agregarEditarRespuestasCuestionarioActivoEmpresa (RespuestaPreguntaIRH respuesta) throws NirhoServiceException {
		CuestionarioEmpresa cuestionario = cuestionarioEmpresaDAO.consultarCuestionarioEmpresa(respuesta.getIdTema());
		if(cuestionario == null){
			cuestionario = new CuestionarioEmpresa();
			cuestionario.setEmpresa(empresaDAO.getOne(new Long(respuesta.getIdParticipante())));
			cuestionario.setFinalizado(respuesta.isFinalizado());
			cuestionario.setTemaCuestionario(temaCuestionarioDAO.getOne(respuesta.getIdTema()));
			cuestionarioEmpresaDAO.save(cuestionario);		
			CuestionarioEmpresa cuestionarioSaved = cuestionarioEmpresaDAO.consultarCuestionarioEmpresa(respuesta.getIdTema());
			respuesta.setCuestionarioEmpresa(cuestionarioSaved);
			respuesta.setIdCuestionarioEmpresa(cuestionarioSaved.getIdCuestionarioEmpresa());
		}else{
			respuesta.setCuestionarioEmpresa(cuestionario);
			respuesta.setIdCuestionarioEmpresa(cuestionario.getIdCuestionarioEmpresa());
		}
		respuestaPreguntaDAO.agregarEditarRespuestasCuestionarioActivoEmpresa(respuesta);
	}
	
	@Override
	public void eliminarRespuestaCuestionarioActivoEmpresa (List<RespuestaPreguntaIRH> respuestas) throws NirhoServiceException {
		for (RespuestaPreguntaIRH respuestaPreguntaIRH : respuestas) {
			respuestaPreguntaDAO.delete(respuestaPreguntaIRH);
		}
	}
	
	@Override
	public CuestionarioEmpresa agregarCuestionarioEmpresa (String rfc, Integer idTema) throws NirhoServiceException {
		CuestionarioEmpresa cuestionarioEmpresa = new CuestionarioEmpresa((empresaDAO.findByRfc(rfc)).get(0), temaCuestionarioDAO.getOne(idTema), false);
		cuestionarioEmpresaDAO.save(cuestionarioEmpresa);
		return cuestionarioEmpresa;
	}
	
	@Override
	public TemaCuestionario agregarTema (TemaCuestionario temaCuestionario) throws NirhoServiceException {
		temaCuestionarioDAO.save(temaCuestionario);
		return temaCuestionario;
	}

@Override
	public TemaCuestionario updateTema (TemaCuestionario temaCuestionario) throws NirhoServiceException {
		temaCuestionarioDAO.update(temaCuestionario);
		return temaCuestionario;
	}
	
	@Override
	public List<VwCuestionarioPreguntasTemas> consultarPreguntasTemaCuestionario(int idTema)
			throws NirhoServiceException {
		return cuestionarioEmpresaDAO.consultarPreguntasTemaCuestionario(idTema);
	}

	@Override
	public List<VwCuestionarioPreguntasTemas> consultarPreguntasTemaCuestionarioConRespuesta(int idTema,
			int idParticipante) throws NirhoServiceException {
		List<VwCuestionarioPreguntasTemas> preguntas = cuestionarioEmpresaDAO.consultarPreguntasTemaCuestionario(idTema);
		if(preguntas != null && !preguntas.isEmpty()){
			for(VwCuestionarioPreguntasTemas preg : preguntas){
				VwCuestionarioRespuestas respuesta = cuestionarioEmpresaDAO.
						consultarRespuestaCuestionario(idParticipante, preg.getIdPregunta());
				preg.setRespuesta(respuesta != null ? respuesta : new VwCuestionarioRespuestas());
			}
		}
		return preguntas;
	}

	@Override
	public List<VwCuestionarioRespuestas> consultarRespuestaCuestionario(int idParticipante)
			throws NirhoServiceException {
		return cuestionarioEmpresaDAO.consultarRespuestaCuestionario(idParticipante);
	}

	@Override
	public VwCuestionarioRespuestas consultarRespuestaCuestionario(int idParticipante, int idPregunta)
			throws NirhoServiceException {
		return cuestionarioEmpresaDAO.consultarRespuestaCuestionario(idParticipante,idPregunta);
	}

	@Override
	public List<VwTemaCuestionario> consultarTemasCuestionario() throws NirhoServiceException {
		return cuestionarioEmpresaDAO.consultarTemasCuestionario();
	}
}

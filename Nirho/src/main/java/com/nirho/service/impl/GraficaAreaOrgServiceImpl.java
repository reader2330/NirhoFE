package com.nirho.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.constant.AreaOrgConstants;
import com.nirho.dao.CuestionarioParticipanteDAO;
import com.nirho.dao.CuestionarioProyectoDAO;
import com.nirho.dao.ParticipanteDAO;
import com.nirho.dto.GraficaAreaOrgDTO;
import com.nirho.dto.GraficaResultadoDTO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.CuestionarioProyecto;
import com.nirho.model.CuetionarioParticipante;
import com.nirho.model.Participante;
import com.nirho.model.Pregunta;
import com.nirho.service.GraficaAreaOrgService;

@Service
public class GraficaAreaOrgServiceImpl implements GraficaAreaOrgService {
	public final static Logger logger = Logger.getLogger(GraficaAreaOrgServiceImpl.class);
	
	@Autowired
	private ParticipanteDAO participanteDAO;
	@Autowired
	private CuestionarioParticipanteDAO cuestPArtDAO;
	@Autowired
	private CuestionarioProyectoDAO cuestProytDAO;
	
	@Override
	public GraficaAreaOrgDTO obtenerResultadosCorporativo(int idProyecto)  throws NirhoServiceException{
		GraficaAreaOrgDTO gdto = new GraficaAreaOrgDTO();
		gdto.setAreaOrg(AreaOrgConstants.CORPORATIVO);
		List<GraficaResultadoDTO> resultados = new ArrayList<>();
		List<Participante> participantes = new ArrayList<>();
		try {
			participantes = participanteDAO.findByAreaOrgProyecto(AreaOrgConstants.CORPORATIVO, idProyecto);	
			for(Participante p: participantes) {
				for(CuestionarioProyecto cuestProy: cuestProytDAO.findByIdProyecto(idProyecto)) {					
					GraficaResultadoDTO resDTO = new GraficaResultadoDTO();
					Pregunta pregunta = cuestProy.getPregunta();
					resDTO.setPregunta(pregunta);
					for (CuetionarioParticipante cuestPart : cuestPArtDAO.findByParticipanteProyectoPregunta(
							p.getParticipantePK().getIdParticipante(), idProyecto, pregunta.getIdPregunta())) {
						int respuesta = cuestPart.getRespuesta() != null ? cuestPart.getRespuesta().intValue() : 0;
						switch (respuesta) {
						case 1:
							resDTO.setNumResp1(resDTO.getNumResp1() + 1);
							break;
						case 2:
							resDTO.setNumResp2(resDTO.getNumResp1() + 1);
							break;
						case 3:
							resDTO.setNumResp3(resDTO.getNumResp1() + 1);
							break;
						case 4:
							resDTO.setNumResp4(resDTO.getNumResp1() + 1);
							break;
						case 5:
							resDTO.setNumResp5(resDTO.getNumResp1() + 1);
							break;
						}
					}
					resultados.add(resDTO);
				}
			}
			gdto.setResultados(resultados);
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
		return gdto;
	}



	@Override
	public GraficaAreaOrgDTO obtenerResultadosProduccion(int idProyecto) throws NirhoServiceException {
		GraficaAreaOrgDTO gdto = new GraficaAreaOrgDTO();
		gdto.setAreaOrg(AreaOrgConstants.PRODUCCION);
		List<GraficaResultadoDTO> resultados = new ArrayList<>();
		List<Participante> participantes = new ArrayList<>();
		try {
			participantes = participanteDAO.findByAreaOrgProyecto(AreaOrgConstants.PRODUCCION, idProyecto);
			for(CuestionarioProyecto cuestProy: cuestProytDAO.findByIdProyecto(idProyecto)) {
				GraficaResultadoDTO resDTO = new GraficaResultadoDTO();
				Pregunta pregunta = cuestProy.getPregunta();
				resDTO.setPregunta(pregunta);
				for(Participante p: participantes) {
					for(CuetionarioParticipante cuestPart : cuestPArtDAO.findByParticipanteProyectoPregunta(
							p.getParticipantePK().getIdParticipante(), idProyecto, pregunta.getIdPregunta())){
						int respuesta = cuestPart.getRespuesta() != null?cuestPart.getRespuesta().intValue():0;
						switch(respuesta) {
							case 1:
								resDTO.setNumResp1(resDTO.getNumResp1()+1);
								break;
							case 2:
								resDTO.setNumResp2(resDTO.getNumResp1()+1);
								break;
							case 3:
								resDTO.setNumResp3(resDTO.getNumResp1()+1);
								break;	
							case 4:
								resDTO.setNumResp4(resDTO.getNumResp1()+1);
								break;
							case 5:
								resDTO.setNumResp5(resDTO.getNumResp1()+1);
								break;
						}
					}
				}
				resultados.add(resDTO);
			}
			gdto.setResultados(resultados);
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
		return gdto;
	}



	@Override
	public GraficaAreaOrgDTO obtenerResultadosVentas(int idProyecto) throws NirhoServiceException {
		GraficaAreaOrgDTO gdto = new GraficaAreaOrgDTO();
		gdto.setAreaOrg(AreaOrgConstants.VENTAS);
		List<GraficaResultadoDTO> resultados = new ArrayList<>();
		List<Participante> participantes = new ArrayList<>();
		try {
			participantes = participanteDAO.findByAreaOrgProyecto(AreaOrgConstants.VENTAS, idProyecto);
			for(CuestionarioProyecto cuestProy: cuestProytDAO.findByIdProyecto(idProyecto)) {
				GraficaResultadoDTO resDTO = new GraficaResultadoDTO();
				Pregunta pregunta = cuestProy.getPregunta();
				resDTO.setPregunta(pregunta);
				for(Participante p: participantes) {
					for(CuetionarioParticipante cuestPart : cuestPArtDAO.findByParticipanteProyectoPregunta(
							p.getParticipantePK().getIdParticipante(), idProyecto, pregunta.getIdPregunta())){
						int respuesta = cuestPart.getRespuesta() != null?cuestPart.getRespuesta().intValue():0;
						switch(respuesta) {
							case 1:
								resDTO.setNumResp1(resDTO.getNumResp1()+1);
								break;
							case 2:
								resDTO.setNumResp2(resDTO.getNumResp1()+1);
								break;
							case 3:
								resDTO.setNumResp3(resDTO.getNumResp1()+1);
								break;	
							case 4:
								resDTO.setNumResp4(resDTO.getNumResp1()+1);
								break;
							case 5:
								resDTO.setNumResp5(resDTO.getNumResp1()+1);
								break;
						}
					}
				}
				resultados.add(resDTO);
			}
			gdto.setResultados(resultados);
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
		return gdto;
	}



	@Override
	public GraficaAreaOrgDTO obtenerResultadosAdministracion(int idProyecto) throws NirhoServiceException {
		GraficaAreaOrgDTO gdto = new GraficaAreaOrgDTO();
		gdto.setAreaOrg(AreaOrgConstants.ADMINISTRACION);
		List<GraficaResultadoDTO> resultados = new ArrayList<>();
		List<Participante> participantes = new ArrayList<>();
		try {
			participantes = participanteDAO.findByAreaOrgProyecto(AreaOrgConstants.ADMINISTRACION, idProyecto);
			for(CuestionarioProyecto cuestProy: cuestProytDAO.findByIdProyecto(idProyecto)) {
				GraficaResultadoDTO resDTO = new GraficaResultadoDTO();
				Pregunta pregunta = cuestProy.getPregunta();
				resDTO.setPregunta(pregunta);
				for(Participante p: participantes) {
					for(CuetionarioParticipante cuestPart : cuestPArtDAO.findByParticipanteProyectoPregunta(
							p.getParticipantePK().getIdParticipante(), idProyecto, pregunta.getIdPregunta())){
						int respuesta = cuestPart.getRespuesta() != null?cuestPart.getRespuesta().intValue():0;
						switch(respuesta) {
							case 1:
								resDTO.setNumResp1(resDTO.getNumResp1()+1);
								break;
							case 2:
								resDTO.setNumResp2(resDTO.getNumResp1()+1);
								break;
							case 3:
								resDTO.setNumResp3(resDTO.getNumResp1()+1);
								break;	
							case 4:
								resDTO.setNumResp4(resDTO.getNumResp1()+1);
								break;
							case 5:
								resDTO.setNumResp5(resDTO.getNumResp1()+1);
								break;
						}
					}
				}
				resultados.add(resDTO);
			}
			gdto.setResultados(resultados);
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
		return gdto;
	}



	@Override
	public GraficaAreaOrgDTO obtenerResultadosControlDeCalidad(int idProyecto) throws NirhoServiceException {
		GraficaAreaOrgDTO gdto = new GraficaAreaOrgDTO();
		gdto.setAreaOrg(AreaOrgConstants.CONTROL_DE_CALIDAD);
		List<GraficaResultadoDTO> resultados = new ArrayList<>();
		List<Participante> participantes = new ArrayList<>();
		try {
			participantes = participanteDAO.findByAreaOrgProyecto(AreaOrgConstants.CONTROL_DE_CALIDAD, idProyecto);
			for(CuestionarioProyecto cuestProy: cuestProytDAO.findByIdProyecto(idProyecto)) {
				GraficaResultadoDTO resDTO = new GraficaResultadoDTO();
				Pregunta pregunta = cuestProy.getPregunta();
				resDTO.setPregunta(pregunta);
				for(Participante p: participantes) {
					for(CuetionarioParticipante cuestPart : cuestPArtDAO.findByParticipanteProyectoPregunta(
							p.getParticipantePK().getIdParticipante(), idProyecto, pregunta.getIdPregunta())){
						int respuesta = cuestPart.getRespuesta() != null?cuestPart.getRespuesta().intValue():0;
						switch(respuesta) {
							case 1:
								resDTO.setNumResp1(resDTO.getNumResp1()+1);
								break;
							case 2:
								resDTO.setNumResp2(resDTO.getNumResp1()+1);
								break;
							case 3:
								resDTO.setNumResp3(resDTO.getNumResp1()+1);
								break;	
							case 4:
								resDTO.setNumResp4(resDTO.getNumResp1()+1);
								break;
							case 5:
								resDTO.setNumResp5(resDTO.getNumResp1()+1);
								break;
						}
					}
				}
				resultados.add(resDTO);
			}
			gdto.setResultados(resultados);
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
		return gdto;
	}

}

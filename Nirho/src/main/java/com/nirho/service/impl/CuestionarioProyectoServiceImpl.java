package com.nirho.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.dao.CuestionarioParticipanteDAO;
import com.nirho.dao.CuestionarioProyectoDAO;
import com.nirho.dao.ParticipanteDAO;
import com.nirho.dto.CuestionarioConfiguracion;
import com.nirho.dto.VerTemaQ;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.CuestionarioProyecto;
import com.nirho.model.CuestionarioProyectoPK;
import com.nirho.model.CuetionarioParticipante;
import com.nirho.model.CuetionarioParticipantePK;
import com.nirho.model.Participante;
import com.nirho.model.PreguntaTema;
import com.nirho.service.CuestionarioProyectoService;

@Service
public class CuestionarioProyectoServiceImpl implements CuestionarioProyectoService {
	public final static Logger logger = Logger.getLogger(CuestionarioProyectoServiceImpl.class);
	
	@Autowired
	private CuestionarioProyectoDAO dao;
	@Autowired
	private ParticipanteDAO participanteDAO;
	@Autowired
	private CuestionarioParticipanteDAO cuestDAO;
	@Override
	public void guardar(CuestionarioConfiguracion cuestionario) throws NirhoServiceException {
		try {
			for(PreguntaTema pregunta: cuestionario.getLista()) {
				CuestionarioProyecto cp = new CuestionarioProyecto();
				CuestionarioProyectoPK pk = new CuestionarioProyectoPK(
						cuestionario.getIdProyecto(), pregunta.getTema().getIdTema(), pregunta.getIdPregunta());
				cp.setCuestionarioProyectoPK(pk);
				dao.save(cp);
				Integer idEmpresa = cp.getProyecto().getIdEmpresa().getId().intValue();
				for(Participante part: participanteDAO.findByIdEmpresa(Long.parseLong(idEmpresa.toString()))) {
					CuetionarioParticipante cuestPart = new CuetionarioParticipante();
					CuetionarioParticipantePK cuestPartPK = new CuetionarioParticipantePK(
							part.getIdParticipante(), cp.getTemaCuestionario().getIdTema(), cp.getPreguntaTema().getIdPregunta());
					cuestPart.setCuetionarioParticipantePK(cuestPartPK);
					cuestDAO.save(cuestPart);
				}
			}
		} catch(Exception e) {
			logger.info("Exception e [" + e.getMessage() +"]");
			throw new NirhoServiceException("Problemas con la BD al guardar el cuestionario");
		}
	}

	@Override
	public List<VerTemaQ> verTemasCuestionario(Integer idProyecto) throws NirhoServiceException {
		List<VerTemaQ> temasq = new ArrayList<>();
		try {
			List<CuestionarioProyecto> cuestionario = dao.findByIdProyecto(idProyecto);
			Map<String, VerTemaQ> mapTemas = new HashedMap<>();
			for(CuestionarioProyecto cp: cuestionario) {
				if(mapTemas.get(cp.getTemaCuestionario().getNombre()) != null) {
					mapTemas.get(cp.getTemaCuestionario().getNombre()).getPreguntas().add(cp.getPreguntaTema().getEnunciado());
				} else {
					VerTemaQ vtq = new VerTemaQ();
					vtq.setNombre(cp.getTemaCuestionario().getNombre());
					List<String> preguntas = new ArrayList<>();
					logger.info("agregando [" + cp.getPreguntaTema() +"]");
					preguntas.add(cp.getPreguntaTema().getEnunciado());
					vtq.setPreguntas(preguntas);
					mapTemas.put(cp.getTemaCuestionario().getNombre(), vtq);
				}
			}
			for (Map.Entry<String, VerTemaQ> entry : mapTemas.entrySet()) {
				logger.info(entry.getValue());
				temasq.add(entry.getValue());
			}
		} catch(Exception e) {
			logger.info("Exception e [" + e.getMessage() +"]");
			throw new NirhoServiceException("Problemas al obtener los temas en la BD del proyecto [" + idProyecto + "]");
		}
		return temasq;
	}
	
}

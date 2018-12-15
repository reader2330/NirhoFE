package com.nirho.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.dao.CuestionarioOpcionDAO;
import com.nirho.dao.CuestionarioParticipanteDAO;
import com.nirho.dao.CuestionarioProyectoDAO;
import com.nirho.dao.EvaluadorEvaluadoDAO;
import com.nirho.dao.OpcionDAO;
import com.nirho.dao.ParticipanteDAO;
import com.nirho.dao.PreguntaDAO;
import com.nirho.dao.TemaDAO;
import com.nirho.dto.CuestPartEvaluadosEVD;
import com.nirho.dto.CuestionarioConfEVD;
import com.nirho.dto.CuestionarioConfOpcion;
import com.nirho.dto.CuestionarioConfiguracion;
import com.nirho.dto.CuestionarioParticipanteEVD;
import com.nirho.dto.PreguntaOpcionesEVD;
import com.nirho.dto.VerTemaQ;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.CuestionarioOpcion;
import com.nirho.model.CuestionarioOpcionPK;
import com.nirho.model.CuestionarioProyecto;
import com.nirho.model.CuestionarioProyectoPK;
import com.nirho.model.CuetionarioParticipante;
import com.nirho.model.CuetionarioParticipantePK;
import com.nirho.model.EvaluadorEvaluado;
import com.nirho.model.Opcion;
import com.nirho.model.Participante;
import com.nirho.model.ParticipantePK;
import com.nirho.model.Pregunta;
import com.nirho.model.Tema;
import com.nirho.service.CuestionarioProyectoService;
import com.nirho.util.NirhoUtil;

@Service
public class CuestionarioProyectoServiceImpl implements CuestionarioProyectoService {
	public final static Logger logger = Logger.getLogger(CuestionarioProyectoServiceImpl.class);

	@Autowired
	private CuestionarioProyectoDAO dao;
	@Autowired
	private ParticipanteDAO participanteDAO;
	@Autowired
	private CuestionarioParticipanteDAO cuestDAO;
	@Autowired
	private CuestionarioParticipanteDAO cuestPartDAO;
	@Autowired
	private PreguntaDAO preguntaDAO;
	@Autowired
	private TemaDAO temaDAO;
	@Autowired
	private OpcionDAO opcionDAO;
	@Autowired
	private CuestionarioOpcionDAO coDAO;
	@Autowired
	private EvaluadorEvaluadoDAO evalEvalDAO;

	@Override
	public void guardar(CuestionarioConfiguracion cuestionario) throws NirhoServiceException {
		logger.info("************* CuestionarioConfiguracion [" + cuestionario +"] *******************");
		try {
			for(Pregunta pregunta: cuestionario.getLista()) {
				Pregunta pregTem = null;
				pregTem = preguntaDAO.getOne(pregunta.getIdPregunta());
				logger.info("************* pregTem [" + pregTem +"] *******************");
				if(pregTem == null) {
					logger.info("************* Es nueva pregunta [" + pregunta +"] *******************");
					preguntaDAO.save(pregunta);
				}
				CuestionarioProyecto cp = new CuestionarioProyecto();
				CuestionarioProyectoPK pk = new CuestionarioProyectoPK(
						cuestionario.getIdProyecto(), pregunta.getIdTema().getIdTema(), pregunta.getIdPregunta());
				cp.setCuestionarioProyectoPK(pk);
				dao.save(cp);
				for(Participante part: participanteDAO.findByIdProyecto(cuestionario.getIdProyecto())) {
					CuetionarioParticipante cuestPart = new CuetionarioParticipante();
					CuetionarioParticipantePK cuestPartPK = new CuetionarioParticipantePK(
							part.getParticipantePK().getIdParticipante(), part.getParticipantePK().getIdProyecto(),
									pregunta.getIdTema().getIdTema(), pregunta.getIdPregunta());
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
	public void guardar(CuestionarioConfEVD cuestionario) throws NirhoServiceException {
		logger.info("************* CuestionarioConfEVD [" + cuestionario +"] *******************");
		try {
			for(Tema tema: cuestionario.getLista()) {
				Tema temaTem = null;
				temaTem = temaDAO.getOne(tema.getIdTema());
				logger.info("************* tema [" + temaTem +"] *******************");
				Pregunta pregunta = null;
				if(temaTem == null) {
					logger.info("************* Es nuevo tema [" + tema +"] *******************");
					temaDAO.save(tema);
					pregunta = new Pregunta();
					pregunta.setIdPregunta(new Integer(tema.getIdTema()+"01"));
					pregunta.setEnunciado(tema.getNombre());
					pregunta.setIdTema(tema);
					preguntaDAO.save(pregunta);
				} else {
					pregunta = preguntaDAO.findByIdTema(temaTem.getIdTema()).get(0);
				}
				CuestionarioProyecto cp = new CuestionarioProyecto();
				CuestionarioProyectoPK pk = new CuestionarioProyectoPK(
						cuestionario.getIdProyecto(), pregunta.getIdTema().getIdTema(), pregunta.getIdPregunta());
				cp.setCuestionarioProyectoPK(pk);
				dao.save(cp);
				for(Participante part: participanteDAO.findByIdProyecto(cuestionario.getIdProyecto())) {
					CuetionarioParticipante cuestPart = new CuetionarioParticipante();
					CuetionarioParticipantePK cuestPartPK = new CuetionarioParticipantePK(
							part.getParticipantePK().getIdParticipante(), part.getParticipantePK().getIdProyecto(),
									pregunta.getIdTema().getIdTema(), pregunta.getIdPregunta());
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
				if(mapTemas.get(cp.getTema().getNombre()) != null) {
					mapTemas.get(cp.getTema().getNombre()).getPreguntas().add(cp.getPregunta().getEnunciado());
				} else {
					VerTemaQ vtq = new VerTemaQ();
					vtq.setNombre(cp.getTema().getNombre());
					List<String> preguntas = new ArrayList<>();
					preguntas.add(cp.getPregunta().getEnunciado());
					vtq.setPreguntas(preguntas);
					mapTemas.put(cp.getTema().getNombre(), vtq);
				}
			}
			for (Map.Entry<String, VerTemaQ> entry : mapTemas.entrySet()) {
				temasq.add(entry.getValue());
			}
		} catch(Exception e) {
			logger.info("Exception e [" + e.getMessage() +"]");
			throw new NirhoServiceException("Problemas al obtener los temas en la BD del proyecto [" + idProyecto + "]");
		}
		return temasq;
	}

	@Override
	public List<CuetionarioParticipante> obtenerCuestionarioParticipante(String token) throws NirhoServiceException {
		List<CuetionarioParticipante> cuestPart = null;
		try {
			ParticipantePK participantePK = NirhoUtil.obtenerParticipanteToken(token);
			Participante participante = participanteDAO.getOne(participantePK);
			if(participante != null) {
				return cuestPartDAO.findByParticipanteProyecto(participante.getParticipantePK().getIdParticipante(),
						participante.getParticipantePK().getIdProyecto());
			}
		} catch(NirhoServiceException nse) {
			throw new NirhoServiceException(nse.getMessage());
		} catch(Exception e) {
			logger.info("Exception e [" + e.getMessage() +"]");
			throw new NirhoServiceException("Problemas al obtener el cuestionario del participante en la BD token [" + token + "]");
		}
		return cuestPart;
	}

	@Override
	public void contestarPregunta(CuetionarioParticipante questPart) throws NirhoServiceException {
		try {
			cuestPartDAO.update(questPart);
		} catch(Exception e) {
			logger.info("Exception e [" + e.getMessage() +"]");
			throw new NirhoServiceException("Problemas al registrar la respuesta del participante en la BD");
		}
	}
	
	@Override
	public List<CuestionarioProyecto> obtenerCuestionarioProyecto(Integer idProyecto) throws NirhoServiceException {
		return dao.findByIdProyecto(idProyecto);
	}

	@Override
	public void guardarOpciones(CuestionarioConfOpcion cuestionario) throws NirhoServiceException {
		logger.info("************* CuestionarioConfOpcion [" + cuestionario +"] *******************");
		try {
			for(Opcion opcion: cuestionario.getLista()) {
				Opcion opcionTem = null;
				opcionTem = opcionDAO.getOne(opcion.getIdOpcion());
				logger.info("************* opcionTem [" + opcionTem +"] *******************");
				if(opcionTem == null) {
					logger.info("************* Es nueva pregunta [" + opcion +"] *******************");
					opcionDAO.save(opcion);
				}
				CuestionarioOpcion co = new CuestionarioOpcion();
				CuestionarioOpcionPK pk = new CuestionarioOpcionPK(
						cuestionario.getIdProyecto(), opcion.getIdTema().getIdTema(), opcion.getIdOpcion());
				co.setCuestionarioOpcionPK(pk);
				coDAO.save(co);
				
			}
		} catch(Exception e) {
			logger.info("Exception e [" + e.getMessage() +"]");
			throw new NirhoServiceException("Problemas con la BD al guardar el cuestionario");
		}
	}

	@Override
	public List<CuestionarioOpcion> obtenerCuestionarioOpcion(Integer idProyecto) throws NirhoServiceException {
		return coDAO.findByIdProyecto(idProyecto);
	}

	@Override
	public List<Tema> obtenerTemasProyecto(Integer idProyecto) throws NirhoServiceException {
		List<Tema> temas = new ArrayList<>();
		try {
			for(CuestionarioProyecto cp: dao.findByIdProyecto(idProyecto)) {
				temas.add(cp.getTema());
			}	
		} catch(Exception e) {
			logger.info("Exception e [" + e.getMessage() +"]");
			throw new NirhoServiceException("Problemas al obtener los temas del proyecto [" + idProyecto + "]");
		}
		return temas;
	}

	@Override
	public List<PreguntaOpcionesEVD> obtenerPreguntasOpciones(Integer idProyecto) throws NirhoServiceException {
		List<PreguntaOpcionesEVD> preguntas = new ArrayList<>();
		try {
			for(CuestionarioProyecto cp: dao.findByIdProyecto(idProyecto)) {
				PreguntaOpcionesEVD poe = new PreguntaOpcionesEVD();
				Pregunta pregunta = preguntaDAO.findByIdTema(cp.getTema().getIdTema()).get(0);
				poe.setPregunta(pregunta);
				List<Opcion> opciones = new ArrayList<>();
				for(CuestionarioOpcion co: coDAO.findByIdProyectoAndIdTema(idProyecto, cp.getTema().getIdTema())) {
					opciones.add(co.getOpcion());
				}
				poe.setOpciones(opciones);
				preguntas.add(poe);
			}	
		} catch(Exception e) {
			logger.info("Exception e [" + e.getMessage() +"]");
			throw new NirhoServiceException("Problemas al obtener los temas del proyecto [" + idProyecto + "]");
		}
		return preguntas;
	}

	@Override
	public List<CuestionarioParticipanteEVD> obtenerCuestionarioParticipanteEVD(String token) throws NirhoServiceException {
		List<CuestionarioParticipanteEVD> cuestPartEVDList = new ArrayList<>();
		try {
			ParticipantePK participantePK = NirhoUtil.obtenerParticipanteToken(token);
			Participante participante = participanteDAO.getOne(participantePK);
			if(participante != null) {
				List<CuetionarioParticipante> cuestPart = cuestPartDAO.findByParticipanteProyecto(participante.getParticipantePK().getIdParticipante(),
						participante.getParticipantePK().getIdProyecto());
				for(CuetionarioParticipante cp: cuestPart) {
					CuestionarioParticipanteEVD cuestPartEVD = new CuestionarioParticipanteEVD();
					cuestPartEVD.setCuestionarioParticipante(cp);
					List<Opcion> opciones = new ArrayList<>();
					for(CuestionarioOpcion cuestOps: coDAO.findByIdProyectoAndIdTema(participantePK.getIdProyecto(), cp.getTema().getIdTema())){
						opciones.add(cuestOps.getOpcion());
					}
					cuestPartEVD.setOpciones(opciones);
					cuestPartEVDList.add(cuestPartEVD);
				}
			}
		} catch(NirhoServiceException nse) {
			throw new NirhoServiceException(nse.getMessage());
		} catch(Exception e) {
			logger.info("Exception e [" + e.getMessage() +"]");
			throw new NirhoServiceException("Problemas al obtener el cuestionario del participante en la BD token [" + token + "]");
		}
		return cuestPartEVDList;
	}

	@Override
	public List<CuestPartEvaluadosEVD> obtenerCuestionarioEvaluadosEVD(String token) throws NirhoServiceException {
		List<CuestPartEvaluadosEVD> cuestPartEvaluadosEVDList = new ArrayList<>();
		try {
			List<CuestPartEvaluadosEVD> cuestEvals = obtenerCuestionarioEvaluadorEvaluadosEVD(token);
			CuestPartEvaluadosEVD cuest0 = cuestEvals.get(0);
			CuestPartEvaluadosEVD[] cuestPartEval = new CuestPartEvaluadosEVD[cuest0.getCuestionarioParticipantes().size()];
			for(int i=0; i<cuestPartEval.length; i++) {
				CuestPartEvaluadosEVD dto = new CuestPartEvaluadosEVD(new ArrayList<>());
				for(CuestPartEvaluadosEVD cpee: cuestEvals) {
					dto.getCuestionarioParticipantes().add(cpee.getCuestionarioParticipantes().get(i));
				}
				cuestPartEval[i] = dto;
			}
			for(CuestPartEvaluadosEVD evd: cuestPartEval) {
				cuestPartEvaluadosEVDList.add(evd);
			}
		} catch(Exception e) {
			logger.info("Exception e [" + e.getMessage() +"]");
			throw new NirhoServiceException("Problemas al obtener el cuestionario del participante en la BD token [" + token + "]");
		}
		return cuestPartEvaluadosEVDList;
	}
	
	private List<CuestPartEvaluadosEVD> obtenerCuestionarioEvaluadorEvaluadosEVD(String token) throws NirhoServiceException {
		List<CuestPartEvaluadosEVD> cuestPartEvaluadosEVDList = new ArrayList<>();
		try {
			ParticipantePK participantePK = NirhoUtil.obtenerParticipanteToken(token);
			Participante participante = participanteDAO.getOne(participantePK);	
			if(participante != null) {
				List<EvaluadorEvaluado> evaluados = evalEvalDAO.findByIdProyectoAndIdEvaluador(participante.getParticipantePK().getIdProyecto() 
						,participante.getParticipantePK().getIdParticipante());	
				for(EvaluadorEvaluado ee: evaluados) {
					List<CuestionarioParticipanteEVD> cuestPartEVDList = new ArrayList<>();
					List<CuetionarioParticipante> cuestPart = cuestPartDAO.findByParticipanteProyecto(ee.getEvaluadorEvaluadoPK().getIdEvaluado(),
							participante.getParticipantePK().getIdProyecto());
					for(CuetionarioParticipante cp: cuestPart) {
						CuestionarioParticipanteEVD cuestPartEVD = new CuestionarioParticipanteEVD();
						cuestPartEVD.setCuestionarioParticipante(cp);
						cuestPartEVD.setParticipante(participanteDAO.getOne(
								new ParticipantePK(cp.getCuetionarioParticipantePK().getIdParticipante(), cp.getCuetionarioParticipantePK().getIdProyecto())));
						List<Opcion> opciones = new ArrayList<>();
						for(CuestionarioOpcion cuestOps: coDAO.findByIdProyectoAndIdTema(participantePK.getIdProyecto(), cp.getTema().getIdTema())){
							opciones.add(cuestOps.getOpcion());
						}
						cuestPartEVD.setOpciones(opciones);
						cuestPartEVDList.add(cuestPartEVD);
					}
					cuestPartEvaluadosEVDList.add(new CuestPartEvaluadosEVD(cuestPartEVDList));
				}
			}
		} catch(NirhoServiceException nse) {
			throw new NirhoServiceException(nse.getMessage());
		} catch(Exception e) {
			logger.info("Exception e [" + e.getMessage() +"]");
			throw new NirhoServiceException("Problemas al obtener el cuestionario del participante en la BD token [" + token + "]");
		}
		return cuestPartEvaluadosEVDList;
	}
	
}

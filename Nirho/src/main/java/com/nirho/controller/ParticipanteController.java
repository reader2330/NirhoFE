package com.nirho.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nirho.constant.ProyectoConstants;
import com.nirho.dto.EmailDatos;
import com.nirho.dto.HeadCount;
import com.nirho.dto.HeadCountAmpliado;
import com.nirho.dto.NivelDTO;
import com.nirho.dto.ParticipanteHC;
import com.nirho.dto.ParticipanteHCA;
import com.nirho.exception.NirhoControllerException;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.CuestionarioProyecto;
import com.nirho.model.CuetionarioParticipante;
import com.nirho.model.CuetionarioParticipantePK;
import com.nirho.model.EstatusProyecto;
import com.nirho.model.Participante;
import com.nirho.model.ParticipantePK;
import com.nirho.model.Proyecto;
import com.nirho.model.Usuario;
import com.nirho.service.CuestionarioParticipanteService;
import com.nirho.service.CuestionarioProyectoService;
import com.nirho.service.EmailService;
import com.nirho.service.EstatusProyectoService;
import com.nirho.service.ParticipanteService;
import com.nirho.service.ProyectoService;
import com.nirho.service.UsuarioService;
import com.nirho.util.NirhoUtil;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping( value = "/participantes" )
public class ParticipanteController {
	public final static Logger logger = Logger.getLogger(ParticipanteController.class);
	@Autowired
	ParticipanteService participanteService;
	@Autowired
	ProyectoService proyectoService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private EstatusProyectoService estatusService;
	@Autowired
	private CuestionarioProyectoService cuestionarioService;
	@Autowired
	private CuestionarioParticipanteService cuestionarioParticipanteService;
	@Autowired
	UsuarioService usuarioService;

	@GetMapping(value = "/organigrama")
	public List<NivelDTO> organigrama(@RequestParam(name="idProyecto") Integer idProyecto) throws NirhoControllerException{
		List<NivelDTO> organigrama = new ArrayList<>();
		try {
			organigrama = participanteService.obtenerParticipantesPorProyecto(idProyecto);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de los proyectos");
		}
		return organigrama;
	}

	@GetMapping(value = "/participantes")
	public List<Participante> participantes(@RequestParam(name="idProyecto") Integer idProyecto) throws NirhoControllerException{
		try {
			return participanteService.obtenerParticipantes(idProyecto);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el head count de la base de datos");
		}
	}

	
	@RequestMapping(value = "/headCount", method = RequestMethod.POST)
	@ResponseBody
	public void guardarParticipantes(@RequestBody HeadCount headcount) throws NirhoControllerException {
		logger.info(" ************************ headcount [" + headcount + "] *****************************");
		try {
			Proyecto proyecto = proyectoService.obtenerProyectoPorId(headcount.getIdProyecto());
        	int estatusActual = proyecto.getIdEstatus().getIdEstatus().intValue();
        	if(!(estatusActual == ProyectoConstants.ESTATUS_CONFIGURACION.intValue() ||
        			estatusActual == ProyectoConstants.ESTATUS_CARGA.intValue())) {
        		throw new NirhoControllerException("No se ha realizado la configuracion del cuestionario en el proyecto");
        	}
			proyecto.setIdEstatus(estatusService.obtenerEstatus(ProyectoConstants.ESTATUS_CARGA));
			proyectoService.registrarProyecto(proyecto, proyecto.getIdModulo());
			List<ParticipanteHC> participantesHC = headcount.getLista();
			List<Participante> participantes = new ArrayList<>();
			for(ParticipanteHC p: participantesHC) {
				try {
					Participante participante = assamblerToParticipanteHC(p);
					ParticipantePK participantePK = new ParticipantePK(Integer.parseInt(p.getIdParticipante()), headcount.getIdProyecto());
					participante.setParticipantePK(participantePK);
					participante.setProyecto(proyecto);
					participante.setToken(NirhoUtil.obtenerToken(Integer.parseInt(p.getIdParticipante()), headcount.getIdProyecto(), participante.getRfc()));
					participantes.add(participante);
				}catch(Exception e) {
					logger.info("Exception [" + e.getMessage() + "]");
				}
			}
			participanteService.guardarParticipanteService(participantes);
		} catch (NirhoControllerException nce) {
        	throw new NirhoControllerException(nce.getMessage());
        } catch (NirhoServiceException e) {
			throw new NirhoControllerException("Problemas al registrar el participante en la BD");
		}
	}

	@RequestMapping(value = "/headCountAmp", method = RequestMethod.POST)
	@ResponseBody
	public void headCountAmp(@RequestBody HeadCountAmpliado headcount) throws NirhoControllerException {
		logger.info(" ************************ headcount [" + headcount + "] *****************************");
		try {
			Proyecto proyecto = proyectoService.obtenerProyectoPorId(headcount.getIdProyecto());
        	int estatusActual = proyecto.getIdEstatus().getIdEstatus().intValue();
        	if(!(estatusActual == ProyectoConstants.ESTATUS_CONFIGURACION.intValue() ||
        			estatusActual == ProyectoConstants.ESTATUS_CARGA.intValue())) {
        		throw new NirhoControllerException("No se ha realizado la configuracion del cuestionario en el proyecto");
        	}
			proyecto.setIdEstatus(new EstatusProyecto(ProyectoConstants.ESTATUS_CARGA));
			proyectoService.registrarProyecto(proyecto, proyecto.getIdModulo());
			List<ParticipanteHCA> participantesHCA = headcount.getLista();
			List<Participante> participantes = new ArrayList<>();
			for(ParticipanteHCA p: participantesHCA) {
				Participante participante = assamblerToParticipanteHCA(p);
				ParticipantePK participantePK = new ParticipantePK(p.getIdParticipante(), headcount.getIdProyecto());
				participante.setParticipantePK(participantePK);
				participantes.add(participante);
			}
			participanteService.ampliarParticipanteService(participantes);
		} catch (NirhoControllerException nce) {
        	throw new NirhoControllerException(nce.getMessage());
        } catch (NirhoServiceException e) {
			throw new NirhoControllerException("Problemas al registrar el participante en la BD");
		}
	}

	@RequestMapping(path = "/emailSend", method = RequestMethod.GET)
    public void sendMail(@RequestParam(name="idProyecto") Integer idProyecto, HttpServletRequest request) throws NirhoControllerException {
        try {
        	Proyecto proyecto = proyectoService.obtenerProyectoPorId(idProyecto);
        	int estatusActual = proyecto.getIdEstatus().getIdEstatus().intValue();
        	if(estatusActual != ProyectoConstants.ESTATUS_CARGA.intValue()) {
        		throw new NirhoControllerException("No se ha realizado la carga de participantes en el proyecto");
        	}

        	List<Participante> participantes = participanteService.obtenerParticipantes(idProyecto);
            for(Participante participante: participantes) {
            	enviarCorreoParticipante(participante, request);
            }
            
            EstatusProyecto estatus = new EstatusProyecto();
            estatus.setIdEstatus(ProyectoConstants.ESTATUS_ENVIADO);
            proyecto.setIdEstatus(estatus);
            proyectoService.registrarProyecto(proyecto, proyecto.getIdModulo());
        } catch (NirhoControllerException nce) {
        	throw new NirhoControllerException(nce.getMessage());
        } catch (Exception e) {
        	throw new NirhoControllerException("Problemas en el registro de envio de los correos electronicos");
        }
    }

	@RequestMapping(path = "/cuestionariosSend", method = RequestMethod.GET)
    public void cuestionariosSend(@RequestParam(name="idProyecto") Integer idProyecto, HttpServletRequest request) throws NirhoControllerException {
        try {
        	Proyecto proyecto = proyectoService.obtenerProyectoPorId(idProyecto);
        	int estatusActual = proyecto.getIdEstatus().getIdEstatus().intValue();
        	if(estatusActual < ProyectoConstants.ESTATUS_CARGA.intValue()) {
        		throw new NirhoControllerException("No se ha realizado la carga de participantes en el proyecto");
        	}

        	List<Participante> participantes = participanteService.obtenerParticipantes(idProyecto);
        	List<CuestionarioProyecto> cuestProyList = cuestionarioService.obtenerCuestionarioProyecto(idProyecto);
			for (Participante participante : participantes) {
				try {
					for (CuestionarioProyecto cp : cuestProyList) {
						CuetionarioParticipante cuetionarioParticipante = new CuetionarioParticipante();
						CuetionarioParticipantePK pk = new CuetionarioParticipantePK(
								participante.getParticipantePK().getIdParticipante(),
								participante.getParticipantePK().getIdProyecto(),
								cp.getCuestionarioProyectoPK().getIdTema(),
								cp.getCuestionarioProyectoPK().getIdPregunta());
						cuetionarioParticipante.setCuetionarioParticipantePK(pk);
						cuetionarioParticipante.setPregunta(cp.getPregunta());
						cuetionarioParticipante.setTema(cp.getTema());
						logger.info("CuestionarioParticipante [" + cuetionarioParticipante + "]");
						cuestionarioParticipanteService.guardar(cuetionarioParticipante);
					}
					enviarCorreoParticipante(participante, request);
				} catch (NirhoServiceException nse) {
					logger.info("Problemas al enviar el cuestionario a la BD, causa + [" + nse.getMessage() + "]");
				}
            }
            
            EstatusProyecto estatus = new EstatusProyecto();
            estatus.setIdEstatus(ProyectoConstants.ESTATUS_ENVIADO);
            proyecto.setIdEstatus(estatus);
            proyectoService.registrarProyecto(proyecto, proyecto.getIdModulo());
        } catch (NirhoControllerException nce) {
        	throw new NirhoControllerException(nce.getMessage());
        } catch (Exception e) {
        	throw new NirhoControllerException("Problemas en el registro de envio de los correos electronicos");
        }
    }


	private Participante assamblerToParticipanteHC(ParticipanteHC participanteHC) {
		SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
		Participante participante = new Participante();
		participante.setNivel(Integer.parseInt(participanteHC.getNivel()));
		participante.setNivelTexto(participanteHC.getNivelTexto());
		participante.setNombres(participanteHC.getNombres());
	    participante.setAPaterno(participanteHC.getAPaterno());
		participante.setAMaterno(participanteHC.getAMaterno());
		participante.setGenero(participanteHC.getGenero());
		participante.setRfc(participanteHC.getRfc());
		participante.setPuesto(participanteHC.getPuesto());
		try {
			participante.setFechaIngreso(formatDate.parse(participanteHC.getFechaIngreso()));
		} catch (ParseException e) {
			participante.setFechaIngreso(new Date());
		}
		try {
			participante.setAntigPuesto(new Double(participanteHC.getAntigPuesto()));
		} catch(Exception e) {
			logger.info("Exception [" + e.getMessage() + "]");
		}
		participante.setNivelEscolaridad(participanteHC.getNivelEscolaridad());
		participante.setOtrosEstudios(participanteHC.getOtrosEstudios());
		participante.setIdioma(participanteHC.getIdioma());
		participante.setNivelIdioma(participanteHC.getNivelIdioma());
		participante.setCorreoElectronico(participanteHC.getCorreoElectronico());
		participante.setSede(participanteHC.getSede());
		participante.setAreaOrg(participanteHC.getAreaOrg());
		return participante;
	}

	private Participante assamblerToParticipanteHCA(ParticipanteHCA participanteHCA) {
		Participante participante = new Participante();
		participante.setObjetivoPuesto(participanteHCA.getObjetivoPuesto());
		participante.setFunciones(participanteHCA.getFunciones());
		participante.setActividades(participanteHCA.getActividades());
		participante.setMetaKpi(participanteHCA.getMetaKpi());
		participante.setCantidadMeta(participante.getCantidadMeta());
		participante.setUnidadMedida(participanteHCA.getUnidadMedida());
		participante.setTiempo(participanteHCA.getTiempo());
		participante.setFrecuenciaEval(participanteHCA.getFrecuenciaEval());
		try {
			participante.setIdEvaluador(Integer.parseInt(participanteHCA.getIdEvaluador()));
		} catch(Exception e) {
			logger.info("Exception [" + e.getMessage() + "]");
		}
		return participante;
	}
	
	private void enviarCorreoParticipante(Participante participante, HttpServletRequest request) {
		try {
    		EmailDatos datos = new EmailDatos();
    		datos.setEmailDestino(participante.getCorreoElectronico());
    		datos.setNombreParticipante(participante.getNombres());
    		datos.setNombreProyecto(participante.getProyecto().getNombre());
    		datos.setToken(participante.getToken());
    		String usuario = (String) request.getAttribute("username");
			Usuario usuarioEnSesion = usuarioService.obtenerUsuario(usuario);
    		emailService.sendEmail(datos, usuarioEnSesion.getEmail());
    	} catch(NirhoServiceException nse) {
    		logger.info("Problemas al enviar un email, causa + [" + nse.getMessage() +"]");
    	}
	}
	
}

package com.nirho.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nirho.dto.ConsultorProyectoDTO;
import com.nirho.dto.PeriodoCLB;
import com.nirho.exception.NirhoControllerException;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ConsultorProyectoPK;
import com.nirho.model.Proyecto;
import com.nirho.model.Usuario;
import com.nirho.service.CatalogoService;
import com.nirho.service.ProyectoService;
import com.nirho.util.SessionUtil;

@RestController
@RequestMapping( value = "/proyecto" )
public class ProyectoController {
	public final static Logger logger = Logger.getLogger(ProyectoController.class);
	
	@Autowired
	ProyectoService proyectoService;
	@Autowired
	CatalogoService catalogoService;
	
	@RequestMapping(value = "/usuario", method = RequestMethod.GET)
	@ResponseBody
	public Usuario getUsuario(HttpServletRequest request) {
		Usuario usuario = SessionUtil.getUsuarioSession(request);
		logger.info("Usuario en Session ["+usuario+"]");
		return usuario;
	}
	
	@GetMapping(value = "/todos")
	public List<Proyecto> todos() throws NirhoControllerException{
		List<Proyecto> proyectos = new ArrayList<>();
		try {
			proyectos = proyectoService.obtenerProyectosTodos();
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de los proyectos");
		}
		return proyectos;
	}
	
	@GetMapping(value = "/porConsultor")
	public List<Proyecto> porConsultor(@RequestParam(name="idUsuario") Integer idUsuario) throws NirhoControllerException{
		List<Proyecto> proyectos = new ArrayList<>();
		try {
			proyectos = proyectoService.obtenerProyectosConsultor(idUsuario);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de los proyectos");
		}
		return proyectos;
	}
	
	@GetMapping(value = "/deConsultorEnSesion")
	public List<Proyecto> deConsultorEnSesion(HttpServletRequest request) throws NirhoControllerException{
		List<Proyecto> proyectos = new ArrayList<>();
		try {
			Long id = SessionUtil.getUsuarioSession(request).getId();
			proyectos = proyectoService.obtenerProyectosConsultor(id.intValue());
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de los proyectos");
		}
		return proyectos;
	}
			
	@RequestMapping(value = "/porId", method = RequestMethod.GET)
	@ResponseBody
	public Proyecto porRfc(@RequestParam(name="idProyecto") Integer idProyecto) throws NirhoControllerException{
		Proyecto proyecto = new Proyecto();
		try {
			proyecto = proyectoService.obtenerProyectoPorId(idProyecto);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro del proyecto");
		}
		return proyecto;
	}
	
	@RequestMapping(value = "/registrar", method = RequestMethod.POST)
	@ResponseBody
	public void registrarProyecto(@RequestBody Proyecto proyecto) throws NirhoControllerException {
		logger.info(" ********************************* proyecto [" + proyecto + "] *****************************");
		try {
			proyectoService.registrarProyecto(proyecto);
		} catch (NirhoServiceException e) {
			throw new NirhoControllerException("Problemas al registrar el proyecto en la BD");
		}
	}
		
	@RequestMapping(value = "/agignarPeriodoGarantia", method = RequestMethod.POST)
	@ResponseBody
	public void agignarPeriodoGarantia(@RequestBody PeriodoCLB periodo) throws NirhoControllerException {
		logger.info(" ********************************* periodo [" + periodo + "] *****************************");
		try {
			SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");			
			try {
				periodo.getProyecto().setFechaRegistro(formatDate.parse(periodo.getFechaRegistro()));
				periodo.getProyecto().setFechaFin(formatDate.parse(periodo.getFechaFin()));
			} catch (ParseException e) {
				logger.info("ParserExceptio [" + e.getLocalizedMessage() +"]");
			}  catch (NullPointerException e) {
				logger.info("NullPointerExceptio [" + e.getLocalizedMessage() +"]");
			}
			proyectoService.registrarProyecto(periodo.getProyecto());
		} catch (NirhoServiceException e) {
			throw new NirhoControllerException("Problemas al registrar el proyecto en la BD");
		}
	}
	
	@RequestMapping(value = "/asignarConsultor", method = RequestMethod.POST)
	@ResponseBody
	public void asignarConsultor(@RequestBody ConsultorProyectoDTO datos) throws NirhoControllerException {
		logger.info(" ********************************* ConsultorProyecto [" + datos + "] *****************************");
		try {
			ConsultorProyectoPK conProPK = new ConsultorProyectoPK(new Integer(datos.getIdUsuario()), new Integer(datos.getIdProyecto()));
			proyectoService.asignarConsultor(conProPK);
		} catch (NirhoServiceException e) {
			throw new NirhoControllerException("Problemas al registrar el proyecto en la BD");
		}
	}
	
	@RequestMapping(value = "/periodoGarantiaMago", method = RequestMethod.POST)
	@ResponseBody
	public void periodoGarantiaMago(@RequestBody PeriodoCLB periodo) throws NirhoControllerException {
		logger.info(" ********************************* periodo [" + periodo + "] *****************************");
		try {
			SimpleDateFormat formatDate = new SimpleDateFormat("MMM dd yyyy hh:mm:ss");			
			try {
				periodo.getProyecto().setFechaRegistro(formatDate.parse(convertSpain(periodo.getFechaRegistro())));
				periodo.getProyecto().setFechaFin(formatDate.parse(convertSpain(periodo.getFechaFin())));
			} catch (ParseException e) {
				logger.info("ParserExceptio [" + e.getLocalizedMessage() +"]");
			}  catch (NullPointerException e) {
				logger.info("NullPointerExceptio [" + e.getLocalizedMessage() +"]");
			}
			proyectoService.registrarProyecto(periodo.getProyecto());
		} catch (NirhoServiceException e) {
			throw new NirhoControllerException("Problemas al registrar el proyecto en la BD");
		}
	}
	
	private String convertSpain(String fechaIngles) {
		String traduccion = null;
		if(fechaIngles != null) {
			traduccion = fechaIngles.replace("Jan", "Ene").replace("Dec", "Dic").replace("Apr", "Abr").replace("Aug", "Ago");
		}
		return traduccion;
	}
}

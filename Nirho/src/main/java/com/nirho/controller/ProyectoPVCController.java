package com.nirho.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nirho.constant.ProyectoConstants;
import com.nirho.dto.PeriodoProyecto;
import com.nirho.exception.NirhoControllerException;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ConsultorProyectoPK;
import com.nirho.model.EstatusProyecto;
import com.nirho.model.ParticipanteAPOAmpActividad;
import com.nirho.model.ParticipanteAPOAmpFuncion;
import com.nirho.model.Proyecto;
import com.nirho.model.ProyectoPVCArea;
import com.nirho.model.ProyectoPVCConocimiento;
import com.nirho.model.ProyectoPVCEsfera;
import com.nirho.model.ProyectoPVCEspecialidad;
import com.nirho.model.ProyectoPVCNivel;
import com.nirho.service.EmpresaService;
import com.nirho.service.EstatusProyectoService;
import com.nirho.service.GraficasProyectoService;
import com.nirho.service.ProyectoPVCAreaService;
import com.nirho.service.ProyectoPVCConocimientoService;
import com.nirho.service.ProyectoPVCEsferaService;
import com.nirho.service.ProyectoPVCEspecialidadService;
import com.nirho.service.ProyectoPVCNivelService;
import com.nirho.service.ProyectoService;
import com.nirho.util.ReporteUtil;
import com.nirho.util.SessionUtil;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping( value = "/proyectoPVC" )
public class ProyectoPVCController {
	
	public final static Logger logger = Logger.getLogger(ProyectoPVCController.class);
	public final static Integer ID_MODULO = 5;
	
	@Autowired
	private ProyectoService proyectoService;
	@Autowired
	private ProyectoPVCAreaService proyectoPVCAreaService;
	@Autowired
	private ProyectoPVCEsferaService proyectoPVCEsferaService;
	@Autowired
	private ProyectoPVCNivelService proyectoPVCNivelService;
	@Autowired
	private ProyectoPVCEspecialidadService proyectoPVCEspecialidadService;
	@Autowired
	private ProyectoPVCConocimientoService proyectoPVCConocimientoService;
	@Autowired
	private EstatusProyectoService estatusService;
	@Autowired
	private GraficasProyectoService graficasService;
	@Autowired
	EmpresaService empresaService;
	
	@GetMapping(value = "/todos")
	public List<Proyecto> todos() throws NirhoControllerException{
		List<Proyecto> proyectos = new ArrayList<>();
		try {
			proyectos = proyectoService.obtenerProyectosTodos(ID_MODULO);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de los proyectos");
		}
		return proyectos;
	}
	
	@GetMapping(value = "/porConsultor")
	public List<Proyecto> porConsultor(@RequestParam(name="idUsuario") Integer idUsuario) throws NirhoControllerException{
		List<Proyecto> proyectos = new ArrayList<>();
		try {
			proyectos = proyectoService.obtenerProyectosConsultor(idUsuario, ID_MODULO);
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
			proyectos = proyectoService.obtenerProyectosConsultor(id.intValue(), ID_MODULO);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de los proyectos");
		}
		return proyectos;
	}
			
	@RequestMapping(value = "/porId", method = RequestMethod.GET)
	@ResponseBody
	public Proyecto porId(@RequestParam(name="idProyecto") Integer idProyecto) throws NirhoControllerException{
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
			proyecto.setIdEstatus(estatusService.obtenerEstatus(ProyectoConstants.ESTATUS_CAPTURA));
			proyectoService.registrarProyecto(proyecto, ID_MODULO);
		} catch (NirhoServiceException e) {
			throw new NirhoControllerException("Problemas al registrar el proyecto en la BD");
		}
	}
		
	@RequestMapping(value = "/agignarPeriodoGarantia", method = RequestMethod.POST)
	@ResponseBody
	public void agignarPeriodoGarantia(@RequestBody PeriodoProyecto periodo) throws NirhoControllerException {
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
			periodo.getProyecto().setIdEstatus(estatusService.obtenerEstatus(ProyectoConstants.ESTATUS_ASIGNADO));
			proyectoService.registrarProyecto(periodo.getProyecto(), ID_MODULO);
		} catch (NirhoServiceException e) {
			throw new NirhoControllerException("Problemas al registrar el proyecto en la BD");
		}
	}
	
	@RequestMapping(value = "/asignarConsultor", method = RequestMethod.POST)
	@ResponseBody
	public void asignarConsultor(@RequestBody ConsultorProyectoPK datos) throws NirhoControllerException {
		logger.info(" ********************************* ConsultorProyecto [" + datos + "] *****************************");
		try {
			proyectoService.asignarConsultor(datos);
		} catch (NirhoServiceException e) {
			throw new NirhoControllerException("Problemas al registrar el proyecto en la BD");
		}
	}
	
	@RequestMapping(value = "/estatus", method = RequestMethod.GET)
	@ResponseBody
	public EstatusProyecto estatus(@RequestParam(name="idProyecto") Integer idProyecto) throws NirhoControllerException{
		EstatusProyecto estatus = new EstatusProyecto();
		try {
			estatus = proyectoService.obtenerProyectoPorId(idProyecto).getIdEstatus();
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro del proyecto");
		}
		return estatus;
	}
	
	@RequestMapping(value = "/apertura", method = RequestMethod.GET)
	@ResponseBody
	public void apertura(@RequestParam(name="idProyecto") Integer idProyecto) throws NirhoControllerException{
		try {
			Proyecto proyecto = proyectoService.obtenerProyectoPorId(idProyecto);
			EstatusProyecto estatus = estatusService.obtenerEstatus(ProyectoConstants.ESTATUS_VIGENTE);
			proyecto.setIdEstatus(estatus);
			proyectoService.registrarProyecto(proyecto, proyecto.getIdModulo());
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al registrar el proyecto");
		}
	}
	
	@RequestMapping(value = "/cierre", method = RequestMethod.GET)
	@ResponseBody
	public void cierre(@RequestParam(name="idProyecto") Integer idProyecto) throws NirhoControllerException{
		try {
			Proyecto proyecto = proyectoService.obtenerProyectoPorId(idProyecto);
			EstatusProyecto estatus = estatusService.obtenerEstatus(ProyectoConstants.ESTATUS_FINALIZADO);
			proyecto.setIdEstatus(estatus);
			proyectoService.registrarProyecto(proyecto, proyecto.getIdModulo());
			graficasService.generarGraficasProyecto(idProyecto);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al registrar el proyecto");
		}
	}
	
	@RequestMapping(value = "/revisionResultados", method = RequestMethod.GET)
	@ResponseBody
	public void revisionResultados(@RequestParam(name="idProyecto") Integer idProyecto) throws NirhoControllerException{
		try {
			Proyecto proyecto = proyectoService.obtenerProyectoPorId(idProyecto);
			EstatusProyecto estatus = estatusService.obtenerEstatus(ProyectoConstants.ESTATUS_RESULTADOS);

			proyecto.setIdEstatus(estatus);
			proyectoService.registrarProyecto(proyecto, proyecto.getIdModulo());
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al registrar el proyecto");
		}
	}
	
	
	
	
	@RequestMapping(value = "/{idProyecto}/areas", method = RequestMethod.GET)
	public List<ProyectoPVCArea> getAreaPorProyecto(@PathVariable("idProyecto") int idProyecto) throws NirhoControllerException{
		try {
			return proyectoPVCAreaService.getByProyecto(idProyecto);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro");
		}
	}
	
	@RequestMapping(value = "/areas/{id}", method = RequestMethod.GET)
	public ProyectoPVCArea getArea(@PathVariable("id") int id) throws NirhoControllerException{
		try {
			return proyectoPVCAreaService.getOne(id);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro");
		}
	}
	
	@RequestMapping(value = "/areas/guardar", method = RequestMethod.POST)
	public void saveArea(@Valid @RequestBody ProyectoPVCArea f) throws NirhoControllerException{
		try {
			proyectoPVCAreaService.guardar(f);	
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar");
		} catch(Exception exe) {
			throw new NirhoControllerException("Problemas al registrar");
		} 
	}
	
	@RequestMapping(value = "/areas/guardarTodas", method = RequestMethod.POST)
	public void saveArea(@Valid @RequestBody List<ProyectoPVCArea> l) throws NirhoControllerException{
		try {
			for(ProyectoPVCArea i: l) {
				proyectoPVCAreaService.guardar(i);	
			}
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar");
		} catch(Exception exe) {
			throw new NirhoControllerException("Problemas al registrar");
		} 
	}
	
	
	
	@RequestMapping(value = "/areas/{id}/esferas", method = RequestMethod.GET)
	public Set<ProyectoPVCEsfera> getEsferaPorArea(@PathVariable("id") int id) throws NirhoControllerException{
		try {
			ProyectoPVCArea area = proyectoPVCAreaService.getOne(id);
			return area.getEsferas();
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro");
		}
	}
	
	@RequestMapping(value = "/areas/esferas/{id}", method = RequestMethod.GET)
	public ProyectoPVCEsfera getEsfera(@PathVariable("id") int id) throws NirhoControllerException{
		try {
			return proyectoPVCEsferaService.getOne(id);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro");
		}
	}
	
	@RequestMapping(value = "/areas/esferas/guardar", method = RequestMethod.POST)
	public void saveEsfera(@Valid @RequestBody ProyectoPVCEsfera f) throws NirhoControllerException{
		try {
			proyectoPVCEsferaService.guardar(f);	
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar");
		} catch(Exception exe) {
			throw new NirhoControllerException("Problemas al registrar");
		} 
	}
	
	@RequestMapping(value = "/areas/{id}/esferas/guardarTodas", method = RequestMethod.POST)
	public void saveEsfera(@Valid @RequestBody Set<ProyectoPVCEsfera> l, @PathVariable("id") int id) throws NirhoControllerException{
		try {
			ProyectoPVCArea p = proyectoPVCAreaService.getOne(id);
			if(p.getEsferas() == null) {
				p.setEsferas(l);
			} else {
				p.getEsferas().addAll(l);
			}
			proyectoPVCAreaService.guardar(p);
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar");
		} catch(Exception exe) {
			throw new NirhoControllerException("Problemas al registrar");
		} 
	}

	
	
	
	@RequestMapping(value = "/esferas/{id}/niveles", method = RequestMethod.GET)
	public Set<ProyectoPVCNivel> getNivelPorEsfera(@PathVariable("id") int id) throws NirhoControllerException{
		try {
			ProyectoPVCEsfera esfera = proyectoPVCEsferaService.getOne(id);
			return esfera.getNiveles();
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro");
		}
	}
	
	@RequestMapping(value = "/esferas/niveles/{id}", method = RequestMethod.GET)
	public ProyectoPVCNivel getNivel(@PathVariable("id") int id) throws NirhoControllerException{
		try {
			return proyectoPVCNivelService.getOne(id);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro");
		}
	}
	
	@RequestMapping(value = "/esferas/niveles/guardar", method = RequestMethod.POST)
	public void saveNivel(@Valid @RequestBody ProyectoPVCNivel f) throws NirhoControllerException{
		try {
			proyectoPVCNivelService.guardar(f);	
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar");
		} catch(Exception exe) {
			throw new NirhoControllerException("Problemas al registrar");
		} 
	}
	
	@RequestMapping(value = "/esferas/{id}/niveles/guardarTodas", method = RequestMethod.POST)
	public void saveNivel(@Valid @RequestBody Set<ProyectoPVCNivel> l, @PathVariable("id") int id) throws NirhoControllerException{
		try {
			ProyectoPVCEsfera p = proyectoPVCEsferaService.getOne(id);
			if(p.getNiveles() == null) {
				p.setNiveles(l);
			} else {
				p.getNiveles().addAll(l);
			}
			proyectoPVCEsferaService.guardar(p);
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar");
		} catch(Exception exe) {
			throw new NirhoControllerException("Problemas al registrar");
		} 
	}
	
	
	
	
	@RequestMapping(value = "/niveles/{id}/especialidades", method = RequestMethod.GET)
	public Set<ProyectoPVCEspecialidad> getEspecialidadPorNivel(@PathVariable("id") int id) throws NirhoControllerException{
		try {
			ProyectoPVCNivel nivel = proyectoPVCNivelService.getOne(id);
			return nivel.getEspecialidades();
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro");
		}
	}
	
	@RequestMapping(value = "/niveles/especialidades/{id}", method = RequestMethod.GET)
	public ProyectoPVCEspecialidad getEspecialidad(@PathVariable("id") int id) throws NirhoControllerException{
		try {
			return proyectoPVCEspecialidadService.getOne(id);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro");
		}
	}
	
	@RequestMapping(value = "/niveles/especialidades/guardar", method = RequestMethod.POST)
	public void saveEspecialidad(@Valid @RequestBody ProyectoPVCEspecialidad f) throws NirhoControllerException{
		try {
			proyectoPVCEspecialidadService.guardar(f);	
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar");
		} catch(Exception exe) {
			throw new NirhoControllerException("Problemas al registrar");
		} 
	}
	
	@RequestMapping(value = "/niveles/{id}/especialidades/guardarTodas", method = RequestMethod.POST)
	public void saveEspecialidad(@Valid @RequestBody Set<ProyectoPVCEspecialidad> l, @PathVariable("id") int id) throws NirhoControllerException{
		try {
			ProyectoPVCNivel p = proyectoPVCNivelService.getOne(id);
			if(p.getEspecialidades() == null) {
				p.setEspecialidades(l);
			} else {
				p.getEspecialidades().addAll(l);
			}
			proyectoPVCNivelService.guardar(p);
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar");
		} catch(Exception exe) {
			throw new NirhoControllerException("Problemas al registrar");
		} 
	}
	
	
	
	
	@RequestMapping(value = "/especialidades/{id}/conocimiento", method = RequestMethod.GET)
	public Set<ProyectoPVCConocimiento> getConocimientoPorEspecialidad(@PathVariable("id") int id) throws NirhoControllerException{
		try {
			ProyectoPVCEspecialidad especialidad = proyectoPVCEspecialidadService.getOne(id);
			return especialidad.getConocimientos();
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro");
		}
	}
	
	@RequestMapping(value = "/especialidades/conocimientos/{id}", method = RequestMethod.GET)
	public ProyectoPVCConocimiento getConocimiento(@PathVariable("id") int id) throws NirhoControllerException{
		try {
			return proyectoPVCConocimientoService.getOne(id);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro");
		}
	}
	
	@RequestMapping(value = "/especialidades/conocimientos/guardar", method = RequestMethod.POST)
	public void saveConocimiento(@Valid @RequestBody ProyectoPVCConocimiento f) throws NirhoControllerException{
		try {
			proyectoPVCConocimientoService.guardar(f);	
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar");
		} catch(Exception exe) {
			throw new NirhoControllerException("Problemas al registrar");
		} 
	}
	
	@RequestMapping(value = "/especialidades/{id}/conocimientos/guardarTodas", method = RequestMethod.POST)
	public void saveConocimiento(@Valid @RequestBody Set<ProyectoPVCConocimiento> l, @PathVariable("id") int id) throws NirhoControllerException{
		try {
			ProyectoPVCEspecialidad p = proyectoPVCEspecialidadService.getOne(id);
			if(p.getConocimientos() == null) {
				p.setConocimientos(l);
			} else {
				p.getConocimientos().addAll(l);
			}
			proyectoPVCEspecialidadService.guardar(p);
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar");
		} catch(Exception exe) {
			throw new NirhoControllerException("Problemas al registrar");
		} 
	}
	
	@RequestMapping(value = "/especialidades/{id}/conocimientos/tecnicos/guardarTodas", method = RequestMethod.POST)
	public void saveConocimiento1(@Valid @RequestBody Set<ProyectoPVCConocimiento> l, @PathVariable("id") int id) throws NirhoControllerException{
		try {
			for(ProyectoPVCConocimiento i: l) {
				i.setTipo(1);
			}
			ProyectoPVCEspecialidad p = proyectoPVCEspecialidadService.getOne(id);
			if(p.getConocimientos() == null) {
				p.setConocimientos(l);
			} else {
				p.getConocimientos().addAll(l);
			}
			proyectoPVCEspecialidadService.guardar(p);
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar");
		} catch(Exception exe) {
			throw new NirhoControllerException("Problemas al registrar");
		} 
	}
	
	@RequestMapping(value = "/especialidades/{id}/conocimientos/humanisticos/guardarTodas", method = RequestMethod.POST)
	public void saveConocimiento2(@Valid @RequestBody Set<ProyectoPVCConocimiento> l, @PathVariable("id") int id) throws NirhoControllerException{
		try {
			for(ProyectoPVCConocimiento i: l) {
				i.setTipo(2);
			}
			ProyectoPVCEspecialidad p = proyectoPVCEspecialidadService.getOne(id);
			if(p.getConocimientos() == null) {
				p.setConocimientos(l);
			} else {
				p.getConocimientos().addAll(l);
			}
			proyectoPVCEspecialidadService.guardar(p);
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar");
		} catch(Exception exe) {
			throw new NirhoControllerException("Problemas al registrar");
		} 
	}
	
	
	
	@RequestMapping(value = "/reporte", method = RequestMethod.GET)
	@ResponseBody
	public void genearReporte(@RequestParam(name="idProyecto") Integer idProyecto, HttpServletResponse response) throws NirhoControllerException{
		try {
			
			URL sqlScriptUrl = ProyectoAPOController.class.getClassLoader().getResource("plantillaReporteAPO.docx");
			
			ZipSecureFile.setMinInflateRatio(0);
	        
	        XWPFDocument document = new XWPFDocument(OPCPackage.open(sqlScriptUrl.getPath())); 
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        
	        Proyecto proyecto = proyectoService.obtenerProyectoPorId(idProyecto);
	     
	        ReporteUtil.reemplazarParrafo(document, "nombre.empresa", proyecto.getIdEmpresa().getEmpresa());
	        
	        ReporteUtil.agregarTitulo(document, "Tabla de contenido");
	        
	        ReporteUtil.agregarTablaContenido(document);
	        
	        ReporteUtil.nuevaPagina(document);
	        
	        ReporteUtil.agregarTitulo1(document, "Introducción");
	        
	        ReporteUtil.agregarTitulo2(document, "Administración por Objetivos");
	        ReporteUtil.agregarParrafo(document, "Los objetivos corporativos son las declaraciones cualitativas y cuantitativas del futuro deseado para la organización.\n" +
	        "Las organizaciones cuentan con objetivos corporativos, que deberán de ser traducidos en objetivos individuales para cada una de las personas que laboran en la misma empresa. \n" +
	        "Los responsables de esta tarea son los líderes (Gerentes o Directores) quienes junto con su equipo de trabajo establecen metas comunes. Lo anterior exige un enfoque participativo y el constante apoyo sin importar la jerarquía para el logro de los mismos, ya que en conjunto garantizarán el éxito empresarial.");
	        
	        ReporteUtil.agregarTitulo2(document, "Lineamientos para la gestión APO");
	        List<String> lista1 = new ArrayList<>();
	        lista1.add("Adhesión de todos los integrantes de la empresa sin importar el nivel jerarquico");
	        lista1.add("Cascadeo de la metas una vez establecida por alta dirección");
	        lista1.add("Establecimiento de metas por áreas y por persona");
	        lista1.add("Autonomía en la gestión de las actividades que garanticen el cumplimiento de la meta");
	        lista1.add("Evaluación del cumplimiento de las metas");
	        ReporteUtil.agregarLista(document, lista1);
	        
	        ReporteUtil.agregarTitulo2(document, "Nuestros objetivos son:");
	        List<String> lista2 = new ArrayList<>();
	        lista2.add("Medibles");
	        lista2.add("Factibles");
	        lista2.add("Flexibles");
	        lista2.add("Motivadores");
	        lista2.add("Comprensibles");
	        lista2.add("Convincentes");
	        ReporteUtil.agregarLista(document, lista2);
	        
	        ReporteUtil.agregarTitulo2(document, "Beneficios APO");
	        List<String> lista3 = new ArrayList<>();
	        lista3.add("Permite definir para cada personas las expectativas de desempeño");
	        lista3.add("Mejora la comunicación entre el líder y su equipo");
	        lista3.add("Ayuda a la planeación y al logro de los objetivos a largo plazo");
	        lista3.add("Genera niveles más altos de compromiso para cada integrante");
	        lista3.add("Logra equidad en los procesos de evaluación de desempeño");
	        ReporteUtil.agregarLista(document, lista3);
	        
	        ReporteUtil.nuevaPagina(document);
	        
	        ReporteUtil.agregarTitulo1(document, "Resultados por Empresa");
	        
	        ReporteUtil.agregarTitulo(document, "Cumplimiento por área respecto al promedio empresarial");
	        
	        List<String> headersT1 = new ArrayList<>();
	        headersT1.add("AREA");
	        headersT1.add("FUNCION");
	        headersT1.add("PROMEDIO DE CUMPLIMIENTO");
	        
	        List<List<String>> contentT1 = new ArrayList<>();
	        List<String> row1T1 = new ArrayList<>();
	        row1T1.add("1");
	        row1T1.add("2");
	        row1T1.add("3");
	        contentT1.add(row1T1);
	        List<String> row2T1 = new ArrayList<>();
	        row2T1.add("4");
	        row2T1.add("5");
	        row2T1.add("6");
	        contentT1.add(row2T1);
	        
	        ReporteUtil.crearTablaTitle(document, headersT1, contentT1);
	        
	        ReporteUtil.agregarSeparadorEnBlanco(document);
	        
	        ReporteUtil.agregarTitulo(document, "Resumen de la empresa");
	        
	        List<String> headers = new ArrayList<>();
	        headers.add("Promedio de cumplimiento");
	        headers.add("Mayor cumplimiento");
	        headers.add("Mínimo cumplimiento");
	        headers.add("Consultor niRHo");
	        
	        List<List<String>> content = new ArrayList<>();
	        List<String> row1 = new ArrayList<>();
	        row1.add("1");
	        content.add(row1);
	        List<String> row2 = new ArrayList<>();
	        row2.add("1");
	        content.add(row2);
	        List<String> row3 = new ArrayList<>();
	        row3.add("1");
	        content.add(row3);
	        List<String> row4 = new ArrayList<>();
	        row4.add("1");
	        content.add(row4);
	        
	        ReporteUtil.crearTablaFirstRowTitle(document, headers, content);
	        
	        ReporteUtil.agregarSeparadorEnBlanco(document);
	        
	        ReporteUtil.nuevaPagina(document);
	        
	        ReporteUtil.agregarTitulo1(document, "Resumen por Área");
	        
	        ReporteUtil.agregarTitulo(document, "Resumen de Área");
	        
	        ReporteUtil.nuevaPagina(document);
	        
	        ReporteUtil.agregarTitulo1(document, "Resultados Individuales");
	        
	        ReporteUtil.agregarTitulo(document, "Resumen personal");
	        
	        response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document"); 
	        response.setHeader("Content-Disposition", "attachment; filename=test.docx");
	        document.write(response.getOutputStream());
	   
	        response.flushBuffer();

		} catch(IOException | InvalidFormatException e){
			throw new NirhoControllerException("Problemas al generar reporte");
		} catch (NirhoServiceException e) {
			throw new NirhoControllerException("Problemas al generar reporte");
		}
	}
	
}

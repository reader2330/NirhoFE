package com.nirho.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.jboss.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;
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

import com.nirho.dto.SolicitanteVacanteDTO;
import com.nirho.exception.NirhoControllerException;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ActividadesPuestoVacante;
import com.nirho.model.Candidato;
import com.nirho.model.CaracteristicasCandidatoCv;
import com.nirho.model.CaracteristicasCandidatoVacante;
import com.nirho.model.CompetenciasVacante;
import com.nirho.model.ConocimientoVacante;
import com.nirho.model.ContratacionVacante;
import com.nirho.model.EntrevistaVacante;
import com.nirho.model.SolicitanteVacante;
import com.nirho.service.CandidatoService;
import com.nirho.service.ContratacionVacanteService;
import com.nirho.service.EntrevistaVacanteService;
import com.nirho.service.SolicitanteVacanteService;
import com.nirho.util.ReporteUtil;



@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping( value = "/vacante" )
public class SolicitanteVacanteController {
	
	public final static Logger logger = Logger.getLogger(SolicitanteController.class);
	 
	@Autowired
	SolicitanteVacanteService solicitanteVacanteService;
	@Autowired
	CandidatoService candidatoService;
	@Autowired
	ContratacionVacanteService contratacionVacanteService;
	@Autowired
	EntrevistaVacanteService entrevistaVacanteService;
	
	@GetMapping(value = "/todos")
	public List<SolicitanteVacante> todos() throws NirhoControllerException{
		try {
			return solicitanteVacanteService.getAll();
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de los entidads");
		}
	}
	
	@GetMapping(value = "/todos/candidatos")
	public List<SolicitanteVacanteDTO> todosConCandidatos() throws NirhoControllerException{
		try {
			List<SolicitanteVacanteDTO> response = new ArrayList<>();
			for(SolicitanteVacante v: solicitanteVacanteService.getAll()) {
				List<Candidato> candidatos = candidatoService.getAllByVacante(v.getId());
				SolicitanteVacanteDTO s = new SolicitanteVacanteDTO(v.getId(), v.getAniosExperiencia(), v.getEstadoVacante(), v.getGiro(), v.getMotivo(), v.getStatus(), v.getNombreVacante(), v.getNumVacantes(), v.getPuesto(), v.getPuestoCargo(), v.getPuestoReporta(), candidatos, v.getActividades() , v.getCaracteristicas(), v.getCompetencias(), v.getConocimientos());
				response.add(s);
			}
			return response;
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de los entidads");
		}
	}
	
	@RequestMapping(value = "/{id}/contrato", method = RequestMethod.GET)
	public List<ContratacionVacante> getContrato(@PathVariable("id") long id) throws NirhoControllerException{
		try {
			return contratacionVacanteService.getByIdVacante(id);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de candidato");
		}
	}
	
	@GetMapping(value = "/conteo")
	public String count() throws NirhoControllerException{
		try {
			JSONObject response = new JSONObject();
			response.accumulate("total", solicitanteVacanteService.getAll().size());
			return response.toString();
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de los entidads");
		} catch (JSONException e) {
			throw new NirhoControllerException("Problemas al obtener el registro de los entidads");
		}
	}
	
	@GetMapping(value = "/totales")
	public String totales() throws NirhoControllerException{
		try {
			JSONObject response = new JSONObject();
			int abiertas = 0;
			int asignadas = 0;
			for(SolicitanteVacante v: solicitanteVacanteService.getAll()) {
				if(v.getStatus() == 1) {
					abiertas++;
				}
				if(v.getStatus() == 2) {
					asignadas++;
				}
			}
			response.accumulate("abiertas", abiertas);
			response.accumulate("asignadas", asignadas);
			return response.toString();
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de los entidads");
		} catch (JSONException e) {
			throw new NirhoControllerException("Problemas al obtener el registro de los entidads");
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public SolicitanteVacante get(@PathVariable("id") long id) throws NirhoControllerException{
		try {
			return solicitanteVacanteService.getOne(id);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de entidad");
		}
	}
	
	@RequestMapping(value = "/{id}/contratacion", method = RequestMethod.GET)
	public List<ContratacionVacante> getContratacion(@PathVariable("id") long id) throws NirhoControllerException{
		try {
			return contratacionVacanteService.getByIdVacante(id);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de entidad");
		}
	}
	
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String add(@Valid @RequestBody SolicitanteVacante e) throws NirhoControllerException{
		try {
			solicitanteVacanteService.editar(e);
			JSONObject json = new JSONObject();
			json.accumulate("id", e.getId());
			return json.toString();
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar entidad");
		} catch (JSONException e1) {
			throw new NirhoControllerException("Problemas al registrar entidad");
		} 
	}
	
	@RequestMapping(value = "/eliminar", method = RequestMethod.POST)
	public void delete(@Valid @RequestBody long id) throws NirhoControllerException{
		try {
			solicitanteVacanteService.eliminar(id);
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar entidad");
		} 
	}
	
	@RequestMapping(value = "/{id}/actividades/guardar", method = RequestMethod.POST)
	public String addAct(@PathVariable("id") long id, @Valid @RequestBody ActividadesPuestoVacante l) throws NirhoControllerException{
		try {
			SolicitanteVacante s = solicitanteVacanteService.getOne(id);
			if(s != null) {
				s.getActividades().add(l);
				solicitanteVacanteService.editar(s);
				JSONObject json = new JSONObject();
				json.accumulate("id", l.getId());
				return json.toString();
			}
			return null;
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar solicitante");
		} catch (JSONException e1) {
			throw new NirhoControllerException("Problemas al registrar entidad");
		} 
	}
	
	@RequestMapping(value = "/{id}/conocimientos/guardar", method = RequestMethod.POST)
	public String addConocimientos(@PathVariable("id") long id, @Valid @RequestBody ConocimientoVacante l) throws NirhoControllerException{
		try {
			SolicitanteVacante s = solicitanteVacanteService.getOne(id);
			if(s != null) {
				s.getConocimientos().add(l);
				solicitanteVacanteService.editar(s);
				JSONObject json = new JSONObject();
				json.accumulate("id", l.getId());
				return json.toString();
			}
			return null;
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar solicitante");
		} catch (JSONException e1) {
			throw new NirhoControllerException("Problemas al registrar entidad");
		} 
	}
	
	@RequestMapping(value = "/{id}/competencias/guardar", method = RequestMethod.POST)
	public String addCompetencias(@PathVariable("id") long id, @Valid @RequestBody CompetenciasVacante l) throws NirhoControllerException{
		try {
			SolicitanteVacante s = solicitanteVacanteService.getOne(id);
			if(s != null) {
				s.getCompetencias().add(l);
				solicitanteVacanteService.editar(s);
				JSONObject json = new JSONObject();
				json.accumulate("id", l.getId());
				return json.toString();
			}
			return null;
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar solicitante");
		} catch (JSONException e1) {
			throw new NirhoControllerException("Problemas al registrar entidad");
		} 
	}
	
	@RequestMapping(value = "/{id}/caracteristicas/guardar", method = RequestMethod.POST)
	public String addCaracteristicas(@PathVariable("id") long id, @Valid @RequestBody CaracteristicasCandidatoVacante l) throws NirhoControllerException{
		try {
			SolicitanteVacante s = solicitanteVacanteService.getOne(id);
			if(s != null) {
				s.getCaracteristicas().add(l);
				solicitanteVacanteService.editar(s);
				JSONObject json = new JSONObject();
				json.accumulate("id", l.getId());
				return json.toString();
			}
			return null;
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar solicitante");
		} catch (JSONException e1) {
			throw new NirhoControllerException("Problemas al registrar entidad");
		} 
	}
	
	@RequestMapping(value = "/reporte", method = RequestMethod.GET)
	@ResponseBody
	public void genearReporteIndividual(@RequestParam(name="idVacante") long idVacante, HttpServletResponse response) throws NirhoControllerException{
		
		try {
			    
			ZipSecureFile.setMinInflateRatio(0);
			XWPFDocument document = new XWPFDocument(OPCPackage.open("/opt/jboss-eap-7.1/standalone/deployments/reporteRYS.docx"));
			//XWPFDocument document = new XWPFDocument(OPCPackage.open("C:\\Users\\pruebas\\elimina\\reporteRYS.docx"));

			SolicitanteVacante vacante = solicitanteVacanteService.getOne(idVacante);

	        XWPFTable informacionGeneral =  ReporteUtil.getTablaPorTitulo(document, "Datos de vacante");
	        XWPFTable actividades =  ReporteUtil.getTablaPorTitulo(document, "Actividades");
	        XWPFTable caracteristicas =  ReporteUtil.getTablaPorTitulo(document, "Características");
	        XWPFTable competencias =  ReporteUtil.getTablaPorTitulo(document, "Competencias");
	        XWPFTable conocimientos =  ReporteUtil.getTablaPorTitulo(document, "Conocimientos");
	        
	        if(informacionGeneral != null){
	        	
	            XWPFTableRow row3 = informacionGeneral.getRow(3);
	            row3.getCell(1).setText(vacante.getNombreVacante());
	            row3.getCell(4).setText(vacante.getPuesto());
	            
	            XWPFTableRow row4 = informacionGeneral.getRow(4);
	            row4.getCell(1).setText(vacante.getAniosExperiencia() + "");
	            row4.getCell(4).setText(vacante.getEstadoVacante() == 1 ? "Creado" : "Asignado");
	            
	            XWPFTableRow row5 = informacionGeneral.getRow(5);
	            row5.getCell(1).setText(vacante.getGiro() + "");
	            row5.getCell(4).setText(vacante.getMotivo() + "");
	            
	            XWPFTableRow row6 = informacionGeneral.getRow(6);
	            row6.getCell(1).setText(vacante.getNumVacantes() + "");
	            row6.getCell(4).setText(vacante.getPuestoCargo());
	            
	            XWPFTableRow row7 = informacionGeneral.getRow(7);
	            row7.getCell(1).setText(vacante.getPuestoReporta());
	        }
	        
	        if(actividades != null) {        	
	        	for(ActividadesPuestoVacante e : vacante.getActividades()) {
	        		XWPFTableRow row = actividades.createRow();
	        		row.createCell().setText("Nombre: " + e.getNombre() + " Descripción: " + e.getDescripcion());
	        	}
	        }

        	if(caracteristicas != null) {
	        	for(CaracteristicasCandidatoVacante e : vacante.getCaracteristicas()) {
	        		XWPFTableRow row = caracteristicas.createRow();
	        		row.createCell().setText("Género: " + e.getGenero() + " Edad mínima: " + e.getMinEdad() + " Edad maáxima: " + e.getMaxEdad());
	        	}
	        }

			if(competencias != null) {
				for(CompetenciasVacante e : vacante.getCompetencias()) {
					XWPFTableRow row = competencias.createRow();
					row.createCell().setText("Nombre: " + e.getNombre() + " Descripción: " + e.getDescripcion());
				}
			}
			
			if(conocimientos != null) {
	        	for(ConocimientoVacante e : vacante.getConocimientos()) {
	        		XWPFTableRow row = conocimientos.createRow();
	        		row.createCell().setText("Nombre: " + e.getNombre() + " Descripción: " + e.getDescripcion());
	        	}
	        }
	        
	        String nombreReporte = "ReporteRYS_Vacante_" + vacante.getNombreVacante() + ".docx";
	        
	        response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document"); 
	        response.setHeader("Content-Disposition", "attachment; filename=" + nombreReporte);
	        document.write(response.getOutputStream());
	   
	        response.flushBuffer();

		} catch(IOException | InvalidFormatException e){
			throw new NirhoControllerException("Problemas al generar reporte");
		} catch (NirhoServiceException e) {
			throw new NirhoControllerException("Problemas al generar reporte");
		}
	}
	
	@RequestMapping(value = "/reporteEntrevista", method = RequestMethod.GET)
	@ResponseBody
	public void generarReporteEntrevista(@RequestParam(name="idVacante") long idVacante, HttpServletResponse response) throws NirhoControllerException{
		
		try {
			    
			ZipSecureFile.setMinInflateRatio(0);
			//XWPFDocument document = new XWPFDocument(OPCPackage.open("/opt/jboss-eap-7.1/standalone/deployments/reporteRYS.docx"));
			XWPFDocument document = new XWPFDocument(OPCPackage.open("C:\\Users\\pruebas\\elimina\\reporteRYSEntrevista.docx"));

			List<Candidato> candidatos = candidatoService.getAllByVacante(idVacante);
			
			for(Candidato candidato: candidatos) {

				for(EntrevistaVacante entrevista : entrevistaVacanteService.getByIdCandidato(candidato.getId())) {
				//	solicitanteVacanteService.
				}
				
				//for(ConcontratacionVacanteService.getByIdCandidato(candidato.getId());
			}

	        String nombreReporte = "ReporteRYS_Entrevista" + ".docx";
	        
	        response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document"); 
	        response.setHeader("Content-Disposition", "attachment; filename=" + nombreReporte);
	        document.write(response.getOutputStream());
	   
	        response.flushBuffer();

		} catch(IOException | InvalidFormatException e){
			throw new NirhoControllerException("Problemas al generar reporte");
		} catch (NirhoServiceException e) {
			throw new NirhoControllerException("Problemas al generar reporte");
		} 
	}
	
	
}

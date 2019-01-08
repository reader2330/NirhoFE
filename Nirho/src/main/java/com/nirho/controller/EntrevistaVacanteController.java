package com.nirho.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFChart;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.jboss.logging.Logger;
import org.json.JSONArray;
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

import com.nirho.dto.ProyectoPVCNivelDTO;
import com.nirho.exception.NirhoControllerException;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ActividadesPuestoVacante;
import com.nirho.model.EntrevistaVacante;
import com.nirho.model.CompetenciasVacante;
import com.nirho.model.ConocimientoVacante;
import com.nirho.model.ParticipantePVC;
import com.nirho.model.ProyectoPVCArea;
import com.nirho.model.ProyectoPVCConocimiento;
import com.nirho.model.ProyectoPVCEsfera;
import com.nirho.model.ProyectoPVCEspecialidad;
import com.nirho.model.ProyectoPVCNivel;
import com.nirho.model.Solicitante;
import com.nirho.model.SolicitanteVacante;
import com.nirho.model.Usuario;
import com.nirho.service.EntrevistaVacanteService;
import com.nirho.service.SolicitanteVacanteService;
import com.nirho.util.ReporteUtil;
import com.nirho.util.SessionUtil;



@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping( value = "/entrevistaVacante" )
public class EntrevistaVacanteController {
	
	public final static Logger logger = Logger.getLogger(EntrevistaVacanteController.class);
	 
	@Autowired
	EntrevistaVacanteService entrevistaVacanteService;
	@Autowired
	SolicitanteVacanteService solicitanteVacanteService;
	
	@GetMapping(value = "/todos")
	public List<EntrevistaVacante> todos() throws NirhoControllerException{
		try {
			return entrevistaVacanteService.getAll();
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de los entrevistaVacantes");
		}
	}
	
	@GetMapping(value = "/conteo")
	public String count() throws NirhoControllerException{
		try {
			JSONObject response = new JSONObject();
			response.accumulate("total", entrevistaVacanteService.getAll().size());
			return response.toString();
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de los entrevistaVacantes");
		} catch (JSONException e) {
			throw new NirhoControllerException("Problemas al obtener el registro de los entrevistaVacantes");
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public EntrevistaVacante get(@PathVariable("id") long id) throws NirhoControllerException{
		try {
			return entrevistaVacanteService.getOne(id);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de entrevistaVacante");
		}
	}
	
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String add(@Valid @RequestBody EntrevistaVacante entrevistaVacante) throws NirhoControllerException{
		try {
			entrevistaVacanteService.save(entrevistaVacante);
			JSONObject json = new JSONObject();
			json.accumulate("id", entrevistaVacante.getId());
			return json.toString();
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar entidad");
		} catch (JSONException e1) {
			throw new NirhoControllerException("Problemas al registrar entidad");
		} 
	}
	
	@RequestMapping(value = "/guardarTodos", method = RequestMethod.POST)
	public void add(@Valid @RequestBody List<EntrevistaVacante> l) throws NirhoControllerException{
		try {
			entrevistaVacanteService.save(l);
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar entrevistaVacante");
		} 
	}
	
	@RequestMapping(value = "/{id}/consultor/{idConsultor}", method = RequestMethod.POST)
	public void setConsultor(@PathVariable("id") long id, @PathVariable("idConsultor") long idConsultor) throws NirhoControllerException{
		try {
			EntrevistaVacante s = entrevistaVacanteService.getOne(id);
			if(s != null) {
				s.setIdConsultor(idConsultor);
				entrevistaVacanteService.save(s);
			}
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar solicitante");
		} 
	}
	
	@RequestMapping(value = "/{id}/consultor/comentario", method = RequestMethod.POST)
	public void setConsultorComen(@PathVariable("id") long id, @Valid @RequestBody String comentario) throws NirhoControllerException{
		try {
			EntrevistaVacante s = entrevistaVacanteService.getOne(id);
			if(s != null) {
				s.setObservacionesConsultor(comentario);
				entrevistaVacanteService.save(s);
			}
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar solicitante");
		} 
	}
	
	@RequestMapping(value = "/{id}/solicitante/{idSolicitante}", method = RequestMethod.POST)
	public void setSolicitante(@PathVariable("id") long id, @PathVariable("idSolicitante") long idSolicitante) throws NirhoControllerException{
		try {
			EntrevistaVacante s = entrevistaVacanteService.getOne(id);
			if(s != null) {
				s.setIdSolicitante(idSolicitante);
				entrevistaVacanteService.save(s);
			}
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar solicitante");
		} 
	}

	@RequestMapping(value = "/{id}/solicitante/comentario", method = RequestMethod.POST)
	public void setSolicitanteComen(@PathVariable("id") long id, @Valid @RequestBody String comentario) throws NirhoControllerException{
		try {
			EntrevistaVacante s = entrevistaVacanteService.getOne(id);
			if(s != null) {
				s.setObservacionesSolicitante(comentario);
				entrevistaVacanteService.save(s);
			}
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar solicitante");
		} 
	}
	
	@RequestMapping(value = "/{id}/candidato/{idCandidato}", method = RequestMethod.POST)
	public void setCandidato(@PathVariable("id") long id, @PathVariable("idCandidato") long idCandidato) throws NirhoControllerException{
		try {
			EntrevistaVacante s = entrevistaVacanteService.getOne(id);
			if(s != null) {
				s.setIdCandidato(idCandidato);
				entrevistaVacanteService.save(s);
			}
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar solicitante");
		} 
	}
	
	@RequestMapping(value = "/{id}/candidato/comentario", method = RequestMethod.POST)
	public void setCandidatoComen(@PathVariable("id") long id, @Valid @RequestBody String comentario) throws NirhoControllerException{
		try {
			EntrevistaVacante s = entrevistaVacanteService.getOne(id);
			if(s != null) {
				s.setObservacionesCliente(comentario);
				entrevistaVacanteService.save(s);
			}
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar solicitante");
		} 
	}

	
	@RequestMapping(value = "/reporte/participante", method = RequestMethod.GET)
	@ResponseBody
	public void genearReporteIndividual(@RequestParam(name="idEntrevistaVacante") long idEntrevistaVacante, HttpServletResponse response) throws NirhoControllerException{
		
		try {
			    
			ZipSecureFile.setMinInflateRatio(0);
			XWPFDocument document = new XWPFDocument(OPCPackage.open("/opt/jboss-eap-7.1/standalone/deployments/reporteRYS.docx"));
			//XWPFDocument document = new XWPFDocument(OPCPackage.open("C:\\Users\\pruebas\\elimina\\reporteRYS.docx"));

	        String nombreReporte = "ReporteRYS_" + ".docx";
	        
	        response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document"); 
	        response.setHeader("Content-Disposition", "attachment; filename=" + nombreReporte);
	        document.write(response.getOutputStream());
	   
	        response.flushBuffer();

		} catch(IOException | InvalidFormatException e){
			throw new NirhoControllerException("Problemas al generar reporte");
		} 
	}
	
	
}

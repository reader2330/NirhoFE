package com.nirho.controller;

import java.io.IOException;
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
import com.nirho.model.Candidato;
import com.nirho.model.CaracteristicasCandidatoCv;
import com.nirho.model.ParticipantePVC;
import com.nirho.model.ProyectoPVCArea;
import com.nirho.model.ProyectoPVCConocimiento;
import com.nirho.model.ProyectoPVCEsfera;
import com.nirho.model.ProyectoPVCEspecialidad;
import com.nirho.model.ProyectoPVCNivel;
import com.nirho.model.Usuario;
import com.nirho.service.CandidatoService;
import com.nirho.util.ReporteUtil;
import com.nirho.util.SessionUtil;



@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping( value = "/candidato" )
public class CandidatoController {
	
	public final static Logger logger = Logger.getLogger(CandidatoController.class);
	 
	@Autowired
	CandidatoService candidatoService;
	
	@GetMapping(value = "/todos")
	public List<Candidato> todos() throws NirhoControllerException{
		try {
			return candidatoService.getAll();
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de los candidatos");
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Candidato get(@PathVariable("id") long id) throws NirhoControllerException{
		try {
			return candidatoService.getOne(id);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de candidato");
		}
	}
	
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String add(@Valid @RequestBody Candidato candidato) throws NirhoControllerException{
		try {
			candidatoService.save(candidato);
			JSONObject json = new JSONObject();
			json.accumulate("id", candidato.getId());
			return json.toString();
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar entidad");
		} catch (JSONException e1) {
			throw new NirhoControllerException("Problemas al registrar entidad");
		} 
	}
	
	@RequestMapping(value = "/guardarTodos", method = RequestMethod.POST)
	public void add(@Valid @RequestBody List<Candidato> l) throws NirhoControllerException{
		try {
			candidatoService.save(l);
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar candidato");
		} 
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	public Candidato login(@RequestBody Candidato c, HttpServletRequest request) throws NirhoControllerException {
		try {
			
			Candidato candidato = candidatoService.getOneByUsername(c.getUsername());
			if(candidato != null) {
				if(c.getPassword().equals(candidato.getPassword())) {
					return candidato;
				} else {
					throw new NirhoControllerException("Password incorrecto");
				}
			}
			throw new NirhoControllerException("Usuario incorrecto");
		} catch (NirhoControllerException e) {
			logger.info("Exception [" + e.getMessage() +"]");
			throw new NirhoControllerException(e.getMessage());
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() +"]");
			throw new NirhoControllerException("Problemas al conectar con la BD");
		}
	}
	
	@RequestMapping(value = "/reporte/participante", method = RequestMethod.GET)
	@ResponseBody
	public void genearReporteIndividual(@RequestParam(name="idCandidato") long idCandidato, HttpServletResponse response) throws NirhoControllerException{
		
		try {
			    
			ZipSecureFile.setMinInflateRatio(0);
			XWPFDocument document = new XWPFDocument(OPCPackage.open("/opt/jboss-eap-7.1/standalone/deployments/reporteRYS.docx"));
			//XWPFDocument document = new XWPFDocument(OPCPackage.open("C:\\Users\\pruebas\\elimina\\reporteRYS.docx"));

	        Candidato candidato = candidatoService.getOne(idCandidato);

	        XWPFTable informacionGeneral =  ReporteUtil.getTablaPorTitulo(document, "Datos de contacto");
	        
	        if(informacionGeneral != null){
	        	
	            XWPFTableRow row3 = informacionGeneral.getRow(3);
	            row3.getCell(1).setText(candidato.getNombre());
	            row3.getCell(4).setText(candidato.getRfc());
	            
	            XWPFTableRow row4 = informacionGeneral.getRow(4);
	            row4.getCell(1).setText(candidato.getDireccion());
	            
	            XWPFTableRow row5 = informacionGeneral.getRow(5);
	            row5.getCell(1).setText(candidato.getNacionalidad());
	            row5.getCell(4).setText(candidato.getNacimiento());
	            
	            XWPFTableRow row6 = informacionGeneral.getRow(6);
	            row6.getCell(1).setText(candidato.getPerfil());
	            row6.getCell(4).setText(candidato.getDisponibilidad());
	            
	            XWPFTableRow row7 = informacionGeneral.getRow(7);
	            row7.getCell(1).setText(candidato.getEstado() + "");
	            row7.getCell(4).setText(candidato.getPerfil());
	            
	            XWPFTableRow row8 = informacionGeneral.getRow(8);
	            row8.getCell(1).setText(candidato.getSituacion());
	            
	            
	            
	            
	            CaracteristicasCandidatoCv caracteristicas = candidato.getCaracteristicasCandidatoCv();
	            
	            
	            XWPFTableRow row12 = informacionGeneral.getRow(12);
	            row12.getCell(1).setText(caracteristicas.getGenero());
	            row12.getCell(4).setText(caracteristicas.getEstadoCivil());
	            
	            XWPFTableRow row13 = informacionGeneral.getRow(13);
	            row13.getCell(1).setText(caracteristicas.getDispoViajar());
	            row13.getCell(4).setText(caracteristicas.getCambioResidencia());
	            
	            XWPFTableRow row14 = informacionGeneral.getRow(14);
	            row14.getCell(1).setText(caracteristicas.getNecesidadesEspeciales());
	            row14.getCell(4).setText(caracteristicas.getMinEdad() + " - " + caracteristicas.getMaxEdad());
	            
	            XWPFTableRow row15 = informacionGeneral.getRow(15);
	            row15.getCell(1).setText(caracteristicas.getCaractAdicionales());
	            
	            
	            
	            XWPFTableRow row19 = informacionGeneral.getRow(19);
	            row19.getCell(1).setText(caracteristicas.getGradoEstudios());
	            row19.getCell(4).setText(caracteristicas.getInstitucion());
	            
	            XWPFTableRow row20 = informacionGeneral.getRow(20);
	            row20.getCell(1).setText(caracteristicas.isTitulo() + "");
	            row20.getCell(4).setText(caracteristicas.getCarrera());
	            
	            XWPFTableRow row21 = informacionGeneral.getRow(21);
	            row21.getCell(1).setText(caracteristicas.getEspecialidad());
	            row21.getCell(4).setText(caracteristicas.getCertificaciones());
	            
	            XWPFTableRow row22 = informacionGeneral.getRow(22);
	            row22.getCell(1).setText(caracteristicas.getCursos());
	            row22.getCell(4).setText(caracteristicas.getOficios());
	            
	            XWPFTableRow row23 = informacionGeneral.getRow(23);
	            row23.getCell(1).setText(caracteristicas.getoCapacidades());
	            
	        }

	        String nombreReporte = "ReporteRYS_" + candidato.getNombre() + ".docx";
	        
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

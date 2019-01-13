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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
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
import com.nirho.model.Catalogo;
import com.nirho.model.CompetenciasVacante;
import com.nirho.model.ConocimientoCandidato;
import com.nirho.model.ConocimientoVacante;
import com.nirho.model.ContratacionVacante;
import com.nirho.model.EntrevistaVacante;
import com.nirho.model.ExperienciaCandidato;
import com.nirho.model.IdiomaCandidato;
import com.nirho.model.Solicitante;
import com.nirho.model.SolicitanteVacante;
import com.nirho.service.CandidatoService;
import com.nirho.service.CatalogoService;
import com.nirho.service.ContratacionVacanteService;
import com.nirho.service.EntrevistaVacanteService;
import com.nirho.service.SolicitanteService;
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
	@Autowired
	SolicitanteService solicitanteService;
	@Autowired
	CatalogoService catalogoService;
	
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
			XWPFDocument document = new XWPFDocument(OPCPackage.open("/opt/jboss-eap-7.1/standalone/deployments/reporteRYSVacante.docx"));
			//XWPFDocument document = new XWPFDocument(OPCPackage.open("C:\\Users\\Alfredo\\elimina\\reporteRYSVacante.docx"));

			SolicitanteVacante vacante = solicitanteVacanteService.getOne(idVacante);

	        XWPFTable informacionGeneral =  ReporteUtil.getTablaPorTitulo(document, "Datos de vacante");
	        XWPFTable actividades =  ReporteUtil.getTablaPorTitulo(document, "Actividades");
	        XWPFTable caracteristicas =  ReporteUtil.getTablaPorTitulo(document, "Caracteristicas");
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
	            row5.getCell(1).setText(getFromCatalogo(vacante.getGiro()));
	            row5.getCell(4).setText(getFromCatalogo(vacante.getMotivo()));
	            
	            XWPFTableRow row6 = informacionGeneral.getRow(6);
	            row6.getCell(1).setText(vacante.getNumVacantes() + "");
	            row6.getCell(4).setText(vacante.getPuestoCargo());
	            
	            XWPFTableRow row7 = informacionGeneral.getRow(7);
	            row7.getCell(1).setText(vacante.getPuestoReporta());
	        }
	        
	        if(actividades != null) {        	
	        	for(ActividadesPuestoVacante e : vacante.getActividades()) {
	        		XWPFTableRow row = actividades.createRow();
	        		row.getCell(0).setText("Nombre: " + e.getNombre() + " Descripción: " + e.getDescripcion());
	        	}
	        }

        	if(caracteristicas != null) {
	        	for(CaracteristicasCandidatoVacante e : vacante.getCaracteristicas()) {
	        		XWPFTableRow row = caracteristicas.createRow();
	        		row.getCell(0).setText("Género: " + e.getGenero() + " Edad mínima: " + e.getMinEdad() + " Edad máxima: " + e.getMaxEdad());
	        	}
	        }

			if(competencias != null) {
				for(CompetenciasVacante e : vacante.getCompetencias()) {
					XWPFTableRow row = competencias.createRow();
					row.getCell(0).setText("Nombre: " + e.getNombre() + " Descripción: " + e.getDescripcion());
				}
			}
			
			if(conocimientos != null) {
	        	for(ConocimientoVacante e : vacante.getConocimientos()) {
	        		XWPFTableRow row = conocimientos.createRow();
	        		row.getCell(0).setText("Nombre: " + e.getNombre() + " Descripción: " + e.getDescripcion());
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
			XWPFDocument document = new XWPFDocument(OPCPackage.open("/opt/jboss-eap-7.1/standalone/deployments/reporteRYSEntrevista.docx"));
			//XWPFDocument document = new XWPFDocument(OPCPackage.open("C:\\Users\\Alfredo\\elimina\\reporteRYSEntrevista.docx"));
				
			Solicitante solicitante = null;
			SolicitanteVacante vacante = null;
			
			for(Solicitante s:  solicitanteService.getAll()) {
				for(SolicitanteVacante v: s.getVacantes()) {
					if(v.getId() == idVacante) {
						vacante = v;
						solicitante = s;
					}
				}
			}
			
			
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
	            row5.getCell(1).setText(getFromCatalogo(vacante.getGiro()));
	            row5.getCell(4).setText(getFromCatalogo(vacante.getMotivo()));
	            
	            XWPFTableRow row6 = informacionGeneral.getRow(6);
	            row6.getCell(1).setText(vacante.getNumVacantes() + "");
	            row6.getCell(4).setText(vacante.getPuestoCargo());
	            
	            XWPFTableRow row7 = informacionGeneral.getRow(7);
	            row7.getCell(1).setText(vacante.getPuestoReporta());
	        }
	        
	        if(actividades != null) {        	
	        	for(ActividadesPuestoVacante e : vacante.getActividades()) {
	        		XWPFTableRow row = actividades.createRow();
	        		row.getCell(0).setText("Nombre: " + e.getNombre() + " Descripción: " + e.getDescripcion());
	        	}
	        }

        	if(caracteristicas != null) {
	        	for(CaracteristicasCandidatoVacante e : vacante.getCaracteristicas()) {
	        		XWPFTableRow row = caracteristicas.createRow();
	        		row.getCell(0).setText("Género: " + e.getGenero() + " Edad mínima: " + e.getMinEdad() + " Edad maáxima: " + e.getMaxEdad());
	        	}
	        }

			if(competencias != null) {
				for(CompetenciasVacante e : vacante.getCompetencias()) {
					XWPFTableRow row = competencias.createRow();
					row.getCell(0).setText("Nombre: " + e.getNombre() + " Descripción: " + e.getDescripcion());
				}
			}
			
			if(conocimientos != null) {
	        	for(ConocimientoVacante e : vacante.getConocimientos()) {
	        		XWPFTableRow row = conocimientos.createRow();
	        		row.getCell(0).setText("Nombre: " + e.getNombre() + " Descripción: " + e.getDescripcion());
	        	}
	        }
			
			
			XWPFTable informacionSolicitante =  ReporteUtil.getTablaPorTitulo(document, "Datos de solicitante");
			
			if(informacionGeneral != null){
	        	
	            XWPFTableRow row3 = informacionSolicitante.getRow(3);
	            row3.getCell(1).setText(solicitante.getNombre());
	            row3.getCell(4).setText(solicitante.getRfc());
	            
	            XWPFTableRow row4 = informacionSolicitante.getRow(4);
	            row4.getCell(1).setText(getFromCatalogo(solicitante.getGiro()));
	            row4.getCell(4).setText(getFromCatalogo(solicitante.getPais()));
	            
	            XWPFTableRow row5 = informacionSolicitante.getRow(5);
	            row5.getCell(1).setText(solicitante.getDireccion());
	            
	        }
			
			
			//document.getPosOfTable(t)
			//document.removeBodyElement(pos)
			
			XWPFTable tablaEntrevista =  ReporteUtil.getTablaPorTitulo(document, "Entrevista");
			XWPFTable tablaContrato =  ReporteUtil.getTablaPorTitulo(document, "Contrato");
			XWPFTable tablaCandidato =  ReporteUtil.getTablaPorTitulo(document, "Candidato contratado");
			XWPFTable tablaCaracteristicas =  ReporteUtil.getTablaPorTitulo(document, "Candidato contratado cv");
			XWPFTable tablaConocimientos =  ReporteUtil.getTablaPorTitulo(document, "Conocimientos contratado");
			XWPFTable tablaIdiomas =  ReporteUtil.getTablaPorTitulo(document, "Idiomas contratado");
			XWPFTable tablaPuestos =  ReporteUtil.getTablaPorTitulo(document, "Puestos contratado");

			int numEntrevista = 1;
			
			for(EntrevistaVacante entrevista : entrevistaVacanteService.getByIdVacante(idVacante)) {
				
				CTTbl ctTbl = CTTbl.Factory.newInstance();
                ctTbl.set(tablaEntrevista.getCTTbl());
                XWPFTable tablaEntrevistaAux = new XWPFTable(ctTbl, document); 
                
                XWPFTableRow row3 = tablaEntrevista.getRow(3);
	            row3.getCell(1).setText(entrevista.getTipoEntrevista());
	            row3.getCell(4).setText(new SimpleDateFormat("yyyy-MM-dd").format(entrevista.getFechaEntrevista()));
	            
	            XWPFTableRow row4 = tablaEntrevista.getRow(4);
	            row4.getCell(1).setText(entrevista.getHoraInicial() + "");
	            row4.getCell(4).setText(entrevista.getHoraFinal() + "");
	            
	            XWPFTableRow row5 = tablaEntrevista.getRow(5);
	            row5.getCell(1).setText(entrevista.getDireccion());
                
	            XWPFTableRow row6 = tablaEntrevista.getRow(6);
	            row6.getCell(1).setText(entrevista.getEncargadoEntrevista());
	            row6.getCell(4).setText(entrevista.getTitulo());
                
	            XWPFTableRow row9 = tablaEntrevista.getRow(9);
	            row9.getCell(0).setText(entrevista.getObservacionesCliente());
	            
	            XWPFTableRow row11 = tablaEntrevista.getRow(11);
	            row11.getCell(0).setText(entrevista.getObservacionesConsultor());
	            
	            XWPFTableRow row13 = tablaEntrevista.getRow(13);
	            row13.getCell(0).setText(entrevista.getObservacionesSolicitante());
	            
                document.createTable();          
            //    document.setTable(document.getPosOfTable(tablaEntrevista) + numEntrevista, tablaEntrevistaAux);
				
				
				Candidato candidato = candidatoService.getOne(entrevista.getIdCandidato());
				
				for(ContratacionVacante contratacion : contratacionVacanteService.getByIdCandidato(candidato.getId())){
					
					
					XWPFTableRow row3Contrato = tablaContrato.getRow(3);
		            row3Contrato.getCell(1).setText(contratacion.getJornada());
		            row3Contrato.getCell(4).setText(contratacion.getTipoContrato());
		            
		            XWPFTableRow row4Contrato = tablaContrato.getRow(4);
		            row4Contrato.getCell(1).setText(contratacion.getSueldo());
		            row4Contrato.getCell(4).setText(contratacion.isAceptado() ? "Sí" : "No");
		            
		            XWPFTableRow row5Contrato = tablaContrato.getRow(5);
		            row5Contrato.getCell(1).setText(contratacion.getPrestaciones());


		            XWPFTableRow row3Candidato = tablaCandidato.getRow(3);
		            row3Candidato.getCell(1).setText(candidato.getNombre());
		            row3Candidato.getCell(4).setText(candidato.getRfc());
		            
		            XWPFTableRow row4Candidato = tablaCandidato.getRow(4);
		            row4Candidato.getCell(1).setText(candidato.getNacionalidad());
		            row4Candidato.getCell(4).setText(candidato.getNacimiento() + "");
		            
		            XWPFTableRow row5Candidato = tablaCandidato.getRow(5);
		            row5Candidato.getCell(1).setText(candidato.getPerfil());
		            row5Candidato.getCell(4).setText(candidato.getSituacion() == null ? "" : candidato.getSituacion());
		            
		            XWPFTableRow row6Candidato = tablaCandidato.getRow(6);
		            row6Candidato.getCell(1).setText(candidato.getDireccion());
		            
		            
		            if(candidato.getCaracteristicasCandidatoCv() != null) {
		            	
			            XWPFTableRow row3CandidatoCV = tablaCaracteristicas.getRow(3);
			            row3CandidatoCV.getCell(1).setText(candidato.getCaracteristicasCandidatoCv().getGenero());
			            row3CandidatoCV.getCell(4).setText(candidato.getCaracteristicasCandidatoCv().getCarrera());
			            
			            XWPFTableRow row4CandidatoCV = tablaCaracteristicas.getRow(4);
			            row4CandidatoCV.getCell(1).setText(candidato.getCaracteristicasCandidatoCv().getGradoEstudios());
			            row4CandidatoCV.getCell(4).setText(candidato.getCaracteristicasCandidatoCv().getInstitucion());
			            
			            XWPFTableRow row5CandidatoCV = tablaCaracteristicas.getRow(5);
			            row5CandidatoCV.getCell(1).setText(candidato.getCaracteristicasCandidatoCv().getCursos());
			            row5CandidatoCV.getCell(4).setText(candidato.getCaracteristicasCandidatoCv().getEspecialidad());
			            
			            XWPFTableRow row6CandidatoCV = tablaCaracteristicas.getRow(6);
			            row6CandidatoCV.getCell(1).setText(candidato.getCaracteristicasCandidatoCv().getEstadoCivil());
			            row6CandidatoCV.getCell(1).setText(candidato.getCaracteristicasCandidatoCv().getDispoViajar());
			            
			            XWPFTableRow row7CandidatoCV = tablaCaracteristicas.getRow(6);
			            row7CandidatoCV.getCell(1).setText(candidato.getCaracteristicasCandidatoCv().getCaractAdicionales());
			            
		            }

					if(tablaIdiomas != null) {
						for(IdiomaCandidato e : candidato.getIdiomas()) {
							XWPFTableRow row = tablaIdiomas.createRow();
							row.getCell(0).setText("Nombre: " + e.getNombre() + " Nivel: " + e.getNivel());
						}
					}
					
					if(tablaConocimientos != null) {
			        	for(ConocimientoCandidato e : candidato.getConocimentos()) {
			        		XWPFTableRow row = tablaConocimientos.createRow();
			        		row.getCell(0).setText("Nombre: " + e.getNombre() + " Descripción: " + e.getDescripcion());
			        	}
			        }
		            
					if(tablaPuestos != null) {
						for(ExperienciaCandidato e : candidato.getPuestos()) {
							XWPFTableRow row = tablaPuestos.createRow();
							row.getCell(0).setText("Nombre: " + e.getArea() + " Descripción: " + e.getPuesto());
						}
					}
		            
					break;
				}
				
				numEntrevista++;
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
	
	
	private String getFromCatalogo(long id) {
		Catalogo catalogo = null;
		try {
			catalogo = catalogoService.getOne(id);
			if(catalogo != null) {
				return catalogo.getDescripcionCatalogo();
			}
		} catch (NirhoServiceException e) {
			
		}
		return "";
	}
	
}

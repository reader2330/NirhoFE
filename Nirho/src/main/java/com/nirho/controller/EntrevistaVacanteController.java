package com.nirho.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
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

import com.nirho.exception.NirhoControllerException;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.Candidato;
import com.nirho.model.EntrevistaVacante;
import com.nirho.model.Solicitante;
import com.nirho.model.SolicitanteVacante;
import com.nirho.model.Usuario;
import com.nirho.service.CandidatoService;
import com.nirho.service.EmailService;
import com.nirho.service.EntrevistaVacanteService;
import com.nirho.service.SolicitanteService;
import com.nirho.service.SolicitanteVacanteService;
import com.nirho.service.UsuarioService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping( value = "/entrevistaVacante" )
public class EntrevistaVacanteController {
	
	public final static Logger logger = Logger.getLogger(EntrevistaVacanteController.class);
	 
	@Autowired
	EntrevistaVacanteService entrevistaVacanteService;
	@Autowired
	SolicitanteVacanteService solicitanteVacanteService;
	@Autowired
	EmailService emailService;
	@Autowired
	UsuarioService usuarioService;
	@Autowired
	SolicitanteService solicitanteService;
	@Autowired
	CandidatoService candidatoService;
	
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
	
	@GetMapping(value = "/porCandidato")
	public List<EntrevistaVacante> porCandidato(@RequestParam(name="idCandidato") long idCandidato) throws NirhoControllerException{
		try {
			return entrevistaVacanteService.getByIdCandidato(idCandidato);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de los solicitantes");
		}
	}
	
	@GetMapping(value = "/porConsultor")
	public List<EntrevistaVacante> porConsultor(@RequestParam(name="idConsultor") long idConsultor) throws NirhoControllerException{
		try {
			return entrevistaVacanteService.getByIdConsultor(idConsultor);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de los solicitantes");
		}
	}
	
	@GetMapping(value = "/porSolicitante")
	public List<EntrevistaVacante> porSolicitante(@RequestParam(name="idSolicitante") long idSolicitante) throws NirhoControllerException{
		try {
			return entrevistaVacanteService.getByIdSolicitante(idSolicitante);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de los solicitantes");
		}
	}
	
	@GetMapping(value = "/porUsernameCandidato")
	public List<EntrevistaVacante> porUsernameCandidato(@RequestParam(name="username") String username) throws NirhoControllerException{
		try {
			Candidato c = candidatoService.getOneByUsername(username);
			if(c != null) {
				return entrevistaVacanteService.getByIdCandidato(c.getId());
			}
			return null;
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de los solicitantes");
		}
	}
	
	@GetMapping(value = "/porUsernameConsultor")
	public List<EntrevistaVacante> porUsernameConsultor(@RequestParam(name="username") String username) throws NirhoControllerException{
		try {
			Usuario u = usuarioService.obtenerUsuario(username);
			if(u != null) {
				return entrevistaVacanteService.getByIdConsultor(u.getId()); 
			}
			return null;
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de los solicitantes");
		}
	}
	
	@GetMapping(value = "/porUsernameSolicitante")
	public List<EntrevistaVacante> porUsernameSolicitante(@RequestParam(name="username") String username) throws NirhoControllerException{
		try {
			Solicitante s = solicitanteService.getByUsername(username);
			if(s != null) {
				return entrevistaVacanteService.getByIdSolicitante(s.getId());
			} 
			return null;
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de los solicitantes");
		}
	}
	
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String add(@Valid @RequestBody EntrevistaVacante entrevistaVacante) throws NirhoControllerException{
		try {
			
			try {
				SolicitanteVacante vacante = solicitanteVacanteService.getOne(entrevistaVacante.getIdVacante());
				vacante.setStatus(3);
				solicitanteVacanteService.editar(vacante);
			} catch(Exception e) {}
			
			entrevistaVacanteService.save(entrevistaVacante);
			
			Candidato candidato = candidatoService.getOne(entrevistaVacante.getIdCandidato());
			if( candidato != null) {
				enviarEntrevista(candidato.getEmail(), entrevistaVacante);
			}
			
			Usuario consultor = null;
			List<Usuario> consultores = usuarioService.obtenerConsultores();
			for(Usuario usuario: consultores) {
				if(usuario.getId() == entrevistaVacante.getIdConsultor()) {
					consultor = usuario;
				}
			}
			
			if( consultor != null) {
				enviarEntrevista(consultor.getEmail(), entrevistaVacante);
			}
			
			
			Solicitante solicitante = solicitanteService.getOne(entrevistaVacante.getIdSolicitante());
			if( solicitante != null) {
				enviarEntrevista(solicitante.getEmail(), entrevistaVacante);
			}
			
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

	private void enviarEntrevista(String to, EntrevistaVacante entrevista) throws NirhoServiceException {
		String emailBody = "";
		emailBody += "<p>Título: " + entrevista.getTitulo() + "</p><br/>";
		emailBody += "<p>Fecha: " + new SimpleDateFormat("yyyy-MM-dd").format(entrevista.getFechaEntrevista()) + "</p>";
		emailBody += "<p>Hora inicial: " + entrevista.getHoraInicial() + "</p>";
		emailBody += "<p>Hora final: " + entrevista.getHoraFinal() + "</p>";
		emailBody += "<p>Dirección: " + entrevista.getDireccion() + "</p>";
		emailBody += "<p>Encargado de entrevista: " + entrevista.getEncargadoEntrevista() + "</p>";
		emailService.sendEmail(to, "Entrevista", emailBody);
	}
	
}

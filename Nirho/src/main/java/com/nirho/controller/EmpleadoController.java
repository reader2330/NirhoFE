package com.nirho.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.codec.binary.Base64;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
 
import com.nirho.exception.NirhoControllerException;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.Empleado;
import com.nirho.service.EmpleadoService;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping( value = "/empleado" )
public class EmpleadoController {

	public final static Logger logger = Logger.getLogger(EmpleadoController.class);
	private final String basePath = System.getProperty("user.home") + "/documentos/empleados/";
	
	@Autowired
	EmpleadoService empleadoService;
	
	@RequestMapping(value = "/todos", method = RequestMethod.GET)
	public List<Empleado> todas() throws NirhoControllerException{
		try {
			return empleadoService.listEmpleados();			
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener los registros de empleado");
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Empleado get(@PathVariable("id") long id) throws NirhoControllerException{
		try {
			Empleado e = empleadoService.getEmpleadoById(id);
			return e;
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de empleado");
		}
	}
	
	@RequestMapping(value = "/agregar", method = RequestMethod.POST)
	public void add(@Valid @RequestBody Empleado e) throws NirhoControllerException{
		FileOutputStream osf = null;
		try {
			
			if(e.getDocumentoComprobanteDomicilio() != null && !e.getDocumentoComprobanteDomicilio().isEmpty()) {
				String[] infoDoc = e.getDocumentoComprobanteDomicilio().split(",");
				String path = basePath + e.getCurp() + "_comprobanteDomicilio" + "." +  infoDoc[0];
				File file = new File(path);
				if(!file.exists()) {
					file.createNewFile();
				} 
				osf = new FileOutputStream(file);
				osf.write(new Base64().decode(infoDoc[1]));
				osf.flush();
				e.setDocumentoComprobanteDomicilio(path);
			}
			
			if(e.getDocumentoCurp() != null && !e.getDocumentoCurp().isEmpty()) {
				String[] infoDoc = e.getDocumentoCurp().split(",");
				String path = basePath + e.getCurp() + "_curp" + "." +  infoDoc[0];
				File file = new File(path);
				if(!file.exists()) {
					file.createNewFile();
				} 
				osf = new FileOutputStream(file);
				osf.write(new Base64().decode(infoDoc[1]));
				osf.flush();
				e.setDocumentoCurp(path);
			}
			
			if(e.getDocumentoCv() != null && !e.getDocumentoCv().isEmpty()) {
				String[] infoDoc = e.getDocumentoCv().split(",");
				String path = basePath + e.getCurp() + "_cv" + "." +  infoDoc[0];
				File file = new File(path);
				if(!file.exists()) {
					file.createNewFile();
				} 
				osf = new FileOutputStream(file);
				osf.write(new Base64().decode(infoDoc[1]));
				osf.flush();
				e.setDocumentoCv(path);
			}
			
			if(e.getDocumentoIne() != null && !e.getDocumentoIne().isEmpty()) {
				String[] infoDoc = e.getDocumentoIne().split(",");
				String path = basePath + e.getCurp() + "_ine" + "." +  infoDoc[0];
				File file = new File(path);
				if(!file.exists()) {
					file.createNewFile();
				} 
				osf = new FileOutputStream(file);
				osf.write(new Base64().decode(infoDoc[1]));
				osf.flush();
				e.setDocumentoIne(path);
			}
			
			empleadoService.addEmpleado(e);
			
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar empleado");
		} catch(Exception exe) {
			throw new NirhoControllerException("Problemas al registrar empleado");
		} finally {
			if(osf != null) {
				try {
					osf.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	@RequestMapping(value = "/editar", method = RequestMethod.POST)
    public void edit(@Valid @RequestBody Empleado e)  throws NirhoControllerException{
		FileOutputStream osf = null;
		
		try {
			
			if(e.getDocumentoComprobanteDomicilio() != null && !e.getDocumentoComprobanteDomicilio().isEmpty()) {
				String[] infoDoc = e.getDocumentoComprobanteDomicilio().split(",");
				String path = basePath + e.getCurp() + "_comprobanteDomicilio" + "." +  infoDoc[0];
				File file = new File(path);
				if(!file.exists()) {
					file.createNewFile();
				} 
				osf = new FileOutputStream(file);
				osf.write(new Base64().decode(infoDoc[1]));
				osf.flush();
				e.setDocumentoComprobanteDomicilio(path);
			}
			
			if(e.getDocumentoCurp() != null && !e.getDocumentoCurp().isEmpty()) {
				String[] infoDoc = e.getDocumentoCurp().split(",");
				String path = basePath + e.getCurp() + "_curp" + "." +  infoDoc[0];
				File file = new File(path);
				if(!file.exists()) {
					file.createNewFile();
				} 
				osf = new FileOutputStream(file);
				osf.write(new Base64().decode(infoDoc[1]));
				osf.flush();
				e.setDocumentoCurp(path);
			}
			
			if(e.getDocumentoCv() != null && !e.getDocumentoCv().isEmpty()) {
				String[] infoDoc = e.getDocumentoCv().split(",");
				String path = basePath + e.getCurp() + "_cv" + "." +  infoDoc[0];
				File file = new File(path);
				if(!file.exists()) {
					file.createNewFile();
				} 
				osf = new FileOutputStream(file);
				osf.write(new Base64().decode(infoDoc[1]));
				osf.flush();
				e.setDocumentoCv(path);
			}
			
			if(e.getDocumentoIne() != null && !e.getDocumentoIne().isEmpty()) {
				String[] infoDoc = e.getDocumentoIne().split(",");
				String path = basePath + e.getCurp() + "_ine" + "." +  infoDoc[0];
				File file = new File(path);
				if(!file.exists()) {
					file.createNewFile();
				} 
				osf = new FileOutputStream(file);
				osf.write(new Base64().decode(infoDoc[1]));
				osf.flush();
				e.setDocumentoIne(path);
			}
	
			empleadoService.updateEmpleado(e);
			
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al actualizar registro de empleado");
		} catch(Exception exe) {
			throw new NirhoControllerException("Problemas al actualizar registro de empleado");
		} finally {
			if(osf != null) {
				try {
					osf.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	@RequestMapping(value = "/eliminar/{id}", method = RequestMethod.DELETE)
    public void removePerson(@PathVariable("id") long id) throws NirhoControllerException{
		try {
			
			Empleado e = empleadoService.getEmpleadoById(id);
			
			if(e != null) {
			
				if(e.getDocumentoComprobanteDomicilio() != null && !e.getDocumentoComprobanteDomicilio().isEmpty()) {
					File file = new File(e.getDocumentoComprobanteDomicilio());
					Files.deleteIfExists(file.toPath());
				}
				
				if(e.getDocumentoCurp() != null && !e.getDocumentoCurp().isEmpty()) {
					File file = new File(e.getDocumentoComprobanteDomicilio());
					Files.deleteIfExists(file.toPath());
				}
				
				if(e.getDocumentoCv() != null && !e.getDocumentoCv().isEmpty()) {
					File file = new File(e.getDocumentoCurp());
					Files.deleteIfExists(file.toPath());
				}
				
				if(e.getDocumentoIne() != null && !e.getDocumentoIne().isEmpty()) {
					File file = new File(e.getDocumentoIne());
					Files.deleteIfExists(file.toPath());
				}
			}
				
			empleadoService.removeEmpleado(empleadoService.getEmpleadoById(id));
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al eliminar el registro de empleado");
		} catch(Exception e) {
			throw new NirhoControllerException("Problemas al eliminar el registro de empleado");
		}
	}
	
}

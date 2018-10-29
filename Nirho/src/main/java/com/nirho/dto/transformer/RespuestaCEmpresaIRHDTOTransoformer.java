package com.nirho.dto.transformer;

import java.util.ArrayList;
import java.util.List;

import com.nirho.dto.PreguntaRespuestaCEmpresaIRHDTO;
import com.nirho.model.PreguntaCuestionarioEmpresa;
import com.nirho.model.PreguntaTema;
import com.nirho.model.RespuestaPreguntaIRH;

public class RespuestaCEmpresaIRHDTOTransoformer {

	/**
	 * Metodo para transformar una entity a DTO
	 * @param pregunta
	 * @return
	 */
	public static PreguntaRespuestaCEmpresaIRHDTO entityToDTO(PreguntaTema pregunta, RespuestaPreguntaIRH respuesta){
		PreguntaRespuestaCEmpresaIRHDTO preguntaRespuesta = new PreguntaRespuestaCEmpresaIRHDTO();
		preguntaRespuesta.setPregunta(pregunta);
		if(respuesta != null){
			eliminaDatos(respuesta);
			preguntaRespuesta.setRespuesta(respuesta);			
		}
		return preguntaRespuesta;
	}
	
	/**
	 * Metodo para devolver la list de DTOs convertidos
	 * @param preguntasCuestionarioEmpresas
	 * @return
	 */
	public static List<PreguntaRespuestaCEmpresaIRHDTO> entitiesToDTOS(List<PreguntaCuestionarioEmpresa> preguntasCuestionarioEmpresas, List<RespuestaPreguntaIRH> respuestas){
		List<PreguntaRespuestaCEmpresaIRHDTO> respuestaCEmpresaIRHDTOs = new ArrayList<PreguntaRespuestaCEmpresaIRHDTO>();
		boolean tieneRespuesta = false;
		for (PreguntaCuestionarioEmpresa pregunta : preguntasCuestionarioEmpresas) {
			tieneRespuesta = false;
			for (RespuestaPreguntaIRH respuesta : respuestas) {
				if(respuesta.getIdPregunta().equals(pregunta.getPreguntaTema().getIdPregunta())){
					tieneRespuesta =true;
					respuestaCEmpresaIRHDTOs.add(entityToDTO(pregunta.getPreguntaTema(), respuesta));
				}
			}
			if (!tieneRespuesta) {
				respuestaCEmpresaIRHDTOs.add(entityToDTO(pregunta.getPreguntaTema(), null));
			}
		}
		return respuestaCEmpresaIRHDTOs;
	}
	
	private static void eliminaDatos(RespuestaPreguntaIRH respuesta){
		respuesta.setCuestionarioEmpresa(null);
	}
}

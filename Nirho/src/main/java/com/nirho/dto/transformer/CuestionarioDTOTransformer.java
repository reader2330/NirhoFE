package com.nirho.dto.transformer;

import java.util.ArrayList;
import java.util.List;

import com.nirho.dto.CuestionarioEmpresaDTO;
import com.nirho.model.CuestionarioEmpresa;
import com.nirho.model.TemaCuestionario;

public class CuestionarioDTOTransformer {
	
	/**
	 * Metodo para transformar cuestionarios para una empresa
	 * @param temaCuestionario
	 * @param cuestionarioEmpresa
	 * @return
	 */
	public static CuestionarioEmpresaDTO entityToDTO(TemaCuestionario temaCuestionario, CuestionarioEmpresa cuestionarioEmpresa){
		CuestionarioEmpresaDTO cuestionarioEmpresaDTO = new CuestionarioEmpresaDTO();
		cuestionarioEmpresaDTO.setIdCuestionarioEmpresa(cuestionarioEmpresa == null ? null: cuestionarioEmpresa.getIdCuestionarioEmpresa());
		cuestionarioEmpresaDTO.setIdTema(temaCuestionario.getIdTema());
		cuestionarioEmpresaDTO.setNombre(temaCuestionario.getNombre());
		cuestionarioEmpresaDTO.setActivo(cuestionarioEmpresa == null ? false: true);
		return cuestionarioEmpresaDTO;
	}
	
	/**
	 * Metodo para transformar una lista de cuestionarios (activos y no activos) de una empresa
	 * @param temasCuestionario
	 * @param cuestionariosEmpresa
	 * @return
	 */
	public static List<CuestionarioEmpresaDTO> entitiesToDTOs(List<TemaCuestionario> temasCuestionario, List<CuestionarioEmpresa> cuestionariosEmpresa){
		List<CuestionarioEmpresaDTO> cuestionariosEmpresaDTO = new ArrayList<CuestionarioEmpresaDTO>();
		boolean tieneCuestionario = false;
		for (TemaCuestionario temaCuestionario : temasCuestionario) {
			tieneCuestionario = false;
			for (CuestionarioEmpresa cuestionarioEmpresa : cuestionariosEmpresa) {
				if(cuestionarioEmpresa.getTemaCuestionario().getIdTema().equals(temaCuestionario.getIdTema())){
					tieneCuestionario = true;
					cuestionariosEmpresaDTO.add(entityToDTO(temaCuestionario, cuestionarioEmpresa));
					break;
				}
			}
			if (!tieneCuestionario) {
				cuestionariosEmpresaDTO.add(entityToDTO(temaCuestionario, null));
			}
		}
		return cuestionariosEmpresaDTO;
	}
}

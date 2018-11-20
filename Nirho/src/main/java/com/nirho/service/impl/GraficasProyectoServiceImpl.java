package com.nirho.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.dto.GraficaAreaOrgDTO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.service.GraficaAreaOrgService;
import com.nirho.service.GraficasProyectoService;

@Service
public class GraficasProyectoServiceImpl implements GraficasProyectoService {
	public final static Logger logger = Logger.getLogger(GraficasProyectoServiceImpl.class);
	
	@Autowired
	private GraficaAreaOrgService graficasAreaService;

	@Override
	public List<GraficaAreaOrgDTO> obtenerGraficasProyecto(Integer idProyecto) throws NirhoServiceException {
		List<GraficaAreaOrgDTO> graficas = new ArrayList<>();
		try {
			graficas.add(graficasAreaService.obtenerResultadosCorporativo(idProyecto));
			graficas.add(graficasAreaService.obtenerResultadosProduccion(idProyecto));
			graficas.add(graficasAreaService.obtenerResultadosVentas(idProyecto));
			graficas.add(graficasAreaService.obtenerResultadosAdministracion(idProyecto));
			graficas.add(graficasAreaService.obtenerResultadosControlDeCalidad(idProyecto));
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD las graficas del proyecto, causa [" + e.getMessage()+ "]");
		}		
		return graficas;
	}
}

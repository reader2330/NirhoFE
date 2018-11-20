package com.nirho.service;

import com.nirho.dto.GraficaAreaOrgDTO;
import com.nirho.exception.NirhoServiceException;

public interface GraficaAreaOrgService {
	GraficaAreaOrgDTO obtenerResultadosCorporativo(int idProyecto) throws NirhoServiceException;;
	GraficaAreaOrgDTO obtenerResultadosProduccion(int idProyecto) throws NirhoServiceException;;
	GraficaAreaOrgDTO obtenerResultadosVentas(int idProyecto) throws NirhoServiceException;;
	GraficaAreaOrgDTO obtenerResultadosAdministracion(int idProyecto) throws NirhoServiceException;;
	GraficaAreaOrgDTO obtenerResultadosControlDeCalidad(int idProyecto) throws NirhoServiceException;;
}

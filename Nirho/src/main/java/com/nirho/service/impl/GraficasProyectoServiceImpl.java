package com.nirho.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.constant.AreaOrgConstants;
import com.nirho.constant.ProyectoConstants;
import com.nirho.dao.GraficaProyectoDAO;
import com.nirho.dto.GraficaAreaOrgDTO;
import com.nirho.dto.GraficaProyectoDTO;
import com.nirho.dto.GraficaRespPregDTO;
import com.nirho.dto.GraficaResultadoDTO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.EstatusProyecto;
import com.nirho.model.GraficaProyecto;
import com.nirho.model.GraficaProyectoPK;
import com.nirho.model.Proyecto;
import com.nirho.service.GraficaAreaOrgService;
import com.nirho.service.GraficasProyectoService;
import com.nirho.service.ProyectoService;

@Service
public class GraficasProyectoServiceImpl implements GraficasProyectoService {
	public final static Logger logger = Logger.getLogger(GraficasProyectoServiceImpl.class);
	
	@Autowired
	private ProyectoService proyectoService;
	@Autowired
	private GraficaAreaOrgService graficasAreaService;
	@Autowired
	private GraficaProyectoDAO graficaDAO;
	
	@Override
	public GraficaProyectoDTO obtenerGraficasProyecto(Integer idProyecto) throws NirhoServiceException {
		GraficaProyectoDTO dto = new GraficaProyectoDTO();
		Proyecto proyecto = null;
		List<List<GraficaProyecto>> graficas = new ArrayList<>();
		try {
			proyecto = proyectoService.obtenerProyectoPorId(idProyecto);
			dto.setProyecto(proyecto);
			graficas.add(obtenerGraficasProyectoPorAreaOrg(idProyecto, AreaOrgConstants.CORPORATIVO));
			graficas.add(obtenerGraficasProyectoPorAreaOrg(idProyecto, AreaOrgConstants.PRODUCCION));
			graficas.add(obtenerGraficasProyectoPorAreaOrg(idProyecto, AreaOrgConstants.VENTAS));
			graficas.add(obtenerGraficasProyectoPorAreaOrg(idProyecto, AreaOrgConstants.ADMINISTRACION));
			graficas.add(obtenerGraficasProyectoPorAreaOrg(idProyecto, AreaOrgConstants.CONTROL_DE_CALIDAD));
			dto.setDatos(graficas);
		} catch(Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD las graficas del proyecto, causa [" + e.getMessage()+ "]");
		}
		return dto;
	}
	
	@Override
	public GraficaRespPregDTO obtenerGraficasRespuestasPreguntas(Integer idProyecto) throws NirhoServiceException {
		GraficaRespPregDTO dto = new GraficaRespPregDTO();
		Proyecto proyecto = null;
		List<GraficaAreaOrgDTO> graficas = new ArrayList<>();
		try {
			proyecto = proyectoService.obtenerProyectoPorId(idProyecto);
			graficas.add(graficasAreaService.obtenerResultadosCorporativo(idProyecto));
			graficas.add(graficasAreaService.obtenerResultadosProduccion(idProyecto));
			graficas.add(graficasAreaService.obtenerResultadosVentas(idProyecto));
			graficas.add(graficasAreaService.obtenerResultadosAdministracion(idProyecto));
			graficas.add(graficasAreaService.obtenerResultadosControlDeCalidad(idProyecto));
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD las graficas del proyecto, causa [" + e.getMessage()+ "]");
		}	
		dto.setProyecto(proyecto);
		dto.setAreas(graficas);
		return dto;
	}

	@Override
	public List<GraficaProyecto> obtenerGraficasProyectoPorAreaOrg(Integer idProyecto, String areaOrg)
			throws NirhoServiceException {
		List<GraficaProyecto> graficas = new ArrayList<>();
		try {
			graficas = graficaDAO.findByIdProyectoIdAreaOrg(idProyecto, areaOrg);
		} catch(Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD las graficas del proyecto, causa [" + e.getMessage()+ "]");
		}
		return graficas;
	}
	
	@Override
	public void generarGraficasProyecto(Integer idProyecto) throws NirhoServiceException {
		try {
			Proyecto proyecto = proyectoService.obtenerProyectoPorId(idProyecto);
			if(proyecto.getIdEstatus().getIdEstatus().intValue() == ProyectoConstants.ESTATUS_RESULTADOS.intValue()) {
				throw new NirhoServiceException("Las graficas de este proyecto ya han sido generadas");
			}
			if(proyecto.getIdEstatus().getIdEstatus().intValue() != ProyectoConstants.ESTATUS_FINALIZADO.intValue()) {
				throw new NirhoServiceException("Es necesario finalizar el estudio para generar las graficas");
			}
			persistirResultadosCorporativo(idProyecto);
			persistirResultadosProduccion(idProyecto);
			persistirResultadosVentas(idProyecto);
			persistirResultadosAdministracion(idProyecto);
			persistirResultadosControlDeCalidad(idProyecto);
			proyecto.setIdEstatus(new EstatusProyecto(ProyectoConstants.ESTATUS_RESULTADOS));
		} catch(Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException(e.getMessage());
		}
	}
	
	@Override
	public void persistirGraficaProyecto(List<GraficaProyecto> graficas) throws NirhoServiceException {
		try {
			for(GraficaProyecto gp: graficas) {
				GraficaProyecto grafProy = graficaDAO.getOne(gp.getGaficaProyectoPK());
				if(grafProy == null) {
					graficaDAO.save(gp);
				} else {
					graficaDAO.update(gp);
				}
			}
		} catch(Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD las graficas del proyecto, causa [" + e.getMessage()+ "]");
		}
	}

	@Override
	public void guardarComentario(GraficaProyecto graficaProyecto) throws NirhoServiceException {
		try {
			graficaDAO.update(graficaProyecto);
		} catch(Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD las graficas del proyecto, causa [" + e.getMessage()+ "]");
		}
	}
		
	private void persistirResultadosCorporativo(Integer idProyecto) throws NirhoServiceException {
		try {
			for(GraficaResultadoDTO grp: graficasAreaService.obtenerResultadosCorporativo(idProyecto).getResultados()) {
				try {
					GraficaProyectoPK gpPK = new GraficaProyectoPK(idProyecto, AreaOrgConstants.CORPORATIVO, grp.getPregunta().getIdTema().getIdTema());
					GraficaProyecto gp = graficaDAO.getOne(gpPK);
					if(gp == null) {
						gp = new GraficaProyecto();
						gp.setGaficaProyectoPK(gpPK);
						gp.setNumResp1(grp.getNumResp1());
						gp.setNumResp2(grp.getNumResp2());
						gp.setNumResp3(grp.getNumResp3());
						gp.setNumResp4(grp.getNumResp4());
						gp.setNumResp5(grp.getNumResp5());
						graficaDAO.save(gp);
					} else {
						gp.setNumResp1(gp.getNumResp1()+grp.getNumResp1());
						gp.setNumResp2(gp.getNumResp2()+grp.getNumResp2());
						gp.setNumResp3(gp.getNumResp3()+grp.getNumResp3());
						gp.setNumResp4(gp.getNumResp4()+grp.getNumResp4());
						gp.setNumResp5(gp.getNumResp5()+grp.getNumResp5());
						graficaDAO.update(gp);
					}
				} catch (Exception e) {
					logger.info("Exception [" + e.getMessage() + "]");
				}
			}
		} catch (NirhoServiceException e) {
			logger.info("Exception [" + e.getMessage() + "]");
			throw new NirhoServiceException(e.getMessage());
		}
	}
	
	private void persistirResultadosProduccion(Integer idProyecto) throws NirhoServiceException {
		try {
			for(GraficaResultadoDTO grp: graficasAreaService.obtenerResultadosProduccion(idProyecto).getResultados()) {
				try {
					GraficaProyectoPK gpPK = new GraficaProyectoPK(idProyecto, AreaOrgConstants.PRODUCCION, grp.getPregunta().getIdTema().getIdTema());
					GraficaProyecto gp = graficaDAO.getOne(gpPK);
					if(gp == null) {
						gp = new GraficaProyecto();
						gp.setGaficaProyectoPK(gpPK);
						gp.setNumResp1(grp.getNumResp1());
						gp.setNumResp2(grp.getNumResp2());
						gp.setNumResp3(grp.getNumResp3());
						gp.setNumResp4(grp.getNumResp4());
						gp.setNumResp5(grp.getNumResp5());
						graficaDAO.save(gp);
					} else {
						gp.setNumResp1(gp.getNumResp1()+grp.getNumResp1());
						gp.setNumResp2(gp.getNumResp2()+grp.getNumResp2());
						gp.setNumResp3(gp.getNumResp3()+grp.getNumResp3());
						gp.setNumResp4(gp.getNumResp4()+grp.getNumResp4());
						gp.setNumResp5(gp.getNumResp5()+grp.getNumResp5());
						graficaDAO.update(gp);
					}
				} catch (Exception e) {
					logger.info("Exception [" + e.getMessage() + "]");
				}
			}
		} catch (NirhoServiceException e) {
			logger.info("Exception [" + e.getMessage() + "]");
			throw new NirhoServiceException(e.getMessage());
		}
	}
	
	private void persistirResultadosVentas(Integer idProyecto) throws NirhoServiceException {
		try {
			for(GraficaResultadoDTO grp: graficasAreaService.obtenerResultadosVentas(idProyecto).getResultados()) {
				try {
					GraficaProyectoPK gpPK = new GraficaProyectoPK(idProyecto, AreaOrgConstants.VENTAS, grp.getPregunta().getIdTema().getIdTema());
					GraficaProyecto gp = graficaDAO.getOne(gpPK);
					if(gp == null) {
						gp = new GraficaProyecto();
						gp.setGaficaProyectoPK(gpPK);
						gp.setNumResp1(grp.getNumResp1());
						gp.setNumResp2(grp.getNumResp2());
						gp.setNumResp3(grp.getNumResp3());
						gp.setNumResp4(grp.getNumResp4());
						gp.setNumResp5(grp.getNumResp5());
						graficaDAO.save(gp);
					} else {
						gp.setNumResp1(gp.getNumResp1()+grp.getNumResp1());
						gp.setNumResp2(gp.getNumResp2()+grp.getNumResp2());
						gp.setNumResp3(gp.getNumResp3()+grp.getNumResp3());
						gp.setNumResp4(gp.getNumResp4()+grp.getNumResp4());
						gp.setNumResp5(gp.getNumResp5()+grp.getNumResp5());
						graficaDAO.update(gp);
					}
				} catch (Exception e) {
					logger.info("Exception [" + e.getMessage() + "]");
				}
			}
		} catch (NirhoServiceException e) {
			logger.info("Exception [" + e.getMessage() + "]");
			throw new NirhoServiceException(e.getMessage());
		}
	}
	
	private void persistirResultadosAdministracion(Integer idProyecto) throws NirhoServiceException {
		try {
			for(GraficaResultadoDTO grp: graficasAreaService.obtenerResultadosAdministracion(idProyecto).getResultados()) {
				try {
					GraficaProyectoPK gpPK = new GraficaProyectoPK(idProyecto, AreaOrgConstants.ADMINISTRACION, grp.getPregunta().getIdTema().getIdTema());
					GraficaProyecto gp = graficaDAO.getOne(gpPK);
					if(gp == null) {
						gp = new GraficaProyecto();
						gp.setGaficaProyectoPK(gpPK);
						gp.setNumResp1(grp.getNumResp1());
						gp.setNumResp2(grp.getNumResp2());
						gp.setNumResp3(grp.getNumResp3());
						gp.setNumResp4(grp.getNumResp4());
						gp.setNumResp5(grp.getNumResp5());
						graficaDAO.save(gp);
					} else {
						gp.setNumResp1(gp.getNumResp1()+grp.getNumResp1());
						gp.setNumResp2(gp.getNumResp2()+grp.getNumResp2());
						gp.setNumResp3(gp.getNumResp3()+grp.getNumResp3());
						gp.setNumResp4(gp.getNumResp4()+grp.getNumResp4());
						gp.setNumResp5(gp.getNumResp5()+grp.getNumResp5());
						graficaDAO.update(gp);
					}
				} catch (Exception e) {
					logger.info("Exception [" + e.getMessage() + "]");
				}
			}
		} catch (NirhoServiceException e) {
			logger.info("Exception [" + e.getMessage() + "]");
			throw new NirhoServiceException(e.getMessage());
		}
	}
	
	private void persistirResultadosControlDeCalidad(Integer idProyecto) throws NirhoServiceException {
		try {
			for(GraficaResultadoDTO grp: graficasAreaService.obtenerResultadosControlDeCalidad(idProyecto).getResultados()) {
				try {
					GraficaProyectoPK gpPK = new GraficaProyectoPK(idProyecto, AreaOrgConstants.CONTROL_DE_CALIDAD, grp.getPregunta().getIdTema().getIdTema());
					GraficaProyecto gp = graficaDAO.getOne(gpPK);
					if(gp == null) {
						gp = new GraficaProyecto();
						gp.setGaficaProyectoPK(gpPK);
						gp.setNumResp1(grp.getNumResp1());
						gp.setNumResp2(grp.getNumResp2());
						gp.setNumResp3(grp.getNumResp3());
						gp.setNumResp4(grp.getNumResp4());
						gp.setNumResp5(grp.getNumResp5());
						graficaDAO.save(gp);
					} else {
						gp.setNumResp1(gp.getNumResp1()+grp.getNumResp1());
						gp.setNumResp2(gp.getNumResp2()+grp.getNumResp2());
						gp.setNumResp3(gp.getNumResp3()+grp.getNumResp3());
						gp.setNumResp4(gp.getNumResp4()+grp.getNumResp4());
						gp.setNumResp5(gp.getNumResp5()+grp.getNumResp5());
						graficaDAO.update(gp);
					}
				} catch (Exception e) {
					logger.info("Exception [" + e.getMessage() + "]");
				}
			}
		} catch (NirhoServiceException e) {
			logger.info("Exception [" + e.getMessage() + "]");
			throw new NirhoServiceException(e.getMessage());
		}
	}
	
}

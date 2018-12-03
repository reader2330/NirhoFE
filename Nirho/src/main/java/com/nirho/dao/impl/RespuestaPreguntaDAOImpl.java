package com.nirho.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.nirho.dao.RespuestaPreguntaDAO;
import com.nirho.model.RespuestaPregunta;
import com.nirho.model.RespuestaPreguntaIRH;

@Repository
public class RespuestaPreguntaDAOImpl extends AbstractDAO<RespuestaPregunta, Integer> implements RespuestaPreguntaDAO {
	public void agregarEditarRespuestasCuestionarioActivoEmpresa(List<RespuestaPreguntaIRH> respuestas) {
		RespuestaPreguntaIRH respuestaPreguntaAActualizar = null;
		for (RespuestaPreguntaIRH respuestaPregunta : respuestas) {
			if(respuestaPregunta.getIdRespuestaPregunta() != null){
				respuestaPreguntaAActualizar = (RespuestaPreguntaIRH) getOne(respuestaPregunta.getIdRespuestaPregunta());
				setDatosEditar(respuestaPreguntaAActualizar, respuestaPregunta);
				update(respuestaPreguntaAActualizar);
			}
			else{
				update(respuestaPregunta);
			}
		}
	}

	@Override
	public void agregarEditarRespuestasCuestionarioActivoEmpresa(RespuestaPreguntaIRH param) {
		update(param);
	}

	@SuppressWarnings("unchecked")
	public List<RespuestaPreguntaIRH> consultarRespuestasCuestionarioIRH(Integer idCuestionarioEmpresa) {
		String hql = "SELECT rp FROM RespuestaPreguntaIRH rp WHERE rp.idCuestionarioEmpresa = :idCuestionarioEmpresa";

		Query query = this.entityManager.createQuery(hql);
		query.setParameter("idCuestionarioEmpresa", idCuestionarioEmpresa);
		return query.getResultList();
	}
	
	private void setDatosEditar(RespuestaPreguntaIRH respuestaPreguntaAActualizar, RespuestaPreguntaIRH respuestaPregunta ){
		respuestaPreguntaAActualizar.setCumplimiento(respuestaPregunta.getCumplimiento());
		respuestaPreguntaAActualizar.setVigencia(respuestaPregunta.getVigencia());
		respuestaPreguntaAActualizar.setEstado(respuestaPregunta.getEstado());
		respuestaPreguntaAActualizar.setObservaciones(respuestaPregunta.getObservaciones());
		respuestaPreguntaAActualizar.setInversionAproximada(respuestaPregunta.getInversionAproximada());
	}
}

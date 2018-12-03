package com.nirho.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.nirho.dao.CuestionarioEmpresaDAO;
import com.nirho.model.CuestionarioEmpresa;
import com.nirho.model.view.VwCuestionarioPreguntasTemas;
import com.nirho.model.view.VwCuestionarioRespuestas;
import com.nirho.model.view.VwTemaCuestionario;

@Repository
public class CuestionarioEmpresaDAOImpl extends AbstractDAO<CuestionarioEmpresa, Integer> implements CuestionarioEmpresaDAO {
	
	@SuppressWarnings("unchecked")
	public List<CuestionarioEmpresa> consultarPorRFC(String rfc) {
		String hql = "SELECT ce FROM CuestionarioEmpresa ce WHERE ce.empresa.rfc = :rfc";

		Query query = this.entityManager.createQuery(hql);
		query.setParameter("rfc", rfc);
		return query.getResultList();
	}

	public CuestionarioEmpresa consultarPreguntasTemaEmpresa(Integer idCuestionarioEmpresa) {
		String hql = "SELECT ce FROM CuestionarioEmpresa ce LEFT JOIN ce.preguntas p WHERE ce.idCuestionarioEmpresa = :idCuestionarioEmpresa";

		Query query = this.entityManager.createQuery(hql);
		query.setParameter("idCuestionarioEmpresa", idCuestionarioEmpresa);
		return (CuestionarioEmpresa) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VwCuestionarioPreguntasTemas> consultarPreguntasTemaCuestionario(int idTema) {
		Query query = this.entityManager.createNamedQuery("VwCuestionarioPreguntasTemas.findByIdTema")
				.setParameter("idTema", idTema);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VwCuestionarioRespuestas> consultarRespuestaCuestionario(int idParticipante) {
		Query query = this.entityManager.createNamedQuery("VwCuestionarioRespuestas.findByIdParticipante")
				.setParameter("idParticipante", idParticipante);
		return query.getResultList();
	}

	@Override
	public VwCuestionarioRespuestas consultarRespuestaCuestionario(int idParticipante, int idPregunta) {
		Query query = this.entityManager.createNamedQuery("VwCuestionarioRespuestas.findByIdParticipanteAndIdPregunta");
		query.setParameter("idParticipante", idParticipante);
		query.setParameter("idPregunta", idPregunta);
		VwCuestionarioRespuestas respuesta = null;
		try{
			respuesta = (VwCuestionarioRespuestas) query.getSingleResult();
		} catch(NoResultException nre){
			respuesta = new VwCuestionarioRespuestas();
		}
		return respuesta;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CuestionarioEmpresa consultarCuestionarioEmpresa(int idTema) {
		CuestionarioEmpresa cuest = null;
		Query query = this.entityManager.createNamedQuery("CuestionarioEmpresa.findByIdTema")
				.setParameter("idTema", idTema);
		List<CuestionarioEmpresa> cuestionarios = query.getResultList();
		if(cuestionarios != null && !cuestionarios.isEmpty()){
			cuest = cuestionarios.get(0);
		}
		return cuest;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VwTemaCuestionario> consultarTemasCuestionario() {
		Query query = this.entityManager.createNamedQuery("VwTemaCuestionario.findAll");
		return query.getResultList();
	}
}

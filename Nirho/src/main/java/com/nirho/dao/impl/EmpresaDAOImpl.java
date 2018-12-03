package com.nirho.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.nirho.dao.EmpresaDAO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.Empresa;
import com.nirho.model.view.VwEmpresasSolicitudCuestionario;

@Repository
public class EmpresaDAOImpl extends AbstractDAO<Empresa, Long> implements EmpresaDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Empresa> findByRfc(String rfc) {
		String hql = "FROM Empresa e WHERE e.rfc = :rfc";
		Query query = entityManager.createQuery(hql);
		query.setParameter("rfc", rfc);
		return query.getResultList();
	}

	@Override
	public void eliminarEmpresa(Empresa empresa) throws NirhoServiceException {
		Query query = entityManager.createNativeQuery("DELETE FROM EMPRESA WHERE ID = " + empresa.getId());
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VwEmpresasSolicitudCuestionario> getEmpresasSolicitadoEvaluacion() throws NirhoServiceException {
		Query query = entityManager.createNamedQuery("VwEmpresasSolicitudCuestionario.findCuestionariosActivos");
		return query.getResultList();
	}
		
}

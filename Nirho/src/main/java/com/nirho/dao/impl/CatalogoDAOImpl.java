package com.nirho.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.nirho.dao.CatalogoDAO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.Catalogo;

@Repository
@SuppressWarnings("unchecked")
public class CatalogoDAOImpl extends AbstractDAO<Catalogo, Long> implements CatalogoDAO {
	
	@Override
	public List<Catalogo> getCatPaises() throws NirhoServiceException {
		Query query = entityManager.createNamedQuery("Catalogo.findPaisesActivos");
		return query.getResultList();
	}

	@Override
	public List<Catalogo> getCatGirosEmpresas() throws NirhoServiceException {
		Query query = entityManager.createNamedQuery("Catalogo.findGirosEmpresasActivos");
		return query.getResultList();
	}

	@Override
	public List<Catalogo> findByTipoCatalogo(Long tipoCatalogo) {
		String hql = "FROM Catalogo c WHERE c.tipoCatalogo.id = :tipoCatalogo";
		Query query = entityManager.createQuery(hql);
		query.setParameter("tipoCatalogo", tipoCatalogo);
		return query.getResultList();
	}
	
}

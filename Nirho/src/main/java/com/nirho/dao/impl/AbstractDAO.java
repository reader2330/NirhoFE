package com.nirho.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.jboss.logging.Logger;

import com.nirho.dao.BaseDAO;

@Transactional
public abstract class AbstractDAO<T, Id> implements BaseDAO<T, Id> {
	
	final static Logger logger = Logger.getLogger(AbstractDAO.class);
	
	@PersistenceContext
	protected EntityManager entityManager;
	
	private final Class<T> entityClass;
	
	@SuppressWarnings("unchecked")
	public AbstractDAO() {
		this.entityClass =(Class<T>) ((ParameterizedType) 
				this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAll(){
		String hql = "FROM ".concat(entityClass.getName());
		return entityManager.createQuery(hql).getResultList();
	}
	
	@Override
	public T getOne(Id id) {
		return (T) entityManager.find(entityClass, id);
	}
	
	@Override
	public void save(T entidad) {
		entityManager.persist(entidad);
	}
	
	@Override
	public void update(T entidad) {
		entityManager.merge(entidad);		
	}
	
	@Override
	public boolean delete(T entidad){
		boolean updated = false;
		try {		
			entityManager.remove(entityManager.contains(entidad) ? entidad : entityManager.merge(entidad) );
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return updated;
	}
}

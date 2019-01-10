package com.nirho.dao;

import java.util.List;

import com.nirho.model.ContratacionVacante;

public interface ContratacionVacanteDAO extends BaseDAO<ContratacionVacante, Long> {
	List<ContratacionVacante> findByIdVacante(long idVacante);
	List<ContratacionVacante> findByIdCandidato(long idCandidato);
}

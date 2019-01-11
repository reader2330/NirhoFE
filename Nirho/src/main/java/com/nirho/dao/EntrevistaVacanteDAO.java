package com.nirho.dao;

import java.util.List;

import com.nirho.model.EntrevistaVacante;

public interface EntrevistaVacanteDAO extends BaseDAO<EntrevistaVacante, Long> {
	List<EntrevistaVacante> findByIdVacante(long idVacante);
	List<EntrevistaVacante> findByIdCandidato(long idCandidato);
	List<EntrevistaVacante> findByIdConsultor(long idConsultor); 
	List<EntrevistaVacante> findByIdSolicitante(long idSolicitante); 
}

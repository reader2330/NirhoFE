package com.nirho.dao;

import java.util.List;

import com.nirho.model.EntrevistaVacante;
import com.nirho.model.SolicitanteVacante;
import com.nirho.model.Vacante;

public interface SolicitanteVacanteDAO extends BaseDAO<SolicitanteVacante, Long> {
	List<SolicitanteVacante> findByIdConsultor(long idConsultor); 
}

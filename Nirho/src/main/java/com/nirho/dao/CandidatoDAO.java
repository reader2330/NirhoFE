package com.nirho.dao;

import java.util.List;

import com.nirho.model.Candidato;

public interface CandidatoDAO extends BaseDAO<Candidato, Long> {
	public List<Candidato> findByUsername(String username);
	public List<Candidato> findByRfc(String rfc);
}

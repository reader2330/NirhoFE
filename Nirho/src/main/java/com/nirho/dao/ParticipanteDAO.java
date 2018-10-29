package com.nirho.dao;

import java.util.List;

import com.nirho.model.Participante;

public interface ParticipanteDAO extends BaseDAO<Participante, Integer> {
	List<Participante> findByIdEmpresa(Long idEmpresa);
}

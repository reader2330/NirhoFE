package com.nirho.dao;

import java.util.List;

import com.nirho.model.ParticipanteAPO;

public interface ParticipanteAPODAO extends BaseDAO<ParticipanteAPO, Integer> {
	public List<ParticipanteAPO> findByIdProyecto(Integer idProyecto);
}

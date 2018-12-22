package com.nirho.dao;

import java.util.List;

import com.nirho.model.ParticipantePVC;

public interface ParticipantePVCDAO extends BaseDAO<ParticipantePVC, Integer> {
	public List<ParticipantePVC> findByIdProyecto(Integer idProyecto);
}

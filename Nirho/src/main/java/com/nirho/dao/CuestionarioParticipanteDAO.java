package com.nirho.dao;

import java.util.List;

import com.nirho.model.CuetionarioParticipante;
import com.nirho.model.CuetionarioParticipantePK;

public interface CuestionarioParticipanteDAO extends BaseDAO<CuetionarioParticipante, CuetionarioParticipantePK> {
	List<CuetionarioParticipante> findByParticipanteProyecto(Integer idParticipante, Integer idProyect);
}

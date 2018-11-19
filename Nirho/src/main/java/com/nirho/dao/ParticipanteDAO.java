package com.nirho.dao;

import java.util.List;

import com.nirho.model.Participante;
import com.nirho.model.ParticipantePK;

public interface ParticipanteDAO extends BaseDAO<Participante, ParticipantePK> {
	List<Participante> findByIdProyecto(Integer idProyecto);
	List<Participante> findByRfc(String rfc);
	List<Participante> findByAreaOrgProyecto(String areaOrg, int idProyecto);
}

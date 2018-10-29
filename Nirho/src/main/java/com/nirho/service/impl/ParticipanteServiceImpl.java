package com.nirho.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.dao.ParticipanteDAO;
import com.nirho.dao.ProyectoDAO;
import com.nirho.dto.NivelDTO;
import com.nirho.dto.ParticipanteDTO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.Participante;
import com.nirho.service.ParticipanteService;

@Service
public class ParticipanteServiceImpl implements ParticipanteService {
	public final static Logger logger = Logger.getLogger(ParticipanteServiceImpl.class);
	
	@Autowired
	private ParticipanteDAO participanteDAO;
	@Autowired
	private ProyectoDAO proyectoDAO;

	@Override
	public void guardarParticipanteService(List<Participante> participantes) throws NirhoServiceException {
		try {
			for(Participante p: participantes) {
				participanteDAO.save(p);
			}
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
	}

	@Override
	public List<NivelDTO> obtenerParticipantesPorProyecto(Integer idProyecto) throws NirhoServiceException {
		try {
			Long idEmpresa = proyectoDAO.getOne(idProyecto).getIdEmpresa().getId();
			List<NivelDTO> niveles = new ArrayList<>();
			Map<Integer, NivelDTO> nivelesMap = new HashedMap<>();
			for(Participante p: participanteDAO.findByIdEmpresa(idEmpresa)) {
				NivelDTO nivel = (NivelDTO) nivelesMap.get(new Integer(p.getNivel()));
				String nombre = p.getNombres()!=null?p.getNombres():"";	
				String apellidoP = p.getAPaterno()!=null?p.getAPaterno():"";
				String apellidoM = p.getAMaterno()!=null?p.getAMaterno():"";
				nombre = nombre + " " + apellidoP + " " + apellidoM;
				if(nivel != null) {
					nivel.getParticipantes().add(new ParticipanteDTO(nombre, p.getPuesto()));
				} else {
					NivelDTO ndto = new NivelDTO();
					ndto.setNivel(p.getNivel());
					ndto.setParticipantes(new ArrayList<>());				
					ndto.getParticipantes().add(new ParticipanteDTO(nombre, p.getPuesto()));
					nivelesMap.put(new Integer(p.getNivel()), ndto);
				}
			}
			for (Map.Entry<Integer, NivelDTO> entry : nivelesMap.entrySet()) {
				logger.info(entry.getValue());
				niveles.add(entry.getValue());
			}
			return niveles;
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
	}

	@Override
	public List<Participante> obtenerParticipantes(Integer idProyecto) throws NirhoServiceException {
		List<Participante> participantes = new ArrayList<>();
		try {
			Long idEmpresa = proyectoDAO.getOne(idProyecto).getIdEmpresa().getId();
			participantes = participanteDAO.findByIdEmpresa(idEmpresa);
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
		return participantes;
	}
	
}

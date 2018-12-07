package com.nirho.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.map.HashedMap;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.dao.ParticipanteAPODAO;
import com.nirho.dto.NivelDTO;
import com.nirho.dto.ParticipanteDTO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ParticipanteAPO;
import com.nirho.model.ParticipanteAPOAmp;
import com.nirho.service.ParticipanteAPOService;

@Service
public class ParticipanteAPOServiceImpl implements ParticipanteAPOService {
	
	public final static Logger logger = Logger.getLogger(ParticipanteAPOServiceImpl.class);
	
	@Autowired
	private ParticipanteAPODAO participanteAPODAO;
	
	@Override
	public ParticipanteAPO getOne(Integer id) throws NirhoServiceException {
		try {
			return participanteAPODAO.getOne(id);
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
	}
	
	@Override
	public void guardarParticipanteService(List<ParticipanteAPO> participantes) throws NirhoServiceException {
		try {
			for(ParticipanteAPO p: participantes) {
				try {
					ParticipanteAPO participante = participanteAPODAO.getOne(p.getId());
					if(participante == null) {
						participanteAPODAO.save(p);
					} else {
						participanteAPODAO.update(p);
					}
				} catch(Exception e) {
					logger.info("Exception [" + e.getMessage() + "");
				}	
			}
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
	}
	
	@Override
	public void ampliarParticipanteService(List<ParticipanteAPOAmp> ampliaciones) throws NirhoServiceException {
		try {
			for(ParticipanteAPOAmp p: ampliaciones) {
				ParticipanteAPO participante = participanteAPODAO.getOne(p.getIdParticipante());			
				if(participante != null) {
					Set<ParticipanteAPOAmp> amp = participante.getAmpliaciones();
					amp.add(p);
					participanteAPODAO.update(participante);
				}			
			}
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
	}

	@Override
	public List<NivelDTO> obtenerParticipantesPorProyecto(Integer idProyecto) throws NirhoServiceException {
		try {
			List<NivelDTO> niveles = new ArrayList<>();
			Map<Integer, NivelDTO> nivelesMap = new HashedMap<>();
			for(ParticipanteAPO p: participanteAPODAO.findByIdProyecto(idProyecto)) {
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
	public List<ParticipanteAPO> obtenerParticipantes(Integer idProyecto) throws NirhoServiceException {
		List<ParticipanteAPO> participantes = new ArrayList<>();
		try {
			participantes = participanteAPODAO.findByIdProyecto(idProyecto);
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
		return participantes;
	}
	
	@Override
	public Set<ParticipanteAPOAmp> obtenerAmpliaciones(Integer idParticipante) throws NirhoServiceException {
		try {
			ParticipanteAPO participante = participanteAPODAO.getOne(idParticipante);
			return participante.getAmpliaciones();
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
	}
		
}

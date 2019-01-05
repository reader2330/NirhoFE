package com.nirho.util;

import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ParticipantePK;

public class NirhoUtil {
	public static final String AUTO_EVAL = "autoeval";
	public static String obtenerToken(int idParticipante, int idProyecto, String rfc) {
		String sCadenaInvertida = "";
		if(rfc == null || rfc.trim().isEmpty()) {
			rfc = "NIRO010203RH4";
		}
		rfc = rfc.toLowerCase();
		for (int x=rfc.length()-1;x>=0;x--) {
			sCadenaInvertida = sCadenaInvertida + rfc.charAt(x);
		}
		String token = Integer.toString(idParticipante) + "-" + sCadenaInvertida + "-" + Integer.toString(idProyecto);
		return token;
	}
	
	public static ParticipantePK obtenerParticipanteToken(String token) throws NirhoServiceException {
		try {
			String[] datos = token.split("-");
			return new ParticipantePK(Integer.parseInt(datos[0]), Integer.parseInt(datos[2]));
		} catch(Exception e) {
			throw new NirhoServiceException("Error, token no autorizado");
		}
	}
}

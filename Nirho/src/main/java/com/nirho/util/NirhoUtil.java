package com.nirho.util;

public class NirhoUtil {
	public static String obtenerTokenRFC(String rfc) {
		String sCadenaInvertida = "";
		if(rfc != null) {
			rfc = rfc.toLowerCase();
			for (int x=rfc.length()-1;x>=0;x--) {
				sCadenaInvertida = sCadenaInvertida + rfc.charAt(x);
			}
		}
		return sCadenaInvertida;
	}
	
	public static String obtenerRFCToken(String token) {
		String sCadenaInvertida = "";
		if(token != null) {
			token = token.toUpperCase();
			for (int x=token.length()-1;x>=0;x--) {
				sCadenaInvertida = sCadenaInvertida + token.charAt(x);
			}
		}
		return sCadenaInvertida;
	}
}

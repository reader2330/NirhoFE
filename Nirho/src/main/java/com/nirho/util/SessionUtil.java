package com.nirho.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import com.nirho.model.Usuario;

public class SessionUtil {
	public static Usuario getUsuarioSession(HttpServletRequest request) {
		Usuario usuario = null;
		if (request.getSession(false) != null) {
			usuario = (Usuario) request.getSession(false).getAttribute(SessionConstants.USUARIO_ATTRIBUTE);
		}
		return usuario;
	}
	
	public static String getEncryptMD5(String input) {
		 try {
		 MessageDigest md = MessageDigest.getInstance("MD5");
		 byte[] messageDigest = md.digest(input.getBytes());
		 BigInteger number = new BigInteger(1, messageDigest);
		 String hashtext = number.toString(16);
		 
		 while (hashtext.length() < 32) {
		 hashtext = "0" + hashtext;
		 }
		 return hashtext;
		 }
		 catch (NoSuchAlgorithmException e) {
		 throw new RuntimeException(e);
		 }
	}
	
	public static void main(String[] args) {
		System.out.println(getEncryptMD5("ventasconsultor"));
		System.out.println(getEncryptMD5("nirhogerente"));
		System.out.println(getEncryptMD5("nirhoconsultor"));
		System.out.println(getEncryptMD5("password"));
	}
}

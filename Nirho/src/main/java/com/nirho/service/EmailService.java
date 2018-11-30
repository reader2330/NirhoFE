package com.nirho.service;

import javax.servlet.http.HttpServletRequest;

import com.nirho.dto.EmailDatos;
import com.nirho.exception.NirhoServiceException;

public interface EmailService {
	void sendEmail(EmailDatos datos, HttpServletRequest request) throws NirhoServiceException;
}

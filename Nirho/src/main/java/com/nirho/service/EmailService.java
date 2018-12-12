package com.nirho.service;

import com.nirho.dto.EmailDatos;
import com.nirho.exception.NirhoServiceException;

public interface EmailService {
	void sendEmail(EmailDatos datos, String cc) throws NirhoServiceException;
	void sendEmailEVD(EmailDatos datos, String cc) throws NirhoServiceException;
}

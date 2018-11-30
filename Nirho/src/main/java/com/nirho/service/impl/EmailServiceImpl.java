package com.nirho.service.impl;

import javax.mail.internet.MimeMessage;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.nirho.dto.EmailDatos;
import com.nirho.exception.NirhoServiceException;
import com.nirho.service.EmailService;
import com.nirho.util.EmailUtil;

@Service
public class EmailServiceImpl implements EmailService {
	public final static Logger logger = Logger.getLogger(EmailServiceImpl.class);
	@Autowired
	private JavaMailSender mailSender;
		
	@Override
	public void sendEmail(EmailDatos datos, String cc) throws NirhoServiceException {
		try {
			MimeMessage mail = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mail);
			helper.setTo(datos.getEmailDestino());
			helper.setSubject(EmailUtil.getAsunto(datos.getNombreProyecto()));
			helper.setText(EmailUtil.getContenido(datos), true);
			helper.setCc(cc);
			mailSender.send(mail);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al enviar el email causa [" + e.getMessage()+ "]");
		}
	}
	
}

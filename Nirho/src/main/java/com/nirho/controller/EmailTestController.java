package com.nirho.controller;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nirho.dto.EmailDatos;
import com.nirho.service.EmailService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping( value = "/correo" )
public class EmailTestController {
	@Autowired
	private EmailService emailService;
	
    @Autowired
    private JavaMailSender mailSender;
    
    @RequestMapping(path = "/email-test-1", method = RequestMethod.GET)
    public String sendMail1() {
        try {
            MimeMessage mail = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper( mail );
            helper.setTo( "eistenroman@gmail.com" );
            helper.setSubject( "Cuestionario de participante Nirho" );
            helper.setText("<p>Estimado Jose Perez<p></p>Para ingresar a su cuestionario de participación al proyecto ${Proyecto}, "
            		+ "por favor hacer clic en la siguiente liga: http://localhost:8080/Nirho/cuestionario/participante?token=1-meh521187cluj-5 </p>"
            		+ "<p>Saludos</p>", true);
            mailSender.send(mail);
                        
            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro al enviar e-mail";
        }
    }
    
    @RequestMapping(path = "/email-test-2", method = RequestMethod.GET)
    public String sendMail2() {
        try {
        	EmailDatos datos = new EmailDatos();
    		datos.setEmailDestino("eistenroman@gmail.com");
    		datos.setNombreParticipante("Eisten Roman");
    		datos.setNombreProyecto("Evaluacion 360 ADA");
    		datos.setToken("3-mdc400188krep-6");
    		emailService.sendEmailEVA360(datos, "eisten.gonzalez@gmail.com");
            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro al enviar e-mail";
        }
    }
    
    @RequestMapping(path = "/email-test-4", method = RequestMethod.GET)
    public String sendMail4() {
        try {
            MimeMessage mail = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper( mail );
            helper.setTo( "eistenroman@gmail.com" );
            helper.setSubject( "Cuestionario de participante Nirho" );
            helper.setText("<p>Estimado Jose Perez<p></p>Para ingresar a su cuestionario de participación al proyecto ${Proyecto}, "
            		+ "por favor hacer clic en la siguiente liga:  <a href='http://localhost:8080/Nirho/cuestionario/participante?token=1-meh521187cluj-5'>liga</a> </p>"
            		+ "<p><img src='http://74.208.252.39/logo.bdfd5939a8d871515e82.png'></p>"
            		+ "<p>Saludos</p>", true);
            mailSender.send(mail);
                        
            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro al enviar e-mail";
        }
    }
}

package com.nirho.controller;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailTestController {

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
    
}

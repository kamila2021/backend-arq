package com.arquitectura.proyecto.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED;

@Service
@RequiredArgsConstructor
@Log4j2
public class EmailService {

    private final JavaMailSender mailSender;

    private final SpringTemplateEngine templateEngine;

    public void sendEmail(String to,
                          String email,
                          String activationCode,
                          String subject) throws MessagingException {

        log.info("📧 Enviando correo a: " + to + " con código: " + activationCode);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom("no-reply@yourapp.com");
        message.setSubject(subject);
        message.setText("Tu código de activación es: " + activationCode);

        mailSender.send(message);
        log.info("✅ Correo enviado correctamente");
    }


}
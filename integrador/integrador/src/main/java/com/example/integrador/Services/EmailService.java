package com.example.integrador.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendPasswordResetEmail(String toEmail, String token, String baseUrl) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject("Restablecimiento de Contrase\u00f1a - Los Reyes del Sabor");
        message.setText("Estimado cliente,\n\n" +
                "Has solicitado restablecer tu contrase\u00f1a. Por favor, haz clic en el siguiente enlace para crear una nueva:\n\n" +
                baseUrl + "/reset-password?token=" + token + "\n\n" +
                "El enlace expirar\u00e1 en 24 horas.\n\n" +
                "Si no solicitaste este cambio, puedes ignorar este mensaje.\n\n" +
                "Saludos,\nLos Reyes del Sabor");

        mailSender.send(message);
    }
}
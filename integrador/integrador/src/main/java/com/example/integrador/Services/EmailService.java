/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.integrador.Services;
/*
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    
    @Value("${spring.mail.username}")
    private String fromEmail;

    public void sendPasswordResetEmail(String toEmail, String token, String baseUrl) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject("Restablecimiento de Contraseña - Los Reyes del Sabor");
        message.setText("Estimado cliente,\n\n" +
                "Has solicitado restablecer tu contraseña. Por favor, haz clic en el siguiente enlace:\n\n" +
                baseUrl + "/reset-password?token=" + token + "\n\n" +
                "Este enlace expirará en 24 horas.\n\n" +
                "Si no solicitaste este cambio, por favor ignora este mensaje.\n\n" +
                "Saludos,\nLos Reyes del Sabor");
        
        mailSender.send(message);
    }
}*/
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.integrador.Services;

import com.example.integrador.Entidades_Model.PasswordResetToken;
import com.example.integrador.Entidades_Model.Usuario;
import com.example.integrador.Repositorio.PasswordResetTokenRepository;
import com.example.integrador.Repositorio.UsuarioRepositorio;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class PasswordResetService {

    private final PasswordResetTokenRepository tokenRepository;
    private final UsuarioRepositorio usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public String generateResetToken(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        // Eliminar token anterior si existe
        tokenRepository.findByUsuario(usuario).ifPresent(tokenRepository::delete);

        // Crear nuevo token
        PasswordResetToken resetToken = new PasswordResetToken(usuario);
        tokenRepository.save(resetToken);
        
        return resetToken.getToken();
    }

    public boolean validateToken(String token) {
        return tokenRepository.findByToken(token)
            .map(resetToken -> !resetToken.isExpired())
            .orElse(false);
    }

    public void changePassword(String token, String newPassword) {
        PasswordResetToken resetToken = tokenRepository.findByToken(token)
            .orElseThrow(() -> new RuntimeException("Token inválido"));

        if (resetToken.isExpired()) {
            tokenRepository.delete(resetToken);
            throw new RuntimeException("Token expirado");
        }

        Usuario usuario = resetToken.getUsuario();
        usuario.setPassword(passwordEncoder.encode(newPassword));
        usuarioRepository.save(usuario);
        
        tokenRepository.delete(resetToken);
    }
}
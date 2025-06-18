/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.integrador.Controladores;


import com.example.integrador.Services.PasswordResetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class PasswordResetController {

    private final PasswordResetService passwordResetService;

    @GetMapping("/forgot-password")
    public String showForgotPasswordForm() {
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String processForgotPassword(@RequestParam("email") String email,
                                      RedirectAttributes redirectAttributes) {
        try {
            String token = passwordResetService.generateResetToken(email);
            return "redirect:/reset-password?token=" + token;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", 
                "No se encontró una cuenta con ese correo electrónico");
            return "redirect:/forgot-password";
        }
    }

    @GetMapping("/reset-password")
    public String showResetPasswordForm(@RequestParam("token") String token,
                                      Model model,
                                      RedirectAttributes redirectAttributes) {
        if (!passwordResetService.validateToken(token)) {
            redirectAttributes.addFlashAttribute("error", 
                "El enlace no es válido o ha expirado");
            return "redirect:/forgot-password";
        }
        
        model.addAttribute("token", token);
        return "reset-password";
    }

    @PostMapping("/reset-password")
    public String handlePasswordReset(@RequestParam("token") String token,
                                    @RequestParam("password") String password,
                                    RedirectAttributes redirectAttributes) {
        try {
            passwordResetService.changePassword(token, password);
            redirectAttributes.addFlashAttribute("success", 
                "Contraseña actualizada exitosamente");
            return "redirect:/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", 
                "Error al cambiar la contraseña: " + e.getMessage());
            return "redirect:/reset-password?token=" + token;
        }
    }
}
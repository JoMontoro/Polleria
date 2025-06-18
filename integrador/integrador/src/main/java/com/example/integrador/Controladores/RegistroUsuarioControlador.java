package com.example.integrador.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.integrador.Services.UsuarioServicio;
import com.example.integrador.controlador.dto.UsuarioRegistroDTO;

@Controller
@RequestMapping("/registro")
public class RegistroUsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @ModelAttribute("usuario")
    public UsuarioRegistroDTO retornarNuevoUsuarioRegistroDTO() {
        return new UsuarioRegistroDTO();
    }

    @GetMapping
    public String mostrarFormularioDeRegistro() {
        return "registro";
    }

    @PostMapping
    public String registrarCuentaDeUsuario(@ModelAttribute("usuario") UsuarioRegistroDTO registroDTO, 
                                         RedirectAttributes redirectAttributes) {
        try {
            usuarioServicio.guardar(registroDTO);
            redirectAttributes.addFlashAttribute("registroExitoso", true);
            return "redirect:/login?registro=true";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al registrar el usuario: " + e.getMessage());
            return "redirect:/registro";
        }
    }
}
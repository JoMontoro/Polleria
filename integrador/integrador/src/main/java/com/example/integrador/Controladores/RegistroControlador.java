package com.example.integrador.Controladores;

import com.example.integrador.Services.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistroControlador {

    @Autowired
    private UsuarioServicio servicio;
    
    @GetMapping("/usuarios")  // Cambiado de "/" a "/usuarios"
    public String verPaginaDeInicio(Model modelo) {
        modelo.addAttribute("usuarios", servicio.listarUsuarios());
        return "index";
    }
}

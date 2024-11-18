package com.example.integrador.Controladores;

import com.example.integrador.Services.EstadisticaService;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class Controlador {

    @GetMapping("/")
    public String mostrarIndex() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/index")
    public String mostrarIndexConModelo(Model model) {
        return "index";
    }

 @Autowired
    private EstadisticaService estadisticaService;
    
    @GetMapping("/listageneral")
    public String listageneral(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario = auth.getName();
        model.addAttribute("nombreUsuario", nombreUsuario);
        
        Map<String, Object> estadisticas = estadisticaService.obtenerEstadisticasAnuales();
        model.addAttribute("estadisticas", estadisticas);
        
        return "listageneral";
    }

    @GetMapping("/contacto")
    public String contacto(Model model) {
        return "contacto";
    }

    @GetMapping("/ladmin")
    public String ladmin(Model model) {
        return "ladmin";
    }

    @GetMapping("/comentario")
    public String sugerencias(Model model) {
        return "comentario";
    }

    @GetMapping("/reclamaciones")
    public String reclamaciones(Model model) {
        return "reclamaciones";
    }

    @GetMapping("/fromcompra")
    public String fromclient(Model model) {
        return "fromcompra";
    }

    @GetMapping("/carrito")
    public String especificaciones(Model model) {
        return "carrito";
    }
    @GetMapping("/nosotros")
    public String nosotros(Model model) {
        return "nosotros";
    }
}
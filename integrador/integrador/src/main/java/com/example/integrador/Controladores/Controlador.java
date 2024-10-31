package com.example.integrador.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Controlador {

    @GetMapping("/")
    public String mostrarIndex() {  // Cambiado nombre del método a mostrarIndex
        return "index";
    }

    @GetMapping("/index")
    public String mostrarIndexConModelo(Model model) {  // Cambiado nombre del método a mostrarIndexConModelo
        return "index";
    }

    @GetMapping("/listageneral")
    public String listageneral(Model model) {
        return "listageneral";
    }

    @GetMapping("/contacto")
    public String contacto(Model model) {
        return "contacto";
    }

    @GetMapping("/ladmin")
    public String login(Model model) {
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
}

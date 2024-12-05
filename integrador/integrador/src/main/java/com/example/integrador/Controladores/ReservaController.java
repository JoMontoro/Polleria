package com.example.integrador.Controladores;

import com.example.integrador.Entidades_Model.Reserva;
import com.example.integrador.Services.ReservaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

    @GetMapping
    public String mostrarFormularioReservas(Model model) {
        if (!model.containsAttribute("reserva")) {
            model.addAttribute("reserva", new Reserva()); // Agrega un objeto vacío al modelo
        }
        return "reservas";
    }

    @PostMapping("/guardar")
    public String guardarReserva(@ModelAttribute Reserva reserva, RedirectAttributes redirectAttributes) {
        // Lógica para guardar reserva en la base de datos
        redirectAttributes.addFlashAttribute("mensaje", "Reserva realizada exitosamente");
        return "redirect:/reservas/confirmacion";
    }

    @GetMapping("/confirmacion")
    public String mostrarConfirmacion() {
        return "reservas-confirmacion"; // Nueva vista para confirmación
    }
}
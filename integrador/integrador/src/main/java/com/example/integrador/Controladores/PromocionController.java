package com.example.integrador.Controladores;

import com.example.integrador.Entidades_Model.Promocion;
import com.example.integrador.Services.PromocionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PromocionController {

    private final PromocionService promocionService;

    public PromocionController(PromocionService promocionService) {
        this.promocionService = promocionService;
    }

    @GetMapping("/promociones")
    public String mostrarPromociones(Model model) {
        List<Promocion> promociones = promocionService.obtenerPromocionesActivas();
        model.addAttribute("promociones", promociones);
        return "promociones";
    }
}
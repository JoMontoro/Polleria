
package com.example.integrador.Controladores;

import com.example.integrador.Entidades_Model.Chefs;
import com.example.integrador.Services.ServicioChefs;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChefsControlador {
    
    @Autowired
    ServicioChefs serviciochefs;
    
    @GetMapping("/excelc")
    public ResponseEntity<InputStreamResource> exportarExcel() throws IOException {
        ByteArrayInputStream flujo = serviciochefs.generarExcel();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=Chef.xls");
        return ResponseEntity.ok().
                headers(headers).
                body(new InputStreamResource(flujo));
    }
    
    @GetMapping("/cheflista")
    public String Chefslista(Model model) {
        List<Chefs> lista = serviciochefs.getList();
        model.addAttribute("lista", lista);
        
        return "cheflista";
    }
    
    @GetMapping("/formchef")
    public String formChefs(Model model) {
        model.addAttribute("chefs", new Chefs());
        return "formchef";
    }
    
    @PostMapping("/registrarchef")
    public String registrarChefs(@ModelAttribute Chefs chefs, Model model) {
        try {
            serviciochefs.save(chefs);
            return "redirect:/cheflista";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("errorMessage", e.getMessage().toString());
            model.addAttribute("chefs", chefs);
            return "formchef";
        }
    }
    
    
    
       @GetMapping("/getEditchefs/{chefid}")
    public String editarFormChefs(Model model, @PathVariable("chefid") Long id) {
        Chefs chefs = serviciochefs.get(id);
        model.addAttribute("chefs", chefs);
        return "formchef";
    }
    
    
    @GetMapping("/deletechefs/{chefid}")
    public String eliminarFormChefs(Model model, @PathVariable("chefid") Long id) {
        serviciochefs.delete(id);
        return "redirect:/cheflista";
    }
    
    
}

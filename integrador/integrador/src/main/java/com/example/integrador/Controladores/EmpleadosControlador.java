/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.integrador.Controladores;

import com.example.integrador.Entidades_Model.Empleados;
import com.example.integrador.Services.ServicioEmpleados;
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
public class EmpleadosControlador {
    @Autowired
    ServicioEmpleados servicioempleados;
   
    @GetMapping("/excele")
  public ResponseEntity<InputStreamResource> exportarExcel() throws IOException {
        ByteArrayInputStream flujo = servicioempleados.generarExcel();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=Empleado.xls");
        return ResponseEntity.ok().
                headers(headers).
                body(new InputStreamResource(flujo));
    }
    
    @GetMapping("/empleadoslista")
    public String Empleadoslista(Model model) {
        List<Empleados> lista = servicioempleados.getList();
        model.addAttribute("lista", lista);
        
        return "empleadoslista";
    }
    
    @GetMapping("/formempleados")
    public String formEmpleados(Model model) {
        model.addAttribute("empleados", new Empleados());
        return "formempleados";
    }
    
    @PostMapping("/registrarempleados")
    public String registrarEmpleados(@ModelAttribute Empleados empleados, Model model) {
        try {
            servicioempleados.save(empleados);
            return "redirect:/empleadoslista";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("errorMessage", e.getMessage().toString());
            model.addAttribute("empleados", empleados);
            return "formempleados";
        }
    }
    
    
    @GetMapping("/getEditempleados/{empleado_id}")
    public String editarFormEmpleados(Model model, 
            @PathVariable("empleado_id") Long id) {
        Empleados empleados = servicioempleados.get(id);
        model.addAttribute("empleados", empleados);
        return "formempleados";
    }
    
    
    @GetMapping("/deleteempleados/{empleado_id}")
    public String eliminarFormEmpleados(Model model, 
            @PathVariable("empleado_id") Long id) {
        servicioempleados.delete(id);
        return "redirect:/empleadoslista";
    }
    
    
    
    
    
    
}

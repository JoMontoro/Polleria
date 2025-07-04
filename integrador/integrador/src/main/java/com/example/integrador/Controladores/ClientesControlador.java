
package com.example.integrador.Controladores;

import com.example.integrador.Entidades_Model.Clientes;
import com.example.integrador.Services.ServicioClientes;
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
public class ClientesControlador {
    
    @Autowired
    ServicioClientes servicioCliente;
    
   
  @GetMapping("/excelx")
public ResponseEntity<InputStreamResource> exportarExcel() throws IOException {
    ByteArrayInputStream flujo = servicioCliente.generarExcel();
    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Clientes.xls");
    headers.add(HttpHeaders.CONTENT_TYPE, "application/vnd.ms-excel"); // Tipo de contenido para archivos Excel

    return ResponseEntity.ok()
            .headers(headers)
            .body(new InputStreamResource(flujo));
}
    
    
    @GetMapping("/clientelista")
    public String Clienteslista(Model model) {
        List<Clientes> lista = servicioCliente.getList();
        model.addAttribute("lista", lista);
        
        return "clientelista";
    }
    
    @GetMapping("/formclientes")
    public String formClientes(Model model) {
        model.addAttribute("clientes", new Clientes());
        return "formclientes";
    }
    
    @PostMapping("/registrarclientes")
    public String registrarClientes(@ModelAttribute Clientes clientes, Model model) {
        try {
            servicioCliente.save(clientes);
            return "redirect:/clientelista";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("errorMessage", e.getMessage().toString());
            model.addAttribute("clientes", clientes);
            return "formclientes";
        }
    }

    
    @GetMapping("/getEdit/{cliente_id}")
    public String editarFormClientes(Model model, @PathVariable("cliente_id") Long id) {
        Clientes clientes = servicioCliente.get(id);
        model.addAttribute("clientes", clientes);
        return "formclientes";
    }
    
    
    @GetMapping("/delete/{cliente_id}")
    public String eliminarFormClientes(Model model, @PathVariable("cliente_id") Long id) {
        servicioCliente.delete(id);
        return "redirect:/clientelista";
    }
    
    
    
    
    
    
}

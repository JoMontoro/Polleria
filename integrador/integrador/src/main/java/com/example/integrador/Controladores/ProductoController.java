package com.example.integrador.Controladores;


import com.example.integrador.Entidades_Model.Productos;
import com.example.integrador.Services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // formulario de nuevo producto
    @GetMapping("/nuevoProducto")
    public String mostrarFormularioNuevoProducto() {
        return "nuevoProducto";
    }

    // Guardar nuevo producto
    @PostMapping("/guardarProducto")
    public String guardarProducto(@ModelAttribute Productos producto,
                                  @RequestParam("imagen") MultipartFile imagen,
                                  RedirectAttributes redirectAttributes) {
        try {
            productoService.guardarProducto(producto, imagen);
            redirectAttributes.addFlashAttribute("mensaje", "Producto guardado exitosamente");
            return "redirect:/menu";
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al guardar el producto: " + e.getMessage());
            return "redirect:/nuevoProducto";
        }
    }

    // Mostrar menú con todos los productos
    @GetMapping("/menu")
    public String mostrarMenu(Model model) {
        List<Productos> productos = productoService.obtenerTodosLosProductos();
        model.addAttribute("productos", productos);
        return "menu";
    }

    // Si necesitas un endpoint para obtener un producto específico
    @GetMapping("/producto/{id}")
    public String verProducto(@PathVariable Long id, Model model) {
        // Aquí irá la lógica para obtener un producto específico
        return "detalleProducto";
    }

    // Endpoint para obtener productos por categoría
    @GetMapping("/productos")
    @ResponseBody
    public List<Productos> obtenerProductosPorCategoria(@RequestParam(required = false) String categoria) {
        if (categoria == null || categoria.isEmpty()) {
            // Si no se selecciona ninguna categoría, devuelve todos los productos
            return productoService.obtenerTodosLosProductos();
        } else {
            // Devuelve productos filtrados por la categoría seleccionada
            return productoService.obtenerProductosPorCategoria(categoria);
        }
    }

}
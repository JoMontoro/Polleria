/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.integrador.Services;

import com.example.integrador.Entidades_Model.Productos;
import com.example.integrador.Repositorio.ProductosDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class ProductoService {
    @Autowired
    private ProductosDAO productosDAO;

    @Value("${upload.path}")
    private String uploadPath;

    public Productos guardarProducto(Productos producto, MultipartFile imagen) throws IOException {
        // Crear directorio si no existe
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Generar nombre único para la imagen
        String nombreArchivo = UUID.randomUUID().toString() + "_" + imagen.getOriginalFilename();
        Path rutaCompleta = Paths.get(uploadPath + nombreArchivo);

        // Guardar imagen
        Files.copy(imagen.getInputStream(), rutaCompleta, StandardCopyOption.REPLACE_EXISTING);

        // Actualizar la URL de la imagen en el producto
        producto.setImagen_url(nombreArchivo);

        return productosDAO.save(producto);
    }

    // ----------------------------Obtener todos los productos
    public List<Productos> obtenerTodosLosProductos() {
        return productosDAO.findAll();
    }
    
    public Productos obtenerProductoPorId(Long id) {
        return productosDAO.findById(id).orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con ID: " + id));
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.integrador.Services;
import com.example.integrador.Entidades_Model.Productos;
import com.example.integrador.Repositorio.ProductosDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductosDAO productosDAO;

    public Productos guardarProducto(Productos producto) {
        return productosDAO.save(producto);
    }

    // Obtener todos los productos
    public List<Productos> obtenerTodosLosProductos() {
        return productosDAO.findAll();
    }


}
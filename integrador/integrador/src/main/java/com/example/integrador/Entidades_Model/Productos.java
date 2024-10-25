/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.integrador.Entidades_Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table (name = "productos")
public class Productos {
     @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id_productos;
    
    private String nombre;
    private String descripcion;
    private Long precio;
    private Integer stock;
    private Long id_proveedor;
     private Long id_almacen;
}

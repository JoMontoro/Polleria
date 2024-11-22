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

@Table (name = "detalle_pedidos")
public class detalle_pedidos {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id_detallepedido;
    
    private Long id_pedido;
    private Long id_productos;    private Long cantidad;
    private Long precio_unitario;
    
}

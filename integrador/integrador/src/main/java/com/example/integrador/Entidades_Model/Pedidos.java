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
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table (name = "pedidos")
public class Pedidos {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id_pedido;
    
    private LocalDate fecha;
    private String hora_pedido;
    private Long cliente_id;
    private Long id_pago;
    private Long chef_id;
     
}

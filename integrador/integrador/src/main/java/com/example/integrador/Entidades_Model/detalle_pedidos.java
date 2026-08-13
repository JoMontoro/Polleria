/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.integrador.Entidades_Model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pedido", referencedColumnName = "id_pedido", insertable = false, updatable = false)
    private Pedidos pedido;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_productos", referencedColumnName = "producto_id", insertable = false, updatable = false)
    private Productos producto;
}

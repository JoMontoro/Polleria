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
    private Long mesa_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id", referencedColumnName = "cliente_id", insertable = false, updatable = false)
    private Clientes cliente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pago", referencedColumnName = "id_pago", insertable = false, updatable = false)
    private metodos_pago metodoPago;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "chef_id", referencedColumnName = "chefid", insertable = false, updatable = false)
    private Chefs chef;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mesa_id", referencedColumnName = "id", insertable = false, updatable = false)
    private mesas mesa;
}

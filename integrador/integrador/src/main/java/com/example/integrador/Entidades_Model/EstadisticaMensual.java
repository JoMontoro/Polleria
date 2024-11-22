/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.integrador.Entidades_Model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Month;



@Entity
@Table(name = "estadisticas_mensuales")
public class EstadisticaMensual {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_estadistica;
    
    @Enumerated(EnumType.STRING)
    private Month mes;
    
    private Integer año;
    private Integer total_clientes;
    private Double total_ventas;
    
    // Constructor vacío necesario para JPA
    public EstadisticaMensual() {
    }
    
    // Constructor con parámetros
    public EstadisticaMensual(Month mes, Integer año, Integer totalClientes, Double totalVentas) {
        this.mes = mes;
        this.año = año;
        this.total_clientes = totalClientes;
        this.total_ventas = totalVentas;
    }

    // Getters y Setters
    public Long getId() {
        return id_estadistica;
    }

    public void setId(Long id) {
        this.id_estadistica = id;
    }

    public Month getMes() {
        return mes;
    }

    public void setMes(Month mes) {
        this.mes = mes;
    }

    public Integer getAño() {
        return año;
    }

    public void setAño(Integer año) {
        this.año = año;
    }

    public Integer getTotalClientes() {
        return total_clientes;
    }

    public void setTotalClientes(Integer totalClientes) {
        this.total_clientes = totalClientes;
    }

    public Double getTotalVentas() {
        return total_ventas;
    }

    public void setTotalVentas(Double totalVentas) {
        this.total_ventas = totalVentas;
    }

    // Método toString para depuración
    @Override
    public String toString() {
        return "EstadisticaMensual{" +
                "id=" + id_estadistica +
                ", mes=" + mes +
                ", año=" + año +
                ", totalClientes=" + total_clientes +
                ", totalVentas=" + total_ventas +
                '}';
    }
}
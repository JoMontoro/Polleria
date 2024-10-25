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
@Table (name = "clientes")
public class Clientes {
    @Id
    
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long cliente_id;
    
    private String nombre;
    private String apellido;
    private String Correo_Electronico;
    private String telefono;
    private String direccion;
    
}

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
@Table (name = "empleados")
public class Empleados {
    @Id
   
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long empleado_id;
    
    private String nombre;
    private String apellido;
    private String dni;
    private String cargo;
    private String telefono;
    private String correo_electronico;
    
}

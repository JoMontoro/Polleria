/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.integrador.Entidades_Model;


import jakarta.persistence.Column;
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
    @Column(name = "empleado_id") // Especifica explícitamente el nombre de la columna
    private Long empleado_id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "dni")
    private String dni;

    @Column(name = "cargo")
    private String cargo;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "correo_electronico")
    private String correo_electronico;
}


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
@Table (name = "Chefs")
public class Chefs {
    @Id
    
    @GeneratedValue (strategy = GenerationType.IDENTITY)
       private Long chefid;
    
    private String nombre;
    private String apellido;
    private String especialidad;
    private String telefono;
    private String correo_electronico;
    
    
}

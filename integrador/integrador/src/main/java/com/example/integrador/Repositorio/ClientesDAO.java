/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.integrador.Repositorio;

import com.example.integrador.Entidades_Model.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author hp
 */
public interface ClientesDAO extends JpaRepository<Clientes, Long>{
    
}

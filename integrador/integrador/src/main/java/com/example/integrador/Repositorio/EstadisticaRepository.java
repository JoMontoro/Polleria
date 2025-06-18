/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.integrador.Repositorio;

import com.example.integrador.Entidades_Model.EstadisticaMensual;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hp
 */
@Repository
public interface EstadisticaRepository extends JpaRepository<EstadisticaMensual, Long> {
    
  
    List<EstadisticaMensual> findByAñoOrderByMes(Integer año);
    
    @Query(value = "SELECT SUM(total_ventas) FROM estadisticas_mensuales WHERE año = :año", nativeQuery = true)
    Double findTotalVentasAño(@Param("año") Integer año);
    
    @Query(value = "SELECT SUM(total_clientes) FROM estadisticas_mensuales WHERE año = :año", nativeQuery = true)
    Integer findTotalClientesAño(@Param("año") Integer año);
}
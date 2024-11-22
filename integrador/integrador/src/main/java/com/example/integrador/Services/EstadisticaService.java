/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.integrador.Services;

import com.example.integrador.Entidades_Model.EstadisticaMensual;
import com.example.integrador.Repositorio.EstadisticaRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EstadisticaService {
    
    @Autowired
    private EstadisticaRepository estadisticaRepository;
    
     public Map<String, Object> obtenerEstadisticasAnuales() {
        int añoActual = LocalDate.now().getYear();
        List<EstadisticaMensual> estadisticas = estadisticaRepository.findByAñoOrderByMes(añoActual);
        
        Map<String, Object> resultado = new HashMap<>();
        resultado.put("meses", estadisticas.stream()
                .map(e -> e.getMes().toString())
                .collect(Collectors.toList()));
        resultado.put("ventas", estadisticas.stream()
                .map(EstadisticaMensual::getTotalVentas)
                .collect(Collectors.toList()));
        resultado.put("clientes", estadisticas.stream()
                .map(EstadisticaMensual::getTotalClientes)
                .collect(Collectors.toList()));
        resultado.put("totalVentasAño", estadisticaRepository.findTotalVentasAño(añoActual));
        resultado.put("totalClientesAño", estadisticaRepository.findTotalClientesAño(añoActual));
        
        return resultado;
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.integrador.Services;

import com.example.integrador.Entidades_Model.Empleados;
import com.example.integrador.Repositorio.EmpleadoDAO;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioEmpleados {
    @Autowired
    private EmpleadoDAO empleadoDAO;

    //Select
    @Transactional(readOnly = true)
    public List<Empleados> getList() {
        return empleadoDAO.findAll();
    }

    //INSERT Y UPDATE
    @Transactional
    public Empleados save(Empleados empleados) {
        return empleadoDAO.save(empleados);
    }

    //SELECT
    @Transactional(readOnly = true)
    public Empleados get(Long id) {
        return empleadoDAO.findById(id).orElse(null);
    }

    //DELETE
    @Transactional
    public void delete(Long id) {
        empleadoDAO.deleteById(id);
    }
    

}


package com.example.integrador.Repositorio;

import com.example.integrador.Entidades_Model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolRepositorio extends JpaRepository<Rol, Long> {
    List<Rol> findByNombre(String nombre);
}
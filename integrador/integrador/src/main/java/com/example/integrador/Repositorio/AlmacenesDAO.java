package com.example.integrador.Repositorio;

import com.example.integrador.Entidades_Model.Almacenes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlmacenesDAO extends JpaRepository<Almacenes, Long> {
}
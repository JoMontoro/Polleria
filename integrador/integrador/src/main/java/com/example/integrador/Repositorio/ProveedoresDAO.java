package com.example.integrador.Repositorio;

import com.example.integrador.Entidades_Model.Proveedores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedoresDAO extends JpaRepository<Proveedores, Long> {
}
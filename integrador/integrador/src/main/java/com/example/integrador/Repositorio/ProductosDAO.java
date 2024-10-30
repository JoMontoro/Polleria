
package com.example.integrador.Repositorio;

import com.example.integrador.Entidades_Model.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductosDAO extends JpaRepository<Productos, Long> {
    
}

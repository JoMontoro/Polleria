
package com.example.integrador.Repositorio;

import com.example.integrador.Entidades_Model.Chefs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChefsDAO extends JpaRepository<Chefs, Long> {
    
}

package com.example.integrador.Repositorio;

import com.example.integrador.Entidades_Model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,Integer> {

    public AppUser findByEmail(String email);
}

package com.example.integrador.Repositorio;

import com.example.integrador.Entidades_Model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppUserRepository extends JpaRepository<AppUser,Integer> {

    public AppUser findByEmail(String email);
    List<AppUser> findByEmail(String role);
}

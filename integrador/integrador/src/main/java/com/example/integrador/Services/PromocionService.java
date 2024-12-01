package com.example.integrador.Services;

import com.example.integrador.Entidades_Model.Promocion;
import com.example.integrador.Repositorio.PromocionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromocionService {

    private final PromocionRepository promocionRepository;

    public PromocionService(PromocionRepository promocionRepository) {
        this.promocionRepository = promocionRepository;
    }

    public List<Promocion> obtenerPromocionesActivas() {
        return promocionRepository.findAll(); // Devuelve todas las promociones
    }
}
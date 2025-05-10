package com.laboratorio.service.interfaces;

import com.laboratorio.dto.PlanoDTO;
import com.laboratorio.persistence.model.Plano;

import java.util.List;
import java.util.Optional;

public interface IPlanoService {
    void save(PlanoDTO plano);
    void update(Plano plano);
    List<Plano> findAll();
    Optional<Plano> findByCodigo(String codigo);
}

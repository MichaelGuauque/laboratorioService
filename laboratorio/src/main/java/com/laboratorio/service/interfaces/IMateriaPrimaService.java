package com.laboratorio.service.interfaces;

import com.laboratorio.dto.MateriaPrimaDTO;
import com.laboratorio.persistence.model.MateriaPrima;

import java.util.List;
import java.util.Optional;

public interface IMateriaPrimaService {

    List<MateriaPrima> findAll();
    Optional<MateriaPrima> findById(Long id);
    void save(MateriaPrimaDTO materiaPrima);
    void update(MateriaPrima materiaPrima);
}

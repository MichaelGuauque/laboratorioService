package com.laboratorio.persistence.repository;

import com.laboratorio.persistence.model.Plano;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlanoRepository extends CrudRepository<Plano, Long> {
    Optional<Plano> findByCodigo(String codigo);
}

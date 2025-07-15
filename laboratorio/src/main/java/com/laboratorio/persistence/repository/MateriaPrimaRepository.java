package com.laboratorio.persistence.repository;

import com.laboratorio.persistence.model.MateriaPrima;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MateriaPrimaRepository extends CrudRepository<MateriaPrima, Long> {
    Optional<MateriaPrima> findByReferencia(String referencia);
}

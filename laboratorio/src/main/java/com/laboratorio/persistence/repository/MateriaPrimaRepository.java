package com.laboratorio.persistence.repository;

import com.laboratorio.persistence.model.MateriaPrima;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MateriaPrimaRepository extends CrudRepository<MateriaPrima, Long> {
}

package com.laboratorio.persistence.repository;

import com.laboratorio.persistence.model.Plano;
import com.laboratorio.persistence.model.PlanoDetalleMaterial;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlanoDetalleMaterialRepository extends CrudRepository<PlanoDetalleMaterial, Long> {
    List<PlanoDetalleMaterial> findByPlano(Plano plano);
}

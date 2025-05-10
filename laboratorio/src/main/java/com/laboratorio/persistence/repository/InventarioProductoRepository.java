package com.laboratorio.persistence.repository;

import com.laboratorio.persistence.model.InventarioProducto;
import com.laboratorio.persistence.model.Plano;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventarioProductoRepository extends CrudRepository<InventarioProducto, Long> {
    Optional<InventarioProducto> findByPlano(Plano plano);
}

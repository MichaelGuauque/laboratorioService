package com.laboratorio.persistence.repository;

import com.laboratorio.persistence.model.Producto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Long> {

    Optional<Producto> findByCodigoSerie(String codigo);

}

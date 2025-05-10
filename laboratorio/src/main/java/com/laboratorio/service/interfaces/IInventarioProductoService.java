package com.laboratorio.service.interfaces;

import com.laboratorio.persistence.model.InventarioProducto;

import java.util.List;
import java.util.Optional;

public interface IInventarioProductoService {
    List<InventarioProducto> findAll();
    Optional<InventarioProducto> findById(Long id);
}

package com.laboratorio.service.interfaces;

import com.laboratorio.persistence.model.Producto;

import java.util.List;

public interface IProductoService {

    List<Producto> findAll();
}

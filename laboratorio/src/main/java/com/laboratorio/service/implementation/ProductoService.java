package com.laboratorio.service.implementation;

import com.laboratorio.persistence.model.Producto;
import com.laboratorio.persistence.repository.ProductoRepository;
import com.laboratorio.service.interfaces.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> findAll() {
        return (List<Producto>) productoRepository.findAll();
    }
}

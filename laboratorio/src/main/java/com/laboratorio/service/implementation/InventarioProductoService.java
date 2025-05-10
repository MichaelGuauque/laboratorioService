package com.laboratorio.service.implementation;

import com.laboratorio.persistence.model.InventarioProducto;
import com.laboratorio.persistence.repository.InventarioProductoRepository;
import com.laboratorio.service.interfaces.IInventarioProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventarioProductoService implements IInventarioProductoService {


    @Autowired
    private InventarioProductoRepository inventarioProductoRepository;

    @Override
    public List<InventarioProducto> findAll() {
        return (List<InventarioProducto>) inventarioProductoRepository.findAll();
    }

    @Override
    public Optional<InventarioProducto> findById(Long id) {
        return inventarioProductoRepository.findById(id);
    }


}

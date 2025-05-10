package com.laboratorio.controller;

import com.laboratorio.persistence.model.InventarioProducto;
import com.laboratorio.service.interfaces.IInventarioProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/lab/inventario")
public class InventarioProductoController {

    @Autowired
    private IInventarioProductoService inventarioProductoService;

    @GetMapping
    public ResponseEntity<List<InventarioProducto>> getAll() {
        return ResponseEntity.ok(inventarioProductoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventarioProducto> get(@PathVariable Long id) {
        return ResponseEntity.ok(inventarioProductoService.findById(id).orElseThrow());
    }
}

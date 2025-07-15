package com.laboratorio.controller;

import com.laboratorio.dto.DespachoProductoDTO;
import com.laboratorio.persistence.model.InventarioProducto;
import com.laboratorio.service.implementation.ConsumoApi;
import com.laboratorio.service.interfaces.IInventarioProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/lab/inventario")
public class InventarioProductoController {

    @Autowired
    private IInventarioProductoService inventarioProductoService;

    @Autowired
    private ConsumoApi consumoApi;

    @GetMapping
    public ResponseEntity<List<InventarioProducto>> getAll() {
        return ResponseEntity.ok(inventarioProductoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventarioProducto> get(@PathVariable Long id) {
        return ResponseEntity.ok(inventarioProductoService.findById(id).orElseThrow());
    }

    @PostMapping("/enviar")
    public void enviarProductos(@RequestBody DespachoProductoDTO despacho) {
        consumoApi.enviarDespacho(despacho);
    }
}

package com.laboratorio.controller;


import com.laboratorio.dto.ConstruirDTO;
import com.laboratorio.persistence.model.Producto;
import com.laboratorio.service.interfaces.IConstruccionService;
import com.laboratorio.service.interfaces.IProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/lab/productos")
public class ProductoController {

    @Autowired
    private IProductoService productoService;
    @Autowired
    private IConstruccionService construccionService;
    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);


    @GetMapping
    public ResponseEntity<List<Producto>> getAll() {
        return ResponseEntity.ok(productoService.findAll());
    }

    @PostMapping("/fabricar")
    public ResponseEntity<Producto> fabricar(@RequestBody ConstruirDTO construirDTO, UriComponentsBuilder uriComponentsBuilder) {
        LOGGER.info("Iniciando fabricar producto {}", construirDTO);
        Producto prod = construccionService.construir(construirDTO);
        URI url = uriComponentsBuilder.path("/lab/productos/{id}").buildAndExpand(prod.getId()).toUri();
        return ResponseEntity.created(url).body(prod);
    }
}

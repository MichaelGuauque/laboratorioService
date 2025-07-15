package com.laboratorio.controller;


import com.laboratorio.dto.ConstruirDTO;
import com.laboratorio.dto.DespachoProductoDTO;
import com.laboratorio.persistence.model.Producto;
import com.laboratorio.service.implementation.ConsumoApi;
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
    @Autowired
    private ConsumoApi consumoApi;
    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);


    @GetMapping
    public ResponseEntity<List<Producto>> getAll() {
        return ResponseEntity.ok(productoService.findAll());
    }

    @PostMapping("/fabricar")
    public ResponseEntity<Producto> fabricar(@RequestBody ConstruirDTO construirDTO) {
        LOGGER.info("Iniciando fabricar producto {}", construirDTO);
        construccionService.construir(construirDTO);
        DespachoProductoDTO despacho = new DespachoProductoDTO("DESPACHO-LAB", construirDTO.codigoSerie(), construirDTO.cantidad());
        consumoApi.enviarDespacho(despacho);
        return ResponseEntity.ok().build();
    }
}

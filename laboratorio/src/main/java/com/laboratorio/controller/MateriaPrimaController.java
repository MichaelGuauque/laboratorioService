package com.laboratorio.controller;

import com.laboratorio.dto.MateriaPrimaDTO;
import com.laboratorio.persistence.model.MateriaPrima;
import com.laboratorio.service.interfaces.IMateriaPrimaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/lab/materias")
public class MateriaPrimaController {

    @Autowired
    private IMateriaPrimaService materiaPrimaService;

    @GetMapping
    public ResponseEntity<List<MateriaPrima>> getAll() {
        List<MateriaPrima> lista = materiaPrimaService.findAll();
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<MateriaPrimaDTO> create(@RequestBody MateriaPrimaDTO m) {
        materiaPrimaService.save(m);
        return ResponseEntity.ok(m);
    }

    @PutMapping("/update")
    public ResponseEntity  update(@RequestBody MateriaPrima m) {
        materiaPrimaService.update(m);
        return ResponseEntity.ok().build();
    }

}

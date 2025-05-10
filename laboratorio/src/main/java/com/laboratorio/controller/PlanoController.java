package com.laboratorio.controller;

import com.laboratorio.dto.PlanoDTO;
import com.laboratorio.persistence.model.Plano;
import com.laboratorio.service.interfaces.IPlanoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/lab/planos")
public class PlanoController {

    @Autowired
    private IPlanoService planoService;

    @GetMapping
    public ResponseEntity<List<Plano>> getAll() {
        return ResponseEntity.ok(planoService.findAll());
    }

    @PostMapping
    public ResponseEntity<PlanoDTO> create(@RequestBody PlanoDTO plano) {
        planoService.save(plano);
        return ResponseEntity.ok(plano);
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Plano plano) {
        planoService.update(plano);
        return ResponseEntity.ok(plano);
    }

}


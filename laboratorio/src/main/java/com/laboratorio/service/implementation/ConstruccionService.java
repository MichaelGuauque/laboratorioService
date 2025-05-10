package com.laboratorio.service.implementation;

import com.laboratorio.dto.ConstruirDTO;
import com.laboratorio.persistence.model.*;
import com.laboratorio.persistence.repository.*;
import com.laboratorio.service.interfaces.IConstruccionService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ConstruccionService implements IConstruccionService {

    @Autowired
    private PlanoRepository planoRepo;
    @Autowired
    private MateriaPrimaRepository materiaRepo;
    @Autowired
    private InventarioProductoRepository inventarioRepo;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private PlanoDetalleMaterialRepository planoDetalleMaterialRepository;


    @Override
    @Transactional
    public Producto construir(ConstruirDTO construirDTO) {
        // Obtener plano
        Plano plano = planoRepo.findById(construirDTO.planoId())
                .orElseThrow(() -> new EntityNotFoundException("Plano no encontrado con ID: " + construirDTO.planoId()));

        // Obtener los materiales requeridos por ese plano
        List<PlanoDetalleMaterial> materialesRequeridos = planoDetalleMaterialRepository.findByPlano(plano);

        // Verificar y descontar materiales
        for (PlanoDetalleMaterial detalle : materialesRequeridos) {
            MateriaPrima materia = detalle.getMateriaPrima();
            double cantidadNecesaria = detalle.getCantidad();

            if (materia.getCantidadDisponible() < cantidadNecesaria) {
                throw new IllegalStateException("No hay suficiente " + materia.getNombre() + ". Se necesita " + cantidadNecesaria + " " + materia.getUnidad());
            }

            materia.setCantidadDisponible(materia.getCantidadDisponible() - cantidadNecesaria);
            materiaRepo.save(materia);
        }

        // Crear nueva cuchilla
        Producto nuevaCuchilla = Producto.builder()
                .codigoSerie(construirDTO.codigoSerie())
                .fechaFabricacion(LocalDate.now())
                .planoUsado(plano)
                .build();
        productoRepository.save(nuevaCuchilla);

        // Actualizar inventario de cuchillas
        InventarioProducto inventario = inventarioRepo.findByPlano(plano)
                .orElseGet(() -> InventarioProducto.builder()
                        .plano(plano)
                        .cantidadDisponible(0)
                        .build());

        inventario.setCantidadDisponible(inventario.getCantidadDisponible() + 1);
        inventarioRepo.save(inventario);

        return nuevaCuchilla;
    }
}

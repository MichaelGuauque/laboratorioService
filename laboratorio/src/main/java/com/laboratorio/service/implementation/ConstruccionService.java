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
import java.util.ArrayList;
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
    @Autowired
    ConsumoRequisisionApi consumoRequisisionApi;


    @Override
    @Transactional
    public void construir(ConstruirDTO construirDTO) {
        List<Producto> cuchillas = new ArrayList<>();
        Producto producto = productoRepository.findByCodigoSerie(construirDTO.codigoSerie()).get();
        // Obtener plano
        Plano plano = producto.getPlanoUsado();

        // Obtener los materiales requeridos por ese plano
        List<PlanoDetalleMaterial> materialesRequeridos = planoDetalleMaterialRepository.findByPlano(plano);

        // Actualizar inventario de cuchillas
        InventarioProducto inventario = inventarioRepo.findByPlano(plano)
                .orElseGet(() -> InventarioProducto.builder()
                        .plano(plano)
                        .cantidadDisponible(0)
                        .build());

        for (int i = 0; i <= construirDTO.cantidad() ; i++){
            // Verificar y descontar materiales
            for (PlanoDetalleMaterial detalle : materialesRequeridos) {
                MateriaPrima materia = detalle.getMateriaPrima();
                double cantidadNecesaria = detalle.getCantidad();

                if (materia.getCantidadDisponible() < cantidadNecesaria) {

                    //Solicitar materia prima.
                    consumoRequisisionApi.enviarProducto(materia);
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
            cuchillas.add(producto);
            inventario.setCantidadDisponible(inventario.getCantidadDisponible() + 1);
        }


        inventarioRepo.save(inventario);
        productoRepository.saveAll(cuchillas);
    }
}

package com.laboratorio.service.implementation;

import com.laboratorio.dto.MateriaDTO;
import com.laboratorio.dto.MateriaPrimaDTO;
import com.laboratorio.persistence.model.MateriaPrima;
import com.laboratorio.persistence.repository.MateriaPrimaRepository;
import com.laboratorio.service.interfaces.IMateriaPrimaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class MateriaPrimaService implements IMateriaPrimaService {

    @Autowired
    private MateriaPrimaRepository materiaPrimaRepository;

    @Override
    public List<MateriaPrima> findAll() {
        return (List<MateriaPrima>) materiaPrimaRepository.findAll();
    }

    @Override
    public Optional<MateriaPrima> findById(Long id) {
        return materiaPrimaRepository.findById(id);
    }

    @Override
    public void save(MateriaPrimaDTO materiaPrima) {
        materiaPrimaRepository.save(cambiarMateriaPrimaDTO(materiaPrima));
    }

    @Override
    public void update(MateriaDTO materiaPrima) {
        Optional<MateriaPrima> findById = findByReferencia(materiaPrima.referencia());
        if (findById.isPresent()) {
            MateriaPrima m = findById.get();
            m.agregarCantidadDisponible(materiaPrima.cantidad());
            materiaPrimaRepository.save(m);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Material no encontrado");
        }
    }

    @Override
    public Optional<MateriaPrima> findByReferencia(String referencia) {
        return materiaPrimaRepository.findByReferencia(referencia);
    }

    public MateriaPrima cambiarMateriaPrimaDTO(MateriaPrimaDTO materiaPrimaDTO) {
        return MateriaPrima.builder()
                .nombre(materiaPrimaDTO.nombre())
                .referencia(materiaPrimaDTO.referencia())
                .unidad(materiaPrimaDTO.unidad())
                .cantidadDisponible(materiaPrimaDTO.cantidadDisponible())
                .build();
    }
}

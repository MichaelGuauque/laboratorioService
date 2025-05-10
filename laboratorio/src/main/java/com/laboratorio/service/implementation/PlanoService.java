package com.laboratorio.service.implementation;

import com.laboratorio.dto.PlanoDTO;
import com.laboratorio.persistence.model.Plano;
import com.laboratorio.persistence.repository.PlanoRepository;
import com.laboratorio.service.interfaces.IPlanoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PlanoService implements IPlanoService {

    @Autowired
    private PlanoRepository planoRepository;

    @Override
    public void save(PlanoDTO planoDTO) {
        planoRepository.save(cambiarPlanoDTO(planoDTO));
    }

    @Override
    public void update(Plano plano) {
        Optional<Plano> planoOptional = planoRepository.findById(plano.getId());
        if (planoOptional.isPresent()) {
            planoRepository.save(plano);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Plano no encontrado");
        }
    }

    @Override
    public List<Plano> findAll() {
        return (List<Plano>) planoRepository.findAll();
    }

    @Override
    public Optional<Plano> findByCodigo(String codigo) {
        return planoRepository.findByCodigo(codigo);
    }

    public Plano cambiarPlanoDTO(PlanoDTO planoDTO) {
        return Plano.builder()
                .codigo(planoDTO.codigo())
                .descripcion(planoDTO.descripcion())
                .build();
    }
}

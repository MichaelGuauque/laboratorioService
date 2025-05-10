package com.laboratorio.service.interfaces;

import com.laboratorio.dto.ConstruirDTO;
import com.laboratorio.persistence.model.Producto;

public interface IConstruccionService {
    public Producto construir(ConstruirDTO construirDTO);
}

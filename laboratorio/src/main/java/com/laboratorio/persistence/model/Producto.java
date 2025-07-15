package com.laboratorio.persistence.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigoSerie;
    private LocalDate fechaFabricacion;
    private Estado estado;

    @ManyToOne
    private Plano planoUsado;
}

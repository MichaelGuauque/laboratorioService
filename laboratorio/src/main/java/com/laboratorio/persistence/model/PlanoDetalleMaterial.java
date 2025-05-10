package com.laboratorio.persistence.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class PlanoDetalleMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Plano plano;

    @ManyToOne
    private MateriaPrima materiaPrima;

    private Double cantidad;
}

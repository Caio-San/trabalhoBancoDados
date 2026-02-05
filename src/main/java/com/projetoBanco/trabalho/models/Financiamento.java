package com.projetoBanco.trabalho.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Financiamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String agenciaFinanciador;
    private String tipoFomento;
    private Double valorTotal;

    // Relacionamento inverso com Projeto (0..1)
    @OneToOne(mappedBy = "financiamento")
    private Projeto projeto;
}
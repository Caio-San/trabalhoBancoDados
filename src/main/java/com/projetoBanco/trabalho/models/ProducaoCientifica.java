package com.projetoBanco.trabalho.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProducaoCientifica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String tipoProducao; // Ex: Artigo, Tese, Resumo
    private LocalDate anoPublicacao;
    private String meioDivulgacao; // Ex: Revista, Congresso

    // Toda produção nasce de um projeto (conforme o traço no diagrama)
    @ManyToOne
    @JoinColumn(name = "projeto_id")
    private Projeto projeto;

    // Relacionamento "autor de" (N:N)
    @ManyToMany
    @JoinTable(
        name = "producao_autores",
        joinColumns = @JoinColumn(name = "producao_id"),
        inverseJoinColumns = @JoinColumn(name = "participante_id")
    )
    private List<Participante> autores;
}
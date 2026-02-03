package com.projetoBanco.trabalho.repositories;
import com.projetoBanco.trabalho.models.ProducaoCientifica;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProducaoCientificaRepository extends JpaRepository<ProducaoCientifica, Long> {
    // 8. Buscar Produções por Projeto
    List<ProducaoCientifica> findByProjetoId(Long projetoId);
}

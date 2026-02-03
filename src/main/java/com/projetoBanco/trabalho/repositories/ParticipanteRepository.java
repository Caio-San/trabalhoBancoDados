package com.projetoBanco.trabalho.repositories;

import com.projetoBanco.trabalho.models.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface ParticipanteRepository extends JpaRepository<Participante, Long> {
    // Busca por CPF (Útil para o login ou validação)
    Optional<Participante> findByCpf(String cpf);
    
    // Busca por Email
    Optional<Participante> findByEmail(String email);
}

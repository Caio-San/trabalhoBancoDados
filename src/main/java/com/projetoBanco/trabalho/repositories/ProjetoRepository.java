package com.projetoBanco.trabalho.repositories;

import com.projetoBanco.trabalho.models.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import java.util.Optional;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
    
    // Busca exata pelo código que o usuário conhece
    Optional<Projeto> findByCodigoUnico(String codigoUnico);
    
    List<Projeto> findByCoordenadorId(Long docenteId);


    // Buscar por parte do título
    List<Projeto> findByTituloContainingIgnoreCase(String titulo);
}
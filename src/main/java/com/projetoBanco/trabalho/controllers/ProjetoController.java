package com.projetoBanco.trabalho.controllers;

import com.projetoBanco.trabalho.models.Projeto;
import com.projetoBanco.trabalho.repositories.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projetos") // Base da URL para projetos
public class ProjetoController {

    @Autowired
    private ProjetoRepository projetoRepository;

    // 3. Cadastrar Projeto (POST)
    @PostMapping
    public Projeto salvar(@RequestBody Projeto projeto) {
        return projetoRepository.save(projeto);
    }

    // Listar todos os projetos
    @GetMapping
    public List<Projeto> listarTodos() {
        return projetoRepository.findAll();
    }

    // URL: /api/projetos/codigo/PROJ-ABC-123
    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<Projeto> buscarPorCodigoUnico(@PathVariable String codigo) {
        return projetoRepository.findByCodigoUnico(codigo)
            .map(projeto -> ResponseEntity.ok().body(projeto))
            .orElse(ResponseEntity.notFound().build());
    }

    // Buscar Projeto por Título
    // URL: /api/projetos/busca?titulo=IA
    @GetMapping("/busca")
    public List<Projeto> buscarPorTitulo(@RequestParam String titulo) {
        return projetoRepository.findByTituloContainingIgnoreCase(titulo);
    }

    // Buscar um projeto específico pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Projeto> buscarPorId(@PathVariable Long id) {
        return projetoRepository.findById(id)
                .map(projeto -> ResponseEntity.ok().body(projeto))
                .orElse(ResponseEntity.notFound().build());
    }
}
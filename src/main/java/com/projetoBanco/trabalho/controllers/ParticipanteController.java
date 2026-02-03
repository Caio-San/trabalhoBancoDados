package com.projetoBanco.trabalho.controllers;

import com.projetoBanco.trabalho.models.*;
import com.projetoBanco.trabalho.repositories.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participantes")
public class ParticipanteController {

    @Autowired
    private ParticipanteRepository participanteRepository;

    // 2. Cadastrar Docente
    @PostMapping("/docentes")
    public Docente salvarDocente(@RequestBody Docente docente) {
        return participanteRepository.save(docente);
    }

    // 2. Cadastrar Discente
    @PostMapping("/discentes")
    public Discente salvarDiscente(@RequestBody Discente discente) {
        return participanteRepository.save(discente);
    }

    // 2. Cadastrar Técnico-Administrativo
    @PostMapping("/tecnicos")
    public Tecnico salvarTecnico(@RequestBody Tecnico tecnico) {
        return participanteRepository.save(tecnico);
    }

    // Listar todos os participantes (de qualquer tipo)
    @GetMapping
    public List<Participante> listarTodos() {
        return participanteRepository.findAll();
    }

    // Buscar por CPF
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Participante> buscarPorCpf(@PathVariable String cpf) {
        return participanteRepository.findByCpf(cpf)
                .map(p -> ResponseEntity.ok(p))
                .orElse(ResponseEntity.notFound().build());
    }
}
package com.projetoBanco.trabalho.controllers;

import com.projetoBanco.trabalho.models.ProducaoCientifica;
import com.projetoBanco.trabalho.models.ProducaoParticipante;
import com.projetoBanco.trabalho.models.Projeto;
import com.projetoBanco.trabalho.repositories.ProducaoCientificaRepository;
import com.projetoBanco.trabalho.repositories.ProducaoParticipanteRepository;
import com.projetoBanco.trabalho.repositories.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/producoes")
public class ProducaoCientificaController {

    @Autowired
    private ProducaoCientificaRepository producaoCientificaRepository;

    @Autowired
    private ProducaoParticipanteRepository producaoParticipanteRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    // 1. Criar uma Produção Científica (POST)
    // Body: { "titulo", "tipoProducao", "anoPublicacao", "meioDivulgacao" }
    // Params: cpfParticipante, codigoProjeto
    @PostMapping
    public ResponseEntity<?> criarProducao(
            @RequestBody ProducaoCientifica producao,
            @RequestParam String cpfParticipante,
            @RequestParam String codigoProjeto) {

        // Busca o projeto pelo código único
        Projeto projeto = projetoRepository.findByCodigoUnico(codigoProjeto).orElse(null);

        if (projeto == null) {
            return ResponseEntity.badRequest().body("Projeto não encontrado");
        }

        // Associa a produção ao projeto
        producao.setProjeto(projeto);

        // Salva a produção
        ProducaoCientifica producaoSalva = producaoCientificaRepository.save(producao);

        // Adiciona o participante à produção
        ProducaoParticipante producaoParticipante = new ProducaoParticipante();
        producaoParticipante.setCpfParticipante(cpfParticipante);
        producaoParticipante.setProducao(producaoSalva);
        producaoParticipante.setAtivo(true);
        producaoParticipanteRepository.save(producaoParticipante);

        return ResponseEntity.ok(producaoSalva);
    }

    // 2. Deletar uma Produção Científica por ID (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProducao(@PathVariable Long id) {
        if (!producaoCientificaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        producaoCientificaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // 3. Buscar Produções por Título (GET)
    @GetMapping("/busca")
    public ResponseEntity<List<ProducaoCientifica>> buscarPorTitulo(@RequestParam String titulo) {
        List<ProducaoCientifica> producoes = producaoCientificaRepository.findByTituloContainingIgnoreCase(titulo);
        return ResponseEntity.ok(producoes);
    }

    // 4. Buscar Produções por CPF de um Participante (GET)
    @GetMapping("/participante/{cpf}")
    public ResponseEntity<List<ProducaoCientifica>> buscarProducoesPorCpfParticipante(@PathVariable String cpf) {
        List<ProducaoParticipante> producaoParticipantes = producaoParticipanteRepository.findByCpfParticipante(cpf);
        
        // Extrai as produções a partir das associações
        List<ProducaoCientifica> producoes = producaoParticipantes.stream()
                .map(ProducaoParticipante::getProducao)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(producoes);
    }
}
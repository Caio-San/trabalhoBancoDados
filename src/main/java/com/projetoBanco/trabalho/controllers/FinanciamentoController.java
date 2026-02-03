package com.projetoBanco.trabalho.controllers;

import com.projetoBanco.trabalho.models.Financiamento;
import com.projetoBanco.trabalho.repositories.FinanciamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/financiamentos")
public class FinanciamentoController {

    @Autowired
    private FinanciamentoRepository financiamentoRepository;

    // 4. Cadastrar Financiamento (POST)
    @PostMapping
    public Financiamento salvar(@RequestBody Financiamento financiamento) {
        return financiamentoRepository.save(financiamento);
    }

    // Listar todos os financiamentos
    @GetMapping
    public List<Financiamento> listarTodos() {
        return financiamentoRepository.findAll();
    }

}



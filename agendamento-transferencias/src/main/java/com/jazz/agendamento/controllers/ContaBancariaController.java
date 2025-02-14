package com.jazz.agendamento.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jazz.agendamento.entity.ContaBancaria;
import com.jazz.agendamento.services.ContaBancariaService;

@RestController
@RequestMapping("/contas")
public class ContaBancariaController {
    private final ContaBancariaService contaBancariaService;

    public ContaBancariaController(ContaBancariaService contaBancariaService) {
        this.contaBancariaService = contaBancariaService;
    }

    @PostMapping
    public ResponseEntity<ContaBancaria> criarConta(@RequestBody ContaBancaria conta) {
        return ResponseEntity.ok(contaBancariaService.salvarConta(conta));
    }

    @GetMapping
    public ResponseEntity<List<ContaBancaria>> listarContas() {
        return ResponseEntity.ok(contaBancariaService.listarContas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaBancaria> buscarConta(@PathVariable Long id) {
        return contaBancariaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
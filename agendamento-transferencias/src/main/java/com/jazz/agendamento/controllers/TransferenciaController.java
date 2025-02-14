package com.jazz.agendamento.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jazz.agendamento.entity.Transferencia;
import com.jazz.agendamento.services.TransferenciaService;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {
    private final TransferenciaService transferenciaService;

    public TransferenciaController(TransferenciaService transferenciaService) {
        this.transferenciaService = transferenciaService;
    }

    @PostMapping
    public ResponseEntity<Transferencia> agendarTransferencia(@RequestBody Transferencia transferencia) {
        return ResponseEntity.ok(transferenciaService.agendarTransferencia(transferencia));
    }

    @GetMapping
    public ResponseEntity<List<Transferencia>> listarTransferencias() {
        return ResponseEntity.ok(transferenciaService.listarTransferencias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transferencia> buscarTransferencia(@PathVariable Long id) {
        return transferenciaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

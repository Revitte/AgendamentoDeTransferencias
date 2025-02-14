package com.jazz.agendamento.services;

import java.util.List;
import java.util.Optional;

import com.jazz.agendamento.entity.Transferencia;
import com.jazz.agendamento.repository.TransferenciaRepository;

public class TransferenciaService {
    private final TransferenciaRepository transferenciaRepository;

    public TransferenciaService(TransferenciaRepository transferenciaRepository) {
        this.transferenciaRepository = transferenciaRepository;
    }

    public Transferencia agendarTransferencia(Transferencia transferencia) {
        transferencia.setStatus("AGENDADA");
        return transferenciaRepository.save(transferencia);
    }

    public List<Transferencia> listarTransferencias() {
        return transferenciaRepository.findAll();
    }

    public Optional<Transferencia> buscarPorId(Long id) {
        return transferenciaRepository.findById(id);
    }
}

package com.jazz.agendamento.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jazz.agendamento.entity.ContaBancaria;
import com.jazz.agendamento.repository.ContaBancariaRepository;

@Service
public class ContaBancariaService {
    private final ContaBancariaRepository contaBancariaRepository;

    public ContaBancariaService(ContaBancariaRepository contaBancariaRepository) {
        this.contaBancariaRepository = contaBancariaRepository;
    }

    public ContaBancaria salvarConta(ContaBancaria conta) {
        return contaBancariaRepository.save(conta);
    }

    public List<ContaBancaria> listarContas() {
        return contaBancariaRepository.findAll();
    }

    public Optional<ContaBancaria> buscarPorId(Long id) {
        return contaBancariaRepository.findById(id);
    }
}

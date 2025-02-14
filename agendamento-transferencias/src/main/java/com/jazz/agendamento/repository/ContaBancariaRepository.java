package com.jazz.agendamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jazz.agendamento.entity.ContaBancaria;

@Repository
public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Long> {}

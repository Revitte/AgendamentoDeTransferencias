package com.jazz.agendamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jazz.agendamento.entity.Transferencia;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {}

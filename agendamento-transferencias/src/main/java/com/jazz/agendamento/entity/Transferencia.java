package com.jazz.agendamento.entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "transferencias")
public class Transferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal valor;
    private LocalDateTime dataAgendamento;
    private String status;
    
    @ManyToOne
    @JoinColumn(name = "conta_origem_id")
    private ContaBancaria contaOrigem;
    
    @ManyToOne
    @JoinColumn(name = "conta_destino_id")
    private ContaBancaria contaDestino;
    
    // Getters e Setters
}

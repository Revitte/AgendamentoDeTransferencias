package com.jazz.agendamento.entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "transferencias")
@Getter
@Setter
@NoArgsConstructor
public class Transferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal valor;
    private LocalDateTime dataAgendamento;
    private String status;

    public void setStatus(String status) {
        this.status = status;
    }
    
    @ManyToOne
    @JoinColumn(name = "conta_origem_id")
    private ContaBancaria contaOrigem;
    
    @ManyToOne
    @JoinColumn(name = "conta_destino_id")
    private ContaBancaria contaDestino;
    
    @PrePersist
    public void prePersist() {
        this.dataAgendamento = LocalDateTime.now(); // Define a data de agendamento no momento do cadastro
    }
}

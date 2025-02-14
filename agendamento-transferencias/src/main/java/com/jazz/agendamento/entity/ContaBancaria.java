package com.jazz.agendamento.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "contas")
public class ContaBancaria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String banco;
    private String agencia;
    private String numeroConta;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    
    // Getters e Setters
}

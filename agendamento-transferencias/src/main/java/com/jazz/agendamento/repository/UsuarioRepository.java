package com.jazz.agendamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jazz.agendamento.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {}

package com.example.testeintegracao.repository;

import com.example.testeintegracao.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}

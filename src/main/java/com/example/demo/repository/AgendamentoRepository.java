package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long>{

}

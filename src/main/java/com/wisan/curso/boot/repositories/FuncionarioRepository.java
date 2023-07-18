package com.wisan.curso.boot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wisan.curso.boot.domain.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

}

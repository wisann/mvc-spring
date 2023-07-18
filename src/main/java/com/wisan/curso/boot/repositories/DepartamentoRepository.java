package com.wisan.curso.boot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wisan.curso.boot.domain.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

	

}

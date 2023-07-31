package com.wisan.curso.boot.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wisan.curso.boot.domain.Departamento;
import com.wisan.curso.boot.repositories.DepartamentoRepository;

@Service
public class DepartamentoService {

	@Autowired
	private DepartamentoRepository departamentoRepository;

	@Transactional(readOnly = true)
	public Departamento findById(Long id) {
		return departamentoRepository.findById(id).get();
	}

	@Transactional(readOnly = true)
	public List<Departamento> findAll() {
		return departamentoRepository.findAll();
	}

	@Transactional
	public Departamento insert(Departamento departamento) {
		return departamentoRepository.saveAndFlush(departamento);
	}

	@Transactional
	public Departamento update(Departamento departamento) {
		Long id = departamento.getId();
		Departamento entity = departamentoRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Departamento não encontrado com o ID: " + id));
		updateData(entity, departamento);
		return departamentoRepository.saveAndFlush(entity);
	}

	public void delete(Long id) {
		departamentoRepository.deleteById(id);
	}
	public boolean thereIsADepartment (Long id) {
		if(findById(id).getCargos().isEmpty()) {
			return false;
		} return true;
	}


	private void updateData(Departamento entity, Departamento obj) {
		entity.setNome(obj.getNome());
	}
}

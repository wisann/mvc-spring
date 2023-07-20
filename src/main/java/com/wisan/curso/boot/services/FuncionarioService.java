package com.wisan.curso.boot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wisan.curso.boot.domain.Funcionario;
import com.wisan.curso.boot.repositories.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Transactional(readOnly = true)
	public Funcionario findById(Long id) {
		return funcionarioRepository.findById(id).get();
	}

	@Transactional(readOnly = true)
	public List<Funcionario> findAll() {
		return funcionarioRepository.findAll();
	}

	@Transactional
	public Funcionario insert(Funcionario funcionario) {
		return funcionarioRepository.saveAndFlush(funcionario);
	}

	@Transactional
	public Funcionario update(Funcionario funcionario, Long id) {
		Funcionario obj = funcionarioRepository.getReferenceById(id);
		updateData(obj, funcionario);
		return funcionarioRepository.saveAndFlush(obj);
	}

	@Transactional
	public void delete(Long id) {
		funcionarioRepository.deleteById(id);
	}

	private void updateData(Funcionario entity, Funcionario obj) {
		entity.setName(obj.getName());
		entity.setSalario(obj.getSalario());
		entity.setDataSaida(obj.getDataSaida());
		entity.setEndereco(obj.getEndereco());
		entity.setCargo(obj.getCargo());
	}
}

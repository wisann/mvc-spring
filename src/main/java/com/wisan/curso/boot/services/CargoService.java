package com.wisan.curso.boot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wisan.curso.boot.domain.Cargo;
import com.wisan.curso.boot.repositories.CargoRepository;

@Service
public class CargoService {

	@Autowired
	private CargoRepository cargoRepository;

	@Transactional(readOnly = true)
	public void FinById(Long id) {
		cargoRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public List<Cargo> findAll() {
		return cargoRepository.findAll();
	}
	
	@Transactional
	public Cargo insert(Cargo cargo) {
		return cargoRepository.saveAndFlush(cargo);
	}
	
	@Transactional
	public Cargo update(Cargo cargo, Long id) {
		Cargo entity = cargoRepository.getReferenceById(id);
		updateData(entity, cargo);
		return cargoRepository.saveAndFlush(entity);
	}
	
	@Transactional
	public void delete(Long id) {
		cargoRepository.deleteById(id);
	}
	private void updateData(Cargo entity, Cargo obj) {
		entity.setNome(obj.getNome());
		entity.setDepartmento(obj.getDepartmento());
	}
	

}

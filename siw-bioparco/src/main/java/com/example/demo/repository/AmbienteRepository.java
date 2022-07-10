package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Ambiente;

public interface AmbienteRepository extends CrudRepository<Ambiente, Long>{
	
	public boolean existsByNome(String nome);
}

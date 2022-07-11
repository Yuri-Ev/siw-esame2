package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Animale;

public interface AnimaleRepository extends CrudRepository<Animale, Long>{

	public boolean existsByNome(String nome);
	
}

package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Specie;
import com.example.demo.repository.SpecieRepository;

@Service
public class SpecieService {

	@Autowired
	SpecieRepository specieRepository;
	
	@Transactional
	public void save(Specie specie) {
		specieRepository.save(specie);
	}
	
	public Specie findById(Long id) {
		return specieRepository.findById(id).get();
	}
	
	public List<Specie> findAll(){
		List<Specie> species = new ArrayList<>();
		for (Specie s : specieRepository.findAll())
			species.add(s);
		return species;
	}
	
	public void deleteById(Long id) {
		specieRepository.deleteById(id);
	}
}

package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Ambiente;
import com.example.demo.repository.AmbienteRepository;

@Service
public class AmbienteService {

	@Autowired
	AmbienteRepository ambienteRepository;
	
	@Transactional
	public void save(Ambiente ambiente) {
		ambienteRepository.save(ambiente);
	}
	
	public Ambiente findById(Long id) {
		return ambienteRepository.findById(id).get();
	}
	
	public List<Ambiente> findAll(){
		List<Ambiente> ambienti = new ArrayList<>();
		for (Ambiente a : ambienteRepository.findAll())
			ambienti.add(a);
		return ambienti;
	}
}

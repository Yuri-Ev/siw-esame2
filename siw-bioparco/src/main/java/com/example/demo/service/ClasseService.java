package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Classe;
import com.example.demo.repository.ClasseRepository;

@Service
public class ClasseService {

	@Autowired
	ClasseRepository classeRepository;
	
	@Transactional
	public void save(Classe classe) {
		classeRepository.save(classe);
	}
	
	public Classe findById(Long id) {
		return classeRepository.findById(id).get();
	}
	
	public List<Classe> findAll(){
		List<Classe> classes = new ArrayList<>();
		for (Classe c : classeRepository.findAll())
			classes.add(c);
		return classes;
	}
	
	public void deleteById(Long id) {
		classeRepository.deleteById(id);
	}
}

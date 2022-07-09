package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Animale;
import com.example.demo.repository.AnimaleRepository;

@Service
public class AnimaleService {

	@Autowired
	AnimaleRepository animaleRepository;
	
	
	@Transactional
	public void save(Animale animale) {
		animaleRepository.save(animale);
	}
	
	public Animale findById(Long id) {
		return animaleRepository.findById(id).get();
	}
	
	public List<Animale> findAll(){
		List<Animale> animali = new ArrayList<>();
		for (Animale a : animaleRepository.findAll())
			animali.add(a);
		return animali;
	}
	
	public void deleteById(Long id) {
		animaleRepository.deleteById(id);
	}
}

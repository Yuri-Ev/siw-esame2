package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Addestratore;
import com.example.demo.repository.AddestratoreRepository;

@Service
public class AddestratoreService {

	@Autowired
	AddestratoreRepository addestratoreRepository;
	
	@Transactional
	public void save(Addestratore addestratore) {
		addestratoreRepository.save(addestratore);
	}
	
	public Addestratore findById(Long id) {
		return addestratoreRepository.findById(id).get();
	}
	
	public List<Addestratore> findAll(){
		List<Addestratore> addestratori = new ArrayList<>();
		for (Addestratore a : addestratoreRepository.findAll())
			addestratori.add(a);
		return addestratori;
	}
}

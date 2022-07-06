package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.AmbienteRepository;

@Service
public class AmbienteService {

	@Autowired
	AmbienteRepository ambienteRepository;
}

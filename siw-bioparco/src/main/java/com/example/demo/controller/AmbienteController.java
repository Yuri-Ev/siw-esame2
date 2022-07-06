package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demo.service.AmbienteService;

@Controller
public class AmbienteController {

	@Autowired
	AmbienteService ambienteService;
}

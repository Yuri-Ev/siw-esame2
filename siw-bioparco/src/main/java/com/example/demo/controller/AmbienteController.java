package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Ambiente;
import com.example.demo.service.AmbienteService;

@Controller
public class AmbienteController {

	@Autowired
	AmbienteService ambienteService;


	@GetMapping("/admin/ambiente")
	public String getFormAmbiente(Model model){
		model.addAttribute("ambiente", new Ambiente());
		return "ambienteForm.html";
	}

	@PostMapping("/ambiente")
	public String addAmbiente(@Valid @ModelAttribute("ambiente") Ambiente ambiente,BindingResult bindingResult, Model model) {
		if(!bindingResult.hasErrors()) {
			ambienteService.save(ambiente);
			model.addAttribute("ambiente",ambiente);
			return "ambiente.html";
		}
		return "ambienteForm.html";
	}

	@GetMapping("/ambiente/{id}")
	public String getAmbiente(@PathVariable("id") Long id, Model model) {
		Ambiente ambiente = ambienteService.findById(id);
		model.addAttribute("ambiente",ambiente);
		return "ambiente.html";
	}

	@GetMapping("/ambienti")
	public String getListaAmbienti (Model model) {
		List<Ambiente> ambienti = ambienteService.findAll();
		model.addAttribute("ambienti",ambienti);
		return "ambienti.html";
	}
	
	@GetMapping("/admin/toDeleteAmbiente/{id}")
	public String toDeleteAmbiente(@PathVariable("id") Long id, Model model) {
		model.addAttribute("ambiente",ambienteService.findById(id));
		return "riepilogoToDeleteAmbiente.html";
	}


	@GetMapping("/admin/deleteAmbiente/{id}")
	public String deleteAmbiente(@PathVariable("id") Long id, Model model) {
		ambienteService.deleteById(id);
		model.addAttribute("ambienti", ambienteService.findAll());
		return "ambienti.html";
	}
}

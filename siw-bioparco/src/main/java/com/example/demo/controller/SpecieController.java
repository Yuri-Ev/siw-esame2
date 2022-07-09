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

import com.example.demo.model.Specie;
import com.example.demo.service.SpecieService;

@Controller
public class SpecieController {

	@Autowired
	SpecieService specieService;
	
	@GetMapping("/admin/specie")
	public String getFormSpecie(Model model){
		model.addAttribute("specie", new Specie());
		return "specieForm.html";
	}
	
	@PostMapping("/specie")
	public String addSpecie(@Valid @ModelAttribute("specie") Specie specie,BindingResult bindingResult, Model model) {
		if(!bindingResult.hasErrors()) {
			specieService.save(specie);
			model.addAttribute("specie",specie);
			return "specie.html";
		}
		return "addestratoreForm.html";
	}

	@GetMapping("/specie/{id}")
	public String getSpecie(@PathVariable("id") Long id, Model model) {
		Specie specie = specieService.findById(id);
		model.addAttribute("specie",specie);
		return "specie.html";
	}

	@GetMapping("/species")
	public String getListaSpecie (Model model) {
		List<Specie> species = specieService.findAll();
		model.addAttribute("species",species);
		return "species.html";
	}
	
}

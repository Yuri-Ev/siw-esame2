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

import com.example.demo.model.Animale;
import com.example.demo.service.AmbienteService;
import com.example.demo.service.AnimaleService;
import com.example.demo.service.SpecieService;

@Controller
public class AnimaleController {

	@Autowired
	AnimaleService animaleService;

	@Autowired
	SpecieService specieService;
	
	@Autowired
	AmbienteService ambienteService;

	@GetMapping("/")
	public String home() {
		return "landingPage.html";
	}

	
	@GetMapping("/admin/animale")
	public String getFormAnimale(Model model){
		model.addAttribute("animale", new Animale());
		model.addAttribute("species",specieService.findAll());
		model.addAttribute("ambienti",ambienteService.findAll());
		return "animaleForm.html";
	}
	
	@PostMapping("/animale")
	public String addAnimale(@Valid @ModelAttribute("animale") Animale animale,BindingResult bindingResult, Model model) {
		if(!bindingResult.hasErrors()) {
			animaleService.save(animale);
			model.addAttribute("animale",animale);
			return "animale.html";
		}
		return "animaleForm.html";
	}

	@GetMapping("/animale/{id}")
	public String getAnimale(@PathVariable("id") Long id, Model model) {
		Animale animale = animaleService.findById(id);
		model.addAttribute("animale",animale);
		return "animale.html";
	}

	@GetMapping("/animali")
	public String getListaAnimali (Model model) {
		List<Animale> animali = animaleService.findAll();
		model.addAttribute("animali",animali);
		return "animali.html";
	}

	@GetMapping("/admin/toDeleteAnimale/{id}")
	public String toDeleteAnimale(@PathVariable("id") Long id, Model model) {
		model.addAttribute("animale",animaleService.findById(id));
		return "riepilogoToDeleteAnimale.html";
	}


	@GetMapping("/admin/deleteAnimale/{id}")
	public String deleteAnimale(@PathVariable("id") Long id, Model model) {
		animaleService.deleteById(id);
		model.addAttribute("animali", animaleService.findAll());
		return "animali.html";
	}
}

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
import com.example.demo.service.AnimaleService;

@Controller
public class AnimaleController {

	@Autowired
	AnimaleService animaleService;
	
	
	
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
	
	
}

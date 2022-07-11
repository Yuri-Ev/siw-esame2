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

import com.example.demo.model.Classe;
import com.example.demo.service.ClasseService;

@Controller
public class ClasseController {

	@Autowired
	ClasseService classeService;

	@GetMapping("/admin/classe")
	public String getFormClasse(Model model){
		model.addAttribute("classe", new Classe());
		return "classeForm.html";
	}

	@PostMapping("/classe")
	public String addClasse(@Valid @ModelAttribute("classe") Classe classe,BindingResult bindingResult, Model model) {
		if(!bindingResult.hasErrors()) {
			classeService.save(classe);
			model.addAttribute("classe",classe);
			return "classe.html";
		}
		return "classeForm.html";
	}

	@GetMapping("/classe/{id}")
	public String getClasse(@PathVariable("id") Long id, Model model) {
		Classe classe = classeService.findById(id);
		model.addAttribute("classe",classe);
		return "classe.html";
	}

	@GetMapping("/classes")
	public String getListaClassi (Model model) {
		List<Classe> classes = classeService.findAll();
		model.addAttribute("classes",classes);
		return "classes.html";
	}

}

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

import com.example.demo.model.Addestratore;
import com.example.demo.service.AddestratoreService;

@Controller
public class AddestratoreController {

	@Autowired
	AddestratoreService addestratoreService;
	
	@GetMapping("/admin/addestratore")
	public String getFormAddestratore(Model model){
		model.addAttribute("addestratore", new Addestratore());
		return "addestratoreForm.html";
	}
	
	@PostMapping("/addestratore")
	public String addAddestratore(@Valid @ModelAttribute("addestratore") Addestratore addestratore,BindingResult bindingResult, Model model) {
		if(!bindingResult.hasErrors()) {
			addestratoreService.save(addestratore);
			model.addAttribute("addestratore",addestratore);
			return "addestratore.html";
		}
		return "addestratoreForm.html";
	}

	@GetMapping("/addestratore/{id}")
	public String getAddestratore(@PathVariable("id") Long id, Model model) {
		Addestratore addestratore = addestratoreService.findById(id);
		model.addAttribute("addestratore",addestratore);
		return "addestratore.html";
	}

	@GetMapping("/addestratori")
	public String getListaAddestratori (Model model) {
		List<Addestratore> addestratori = addestratoreService.findAll();
		model.addAttribute("addestratori",addestratori);
		return "addestratori.html";
	}
	
	@GetMapping("/admin/toDeleteAddestratore/{id}")
	public String toDeleteAddestratore(@PathVariable("id") Long id, Model model) {
		model.addAttribute("addestratore",addestratoreService.findById(id));
		return "riepilogoToDeleteAddestratore.html";
	}


	@GetMapping("/admin/deleteAddestratore/{id}")
	public String deleteAddestratore(@PathVariable("id") Long id, Model model) {
		addestratoreService.deleteById(id);
		model.addAttribute("addestratori", addestratoreService.findAll());
		return "addestratori.html";
	}
}

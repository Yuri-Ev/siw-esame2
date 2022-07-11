package com.example.demo.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Animale;
import com.example.demo.service.AmbienteService;
import com.example.demo.service.AnimaleService;
import com.example.demo.service.ClasseService;
import com.example.demo.validator.AnimaleValidator;

@Controller
public class AnimaleController {

	@Autowired
	AnimaleService animaleService;

	@Autowired
	ClasseService classeService;

	@Autowired
	AmbienteService ambienteService;

	@Autowired
	AnimaleValidator validator;


	@GetMapping("/admin/animale")
	public String getFormAnimale(Model model){
		model.addAttribute("animale", new Animale());
		model.addAttribute("classes",classeService.findAll());
		model.addAttribute("ambienti",ambienteService.findAll());
		return "animaleForm.html";
	}

	@PostMapping("/animale")
	public String addAnimale(@Valid @ModelAttribute("animale") Animale animale,BindingResult bindingResult, Model model,@RequestParam("image") MultipartFile image) throws IOException {
		validator.validate(animale, bindingResult);
		if(!bindingResult.hasErrors()) {
			String fileName = animale.getNome() + ".png";
	        animale.setPhoto(fileName);         
	        animaleService.save(animale);	 
	        String uploadDir = "animali-photos/";
	        FileUploadUtil.saveFile(uploadDir, fileName, image);
			model.addAttribute("animale",animale);
			return "animale.html";
		}
		model.addAttribute("classes",classeService.findAll());
		model.addAttribute("ambienti",ambienteService.findAll());
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
	
	@GetMapping("/admin/animale/edit/{id}")
	public String toEditAnimale(@PathVariable("id") Long id, Model model){
		model.addAttribute("animale",animaleService.findById(id));
		model.addAttribute("classes",classeService.findAll());
		model.addAttribute("ambienti",ambienteService.findAll());
		return "animaleEditForm.html";
	}


	@PostMapping("/admin/animale/edit/{id}")
	public String editAmbiente(@Valid @ModelAttribute("animale") Animale animale,BindingResult bindingResult, Model model) {
		if(!bindingResult.hasErrors()) {
			animaleService.save(animale);
			model.addAttribute("animale",animale);
			return "animale.html";
		}
		model.addAttribute("classes",classeService.findAll());
		model.addAttribute("ambienti",ambienteService.findAll());
		return "animaleEditForm.html";
	}
}

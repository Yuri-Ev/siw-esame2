package com.example.demo.controller;

import java.io.File;
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

import com.example.demo.configuration.FileUploadUtil;
import com.example.demo.model.Ambiente;
import com.example.demo.service.AmbienteService;
import com.example.demo.validator.AmbienteValidator;

@Controller
public class AmbienteController {

	@Autowired
	AmbienteService ambienteService;

	@Autowired
	AmbienteValidator validator;


	@GetMapping("/admin/ambiente")
	public String getFormAmbiente(Model model){
		model.addAttribute("ambiente", new Ambiente());
		return "ambienteForm.html";
	}

	@PostMapping("/ambiente")
	public String addAmbiente(@Valid @ModelAttribute("ambiente") Ambiente ambiente,BindingResult bindingResult, Model model,@RequestParam("image") MultipartFile image) throws IOException {
		validator.validate(ambiente, bindingResult);
		if(!bindingResult.hasErrors()) {
			String fileName = ambiente.getNome() + ".png";
			ambiente.setPhoto(fileName);         
			ambienteService.save(ambiente);	 
			String uploadDir = "src/main/resources/static/ambienti-photos/";
			FileUploadUtil.saveFile(uploadDir, fileName, image);
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
		String dirToPic = "src/main/resources/static/ambienti-photos/";
		File picToDestroy = new File(dirToPic + ambienteService.findById(id).getPhoto());
		picToDestroy.delete();
		ambienteService.deleteById(id);
		model.addAttribute("ambienti", ambienteService.findAll());
		return "ambienti.html";
	}

	@GetMapping("/admin/ambiente/edit/{id}")
	public String toEditAmbiente(@PathVariable("id") Long id, Model model){
		model.addAttribute("ambiente", ambienteService.findById(id));
		return "ambienteEditForm.html";
	}


	@PostMapping("/admin/ambiente/edit/{id}")
	public String editAmbiente(@Valid @ModelAttribute("ambiente") Ambiente ambiente,BindingResult bindingResult, Model model,@RequestParam("image") MultipartFile image) throws IOException  {
		if(!bindingResult.hasErrors()) {
			String dirToPic = "src/main/resources/static/ambienti-photos/";
			File picToDestroy = new File(dirToPic + ambienteService.findById(ambiente.getId()).getPhoto());
			picToDestroy.delete();
			String fileName = ambiente.getNome() + ".png";
			ambiente.setPhoto(fileName);         
			ambienteService.save(ambiente);	 
			FileUploadUtil.saveFile(dirToPic, fileName, image);
			model.addAttribute("ambiente",ambiente);
			return "ambiente.html";
		}
		model.addAttribute("ambiente",ambiente);
		return "ambienteEditForm.html";
	}
}

package com.example.demo.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.model.Ambiente;
import com.example.demo.service.AmbienteService;

@Component
public class AmbienteValidator implements  Validator{

	@Autowired
	AmbienteService ambienteService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Ambiente.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (ambienteService.alreadyExists((Ambiente) target))
			errors.reject("ambiente.duplicato");
	}

}

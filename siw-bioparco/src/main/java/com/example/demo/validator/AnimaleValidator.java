package com.example.demo.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.model.Animale;
import com.example.demo.service.AnimaleService;

@Component
public class AnimaleValidator implements Validator{

	@Autowired
	AnimaleService animaleService;
	
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Animale.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (animaleService.alreadyExists((Animale) target))
			errors.reject("animale.duplicato");
	}

}

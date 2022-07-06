package com.example.demo.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.model.Ambiente;

@Component
public class AmbienteValidator implements  Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Ambiente.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
	}

}

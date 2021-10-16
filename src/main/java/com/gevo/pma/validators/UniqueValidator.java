package com.gevo.pma.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.gevo.pma.dao.IEmployeeRepository;

public class UniqueValidator implements ConstraintValidator<UniqueValue, String> {

	
	@Autowired
	IEmployeeRepository empRepo;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(empRepo.findByEmail(value) != null)
			return false;
		else
			return true;
	}
	
	 
	
}

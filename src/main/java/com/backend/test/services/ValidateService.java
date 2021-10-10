/**
 * 
 */
package com.backend.test.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;

import org.springframework.stereotype.Service;

@Service
public class ValidateService {
	
	public <T> List<String> validate (T input) {
	    List<String> errors = new ArrayList<>();
	    Set<ConstraintViolation<T>> violations = Validation.buildDefaultValidatorFactory().getValidator().validate(input);
	    if (violations.size() > 0) {
	        for (ConstraintViolation<T> violation : violations) {
	            errors.add(violation.getPropertyPath() + " " + violation.getMessage());
	        }
	    }
	    return errors;
	}
	
}

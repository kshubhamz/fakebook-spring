package com.kshz.fakebookserver.validator;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.kshz.fakebookserver.annotation.ConnectionAction;

public class ConnectionActionValidator implements ConstraintValidator<ConnectionAction, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return List.of("add", "remove").contains(value.toLowerCase());
	}

}

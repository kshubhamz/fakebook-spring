package com.kshz.fakebookserver.validator;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.kshz.fakebookserver.annotation.ConnectionEntity;

public class ConnectionEntityValidator implements ConstraintValidator<ConnectionEntity, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return List.of("follower", "following").contains(value.toLowerCase());
	}

}

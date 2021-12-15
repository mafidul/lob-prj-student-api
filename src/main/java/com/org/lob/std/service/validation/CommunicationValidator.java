package com.org.lob.std.service.validation;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommunicationValidator implements ConstraintValidator<ValidCommunication, String> {
	private static final Logger LOGGER = LoggerFactory.getLogger(CommunicationValidator.class);

	private List<String> commPreferences;

	@Override
	public void initialize(ValidCommunication constraintAnnotation) {
		this.commPreferences = Arrays.asList(constraintAnnotation.values());
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		LOGGER.info("Student Model Input {}:" + value);

		return commPreferences.contains(value);
	}
}

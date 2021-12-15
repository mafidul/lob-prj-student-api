package com.org.lob.std.service.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;

public class ValidEmailIdValidator implements ConstraintValidator<ValidEmailId, Object> {

	private String field;
	private String matchField;

	@Override
	public void initialize(ValidEmailId validEmailId) {
		this.field = validEmailId.field();
		this.matchField = validEmailId.fieldMatch();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		Object fieldValue = new BeanWrapperImpl(value).getPropertyValue(field);
		Object fieldMatchValue = new BeanWrapperImpl(value).getPropertyValue(matchField);

		if (fieldValue != null) {
			return fieldValue.equals(fieldMatchValue);
		} else if (fieldMatchValue != null) {
			return fieldMatchValue.equals(fieldValue);
		} else {
			return true;
		}
	}
}
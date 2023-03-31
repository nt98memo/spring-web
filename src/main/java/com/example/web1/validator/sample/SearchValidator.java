package com.example.web1.validator.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.example.common.search.BaseSearchEntity;
import com.example.common.service.demo.SampleService;
import com.example.common.validator.BaseSearchValidator;

import jakarta.persistence.EntityManager;

@Component
public class SearchValidator extends BaseSearchValidator {

    @Autowired
    SampleService sampleService;

	public SearchValidator() {
	}

	public void init(EntityManager entityManager) {
		super.init(entityManager);
		this.sampleService.init(this.entityManager);
	}
	
	@Override
	public void validate(Object target, Errors errors) {

		BaseSearchEntity entity = (BaseSearchEntity)target;

	}

}

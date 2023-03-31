package com.example.web1.validator.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.example.common.entity.demo.Sample;
import com.example.common.service.demo.SampleService;
import com.example.common.util.LogUtil;
import com.example.common.validator.BaseUpdateValidator;

import jakarta.persistence.EntityManager;

@Component
public class UpdateValidator extends BaseUpdateValidator {

    @Autowired
    SampleService sampleService;

    @Autowired
    LogUtil logUtil;

	public UpdateValidator() {
	}

	public void init(EntityManager entityManager) {
		super.init(entityManager);
		this.sampleService.init(this.entityManager);
	}
	
	@Override
	public void validate(Object target, Errors errors) {

		try {

			Sample entity = (Sample)target;

			Sample sample = this.sampleService.findByIdForUpdate(entity.getId());

			if (entity.getSampleParent().getId() == null) {
				errors.rejectValue("sampleParent.id", "jakarta.validation.constraints.NotNull.message", "may not be null");
			}

		} catch (Exception e) {
			logUtil.errorUrl();
			logUtil.writeException(e);
			throw new RuntimeException(e);
		}

	}

}

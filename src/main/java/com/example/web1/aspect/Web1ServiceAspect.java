package com.example.web1.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.example.common.aspect.ServiceAspect;

@Aspect
@Component
public class Web1ServiceAspect extends ServiceAspect {

	@Autowired
	protected WebApplicationContext context;

	public Web1ServiceAspect() {
	}

	@Override
	@Before("execution(public void com.example.common.service.BaseService.insert(*))")
	public void insertBefore(JoinPoint joinPoint) {
		super.insertBefore(joinPoint);
	}

	@Override
	@AfterReturning("execution(public void com.example.common.service.BaseService.insert(*))")
	public void insertAfter(JoinPoint joinPoint) {
		super.insertAfter(joinPoint);
	}

	@Override
	@Before("execution(public void com.example.common.service.BaseService.update(*))")
	public void updateBefore(JoinPoint joinPoint) {
		super.updateBefore(joinPoint);
	}

	@Override
	@AfterReturning("execution(public void com.example.common.service.BaseService.update(*))")
	public void updateAfter(JoinPoint joinPoint) {
		super.updateAfter(joinPoint);
	}

	@Override
	@Before("execution(public void com.example.common.service.BaseService.delete(*))")
	public void deleteBefore(JoinPoint joinPoint) {
		super.deleteBefore(joinPoint);
	}

	@Override
	@AfterReturning("execution(public void com.example.common.service.BaseService.delete(*))")
	public void deleteAfter(JoinPoint joinPoint) {
		super.deleteAfter(joinPoint);
	}

	@Override
	@Before("execution(public void com.example.common.service.BaseService.find(*))")
	public void findBefore(JoinPoint joinPoint) {
		super.findBefore(joinPoint);
	}

	@Override
	@AfterReturning("execution(public void com.example.common.service.BaseService.find(*))")
	public void findAfter(JoinPoint joinPoint) {
		super.findAfter(joinPoint);
	}

}

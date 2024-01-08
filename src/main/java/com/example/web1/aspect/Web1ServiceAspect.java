package com.example.web1.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.example.common.aspect.ServiceAspect;

@Aspect
@Component
public class Web1ServiceAspect extends ServiceAspect {

	public Web1ServiceAspect() {
	}

}

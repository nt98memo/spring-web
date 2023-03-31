package com.example.web1.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.example.common.advice.CommonRestControllerAdvice;
import com.example.common.controller.RestBaseController;
import com.example.common.msg.ErrorMessage;
import com.example.common.util.LogUtil;
import com.example.common.util.WebExceptionUtil;

@RestControllerAdvice(assignableTypes = RestBaseController.class)
@Component
public class Web1RestControllerAdvice extends CommonRestControllerAdvice {

	public Web1RestControllerAdvice() {
	}

	@Autowired
	protected MessageSource messageSource;

	@Autowired
	protected LogUtil logUtil;

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({ Exception.class })
	@ResponseBody
	public ErrorMessage exceptionHandler(Exception e, WebRequest request) {
		return WebExceptionUtil.exceptionHandler(logUtil, e, request, this.messageSource, null);
	}

}

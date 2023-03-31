package com.example.web1.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.example.common.advice.CommonHtmlControllerAdvice;
import com.example.common.controller.HtmlBaseController;
import com.example.common.exception.DemoException;
import com.example.common.util.DeviceUtil;
import com.example.common.util.LogUtil;

@ControllerAdvice(assignableTypes = HtmlBaseController.class)
@Component
public class Web1HtmlControllerAdvice extends CommonHtmlControllerAdvice {

	public Web1HtmlControllerAdvice() {
	}

	@Autowired
	protected LogUtil logUtil;

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({ Exception.class })
	public String handleSystemException(Exception e, WebRequest request) {

		if (!(e instanceof DemoException)) {
			logUtil.errorUrl();
			logUtil.writeException(e);
		}
		
		return DeviceUtil.getTplName(this.appName, "index", "error");
		
	}

}

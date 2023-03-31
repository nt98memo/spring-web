package com.example.web1.component;

import org.springframework.stereotype.Component;

import com.example.common.component.BaseRequestListener;

import jakarta.servlet.ServletRequestEvent;

@Component
public class RequestListener extends BaseRequestListener {

	@Override
    public void requestDestroyed (ServletRequestEvent sre) {
		super.requestDestroyed(sre);
    }

	@Override
    public void requestInitialized (ServletRequestEvent sre) {
		super.requestInitialized(sre);
    }

}

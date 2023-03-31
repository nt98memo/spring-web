package com.example.web1;

import java.io.IOException;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.example.common.CommonApplication;

@SpringBootApplication
@EnableAspectJAutoProxy
@ComponentScan("com.example")
public class Web1Application extends CommonApplication {

	public static void main(String[] args) {

		try {
			start(args, Web1Application.class, "application,web1");
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

}

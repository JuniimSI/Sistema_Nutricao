package com.example.nutricao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class SistemaNutricaoApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SistemaNutricaoApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SistemaNutricaoApplication.class, args);
	}
}




package com.example.nutricao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class NutricaoController {
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	
	@GetMapping("/registration")
	public String registration() {
		return "registration";
	}
}

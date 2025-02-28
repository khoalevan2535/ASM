package com.poly.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
	@GetMapping("/Login")
	public String login() {
		return "/Login.html";
	}
	@PostMapping("/Login")
	public String hasLogin() {
		return "/Home.html";
	}
}

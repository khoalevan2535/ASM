package com.poly.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
	@GetMapping("/Register")
	public String register() {
		return "/Register.html";
	}

	@PostMapping("/Register")
	public String hasRegister() {

		return "/user/Account.html";
	}
}

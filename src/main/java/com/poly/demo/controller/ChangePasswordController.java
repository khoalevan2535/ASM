package com.poly.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChangePasswordController {
	@GetMapping("/ChangePassword")
	public String changePassword() {
		return "/ChangePassword.html";
	}
	@PostMapping("/ChangePassword")
	public String hasChangePassword() {
		return "/Home.html";
	}

}

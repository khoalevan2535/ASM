package com.poly.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ForgotPasswordController {
	@GetMapping("/ForgotPassword")
	public String forgotPassword() {
		return "/ForgotPassword.html";
	}

	@PostMapping("ForgotPassword")
	public String hasForgotPassword() {

		return "/EnterVerificationCode.html";
	}

	@GetMapping("/EnterVerificationCode")
	public String enterVerificationCode() {
		return "/EnterVerificationCode.html";
	}

	@PostMapping("/EnterVerificationCode")
	public String hasEnterVerificationCode() {

		return "/ChangePassword.html";
	}
}

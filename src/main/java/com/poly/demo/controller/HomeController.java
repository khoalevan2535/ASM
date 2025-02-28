package com.poly.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/Home")
	public String login() {
		return "/Home.html";
	}
}

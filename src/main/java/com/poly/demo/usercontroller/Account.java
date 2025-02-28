package com.poly.demo.usercontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class Account {
	@GetMapping("/User/Account")
	public String acount() {
		return "/user/Account.html";
	}
	@PostMapping("/User/Account")
	public String hasUserAccount() {

		return "/user/Account.html";
	}

}

package com.poly.demo.admincontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class adminController {
	@GetMapping("/Admin/Index")
    public String adminIndex(){
        return"/admin/Index.html";
    }
	@GetMapping("/Admin/Home")
	public String adminHome() {
		return "/admin/Home.html";
	}


}

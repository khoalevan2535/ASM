package com.poly.demo.admincontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class UserController {
	 @GetMapping("/Admin/User")
	    public String userShow(){
	        return"/admin/User.html";
	    }
}

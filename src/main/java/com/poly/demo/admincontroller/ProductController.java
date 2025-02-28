package com.poly.demo.admincontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class ProductController {
	@GetMapping("/Admin/Product")
    public String productShow(){
        return"/admin/Product.html";
    }
}

package com.poly.demo.admincontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class PomotionController {
    @GetMapping("/Admin/Pomotion")
    public String promotion(){
        return "/admin/Promotion";
    }
}

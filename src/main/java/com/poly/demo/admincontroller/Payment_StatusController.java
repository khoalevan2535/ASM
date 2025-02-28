package com.poly.demo.admincontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.poly.demo.beans.Payment_statusBean;
import com.poly.demo.enities.Payment_statusEntity;
import com.poly.demo.jpas.Payment_StatusJPA;
import com.poly.demo.services.Payment_StatusService;


import jakarta.validation.Valid;


@Controller
public class Payment_StatusController {

    @Autowired
    Payment_StatusService payment_StatusService;
    
    @Autowired
    Payment_StatusJPA payment_StatusJPA;
    
    @GetMapping("/Admin/Payment_status")
    public String showPayment_StatusPage(
            @RequestParam(required = false) String search, 
            @RequestParam(required = false, defaultValue = "asc") String sort, 
            Model model) {

        List<Payment_statusEntity> payment_status;

        if (search != null && !search.isEmpty()) {
        	payment_status = payment_StatusService.getPayment_Status(search, sort);
            if (payment_status.isEmpty()) {
                model.addAttribute("message", "Không tìm thấy danh mục"); 
            }
        } else {
        	payment_status = payment_StatusService.getPayment_Status(null, sort);
        }

        model.addAttribute("listPayment_status", payment_status);
        model.addAttribute("Payment_statusBean", new Payment_statusBean());
        return "/admin/Payment_Status.html";
    }
    
    @GetMapping("/Admin/Payment_status/Edit")
    public String editPayment_Status(@RequestParam("id") Integer id, Model model) {
        if (id == null) {
            model.addAttribute("error", "Invalid category ID.");
            return "/admin/Payment_Status.html"; 
        }

        Optional<Payment_statusEntity> Payment_statusEntityOpt = payment_StatusService.getPaymentStatusById(id);
        if (Payment_statusEntityOpt.isEmpty()) {
            model.addAttribute("error", "Category not found.");
            return "/admin/Payment_Status.html"; 
        }

        Payment_statusEntity Payment_statusEntity = Payment_statusEntityOpt.get();
        Payment_statusBean Payment_statusBean = new Payment_statusBean();
        Payment_statusBean.setId(Payment_statusEntity.getId());
        Payment_statusBean.setName(Payment_statusEntity.getName());

        model.addAttribute("Payment_statusBean", Payment_statusBean);
        model.addAttribute("listPayment_status", payment_StatusService.getAllPaymentStatus());

        return "/admin/Payment_Status.html";
    }



    @PostMapping("/Admin/Payment_status/Save")
    public String savePayment_StatusBean(@Valid @ModelAttribute("Payment_statusBean") Payment_statusBean payment_statusBean, 
                                         Errors errors, Model model, RedirectAttributes redirectAttributes) {
        
    	  model.addAttribute("listPayment_status", payment_StatusService.getAllPaymentStatus());
    	  
        if (errors.hasErrors()) {
            model.addAttribute("error", "Validation errors occurred");
            model.addAttribute("Payment_statusBean", payment_statusBean);
            model.addAttribute("listPayment_status", payment_StatusService.getAllPaymentStatus());
            return "/admin/Payment_Status.html";
        }

        String response;
        if (payment_statusBean.getId() == null) {
            response = payment_StatusService.insertPaymentStatus(payment_statusBean);
            if (response != null) {
                model.addAttribute("error", response); 
                return "/admin/Payment_Status.html";
            }
            redirectAttributes.addFlashAttribute("successMessage", "Thêm trạng thái thanh toán thành công!");
        } else { 
            response = payment_StatusService.updatePaymentStatus(payment_statusBean);
            if (response != null) {
                model.addAttribute("error", response); 
                return "/admin/Payment_Status.html";
            }
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật trạng thái thanh toán thành công!");
        }

        return "redirect:/Admin/Payment_status";
    }


    @PostMapping("/Admin/Payment_status/Delete")
    public String deletePayment_Status(@RequestParam("id") int id, RedirectAttributes redirectAttributes) {
        boolean deleted = payment_StatusService.deletePaymentStatus(id);
        if (!deleted) {
            redirectAttributes.addFlashAttribute("error", "Xóa trạng thái thanh toán thất bại!");
        } else {
            redirectAttributes.addFlashAttribute("successMessage", "Xóa trạng thái thanh toán thành công!");
        }
        return "redirect:/Admin/Payment_status";
    }
    
}



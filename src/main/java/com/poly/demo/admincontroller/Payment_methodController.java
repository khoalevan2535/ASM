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

import com.poly.demo.beans.Payment_methodBean;
import com.poly.demo.enities.Payment_methodEntity;
import com.poly.demo.jpas.Payment_MethodJPA;
import com.poly.demo.services.Payment_methodService;

import jakarta.validation.Valid;


@Controller
public class Payment_methodController {

    @Autowired
    Payment_methodService payment_methodService;
    
    @Autowired
    Payment_MethodJPA payment_MethodJPA;
    
    @GetMapping("/Admin/Payment_method")
    public String showPayment_methodPage(
            @RequestParam(required = false) String search, 
            @RequestParam(required = false, defaultValue = "asc") String sort, 
            Model model) {

        List<Payment_methodEntity> payment_method;

        if (search != null && !search.isEmpty()) {
        	payment_method = payment_methodService.getPayment_methodEntities(search, sort);
            if (payment_method.isEmpty()) {
                model.addAttribute("message", "Không tìm thấy danh mục"); 
            }
        } else {
        	payment_method = payment_methodService.getPayment_methodEntities(null, sort);
        }

        model.addAttribute("listPayment_method", payment_method);
        model.addAttribute("Payment_methodBean", new Payment_methodBean());
        return "/admin/Payment_method.html";
    }
    
    @GetMapping("/Admin/Payment_method/Edit")
    public String editPayment_Method(@RequestParam("id") Integer id, Model model) {
        if (id == null) {
            model.addAttribute("error", "Invalid Payment_Method ID.");
            return "redirect:/Admin/Payment_method";
        }

        Optional<Payment_methodEntity> Payment_methodEntityOpt = payment_methodService.getPaymentMethodById(id);
        if (Payment_methodEntityOpt.isEmpty()) {
            model.addAttribute("error", "Payment_Method not found.");
            return "redirect:/Admin/Payment_method";
        }

        Payment_methodEntity Payment_methodEntity = Payment_methodEntityOpt.get();
        Payment_methodBean Payment_methodBean = new Payment_methodBean();
        Payment_methodBean.setId(Payment_methodEntity.getId());
        Payment_methodBean.setName(Payment_methodEntity.getName());
        Payment_methodBean.setDescription(Payment_methodEntity.getDescription());

        model.addAttribute("Payment_methodBean", Payment_methodBean);
        model.addAttribute("listPayment_methodService", payment_methodService.getAllPaymentMethods());

        return "/admin/Payment_method.html";
    }


    @PostMapping("/Admin/Payment_method/Save")
    public String savePayment_MethodBean(@Valid @ModelAttribute("Payment_methodBean") Payment_methodBean payment_methodBean, 
                                         Errors errors, Model model, RedirectAttributes redirectAttributes) {
        
        model.addAttribute("listPayment_method", payment_methodService.getAllPaymentMethods());

        if (errors.hasErrors()) {
            model.addAttribute("error", "Validation errors occurred");
            model.addAttribute("Payment_methodBean", payment_methodBean);
            return "/admin/Payment_method.html";
        }

        String response;
        if (payment_methodBean.getId() == null) {
            response = payment_methodService.insertPaymentMethod(payment_methodBean);
            if (response != null) {
                model.addAttribute("error", response); 
                model.addAttribute("Payment_methodBean", payment_methodBean); 
                return "/admin/Payment_method.html";
            }
            redirectAttributes.addFlashAttribute("successMessage", "Thêm phương thức thanh toán thành công!");
        } else { 
            response = payment_methodService.updatePaymentMethod(payment_methodBean);
            if (response != null) {
                model.addAttribute("error", response); 
                model.addAttribute("Payment_methodBean", payment_methodBean); 
                return "/admin/Payment_method.html";
            }
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật phương thức thanh toán thành công!");
        }

        return "redirect:/Admin/Payment_method";
    }
    
    @PostMapping("/Admin/Payment_method/Delete")
    public String deletePayment_Method(@RequestParam("id") int id, RedirectAttributes redirectAttributes) {
        boolean deleted = payment_methodService.deletePaymentMethod(id);
        if (!deleted) {
            redirectAttributes.addFlashAttribute("error", "Xóa phương thức thanh toán thất bại!");
        } else {
            redirectAttributes.addFlashAttribute("successMessage", "Xóa phương thức thanh toán thành công!");
        }
        return "redirect:/Admin/Payment_method";
    }
}



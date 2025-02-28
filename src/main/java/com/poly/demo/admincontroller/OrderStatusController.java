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

import com.poly.demo.beans.Order_statusBean;
import com.poly.demo.enities.Order_statusEntity;
import com.poly.demo.jpas.Order_StatusJPA;
import com.poly.demo.services.OrderStatusService;

import jakarta.validation.Valid;

@Controller
public class OrderStatusController {

    @Autowired
    private Order_StatusJPA orderStatusJPA;

    @Autowired
    private OrderStatusService orderStatusService;

    @ModelAttribute("orderStatus")
    public List<Order_statusEntity> getAllOrderStatus() {
        return orderStatusJPA.findAll();
    }

    @GetMapping("/Admin/OrderStatus")
    public String orderStatus(Model model, @RequestParam(value = "id", required = false) Integer id) {
        Order_statusBean orderStatusBean = new Order_statusBean();

        if (id != null) {
            Optional<Order_statusEntity> orderStatusOptional = orderStatusJPA.findById(id);
            if (orderStatusOptional.isPresent()) {
                Order_statusEntity entity = orderStatusOptional.get();
                orderStatusBean.setId(Optional.ofNullable(entity.getId()));
                orderStatusBean.setName(entity.getName());
            } else {
                model.addAttribute("error", "Không tìm thấy trạng thái đơn hàng với ID: " + id);
            }
        }

        model.addAttribute("orderStatusBean", orderStatusBean);
        return "admin/OrderStatus";
    }

    @PostMapping("/Admin/OrderStatus")
    public String addOrderStatus(@Valid @ModelAttribute("orderStatusBean") Order_statusBean orderStatusBean,
            Errors errors,
            @RequestParam(value = "id", required = false) Integer idFromForm,
            Model model, RedirectAttributes redirectAttributes) {

        System.out.println("Bean received: " + orderStatusBean); // Debug dữ liệu gửi lên
        if (errors.hasErrors()) {
            System.out.println("Validation errors: " + errors);
            model.addAttribute("orderStatusBean", orderStatusBean);
            model.addAttribute("error", "Vui lòng kiểm tra dữ liệu nhập vào");
            return "admin/OrderStatus";
        }

        if (idFromForm != null) {
            orderStatusBean.setId(Optional.of(idFromForm));
        }

        String result = orderStatusBean.getId().isPresent()
                ? orderStatusService.updateOrder(orderStatusBean)
                : orderStatusService.insertOrderStatus(orderStatusBean);

        if (result != null) {
            System.out.println("Service error: " + result);
            model.addAttribute("error", result);
            model.addAttribute("orderStatusBean", orderStatusBean);
            return "admin/OrderStatus";
        }

        redirectAttributes.addFlashAttribute("success", "Thao tác thành công!");
        return "redirect:/Admin/OrderStatus";
    }

    @PostMapping("/Admin/OrderStatus/Delete")
    public String deleteOrderStatus(@RequestParam("id") int id, RedirectAttributes redirectAttributes) {
        boolean deleted = orderStatusService.deleteOrderStatus(id);
        redirectAttributes.addFlashAttribute(deleted ? "success" : "serviceError",
                deleted ? "Xóa thành công!" : "Không thể xóa trạng thái đơn hàng");
        return "redirect:/Admin/OrderStatus";
    }
}
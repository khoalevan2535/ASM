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

import com.poly.demo.beans.ImageBean;
import com.poly.demo.enities.ImageEntity;
import com.poly.demo.enities.ProductEntity; // Thêm import
import com.poly.demo.services.ImageService1;
import com.poly.demo.services.ProductService; // Thêm import

import jakarta.validation.Valid;

@Controller
public class ImageController {

    @Autowired
    ImageService1 imageService;

    @Autowired
    ProductService productService; 

    @Autowired
    ImageBean imageBean;

    @GetMapping("/Admin/Images")
    public String showImagePage(Model model) {
        List<ImageEntity> images = imageService.getAllImages();
        List<ProductEntity> products = productService.getAllProducts();
        model.addAttribute("listImages", images);
        model.addAttribute("products", products); 
        model.addAttribute("imageBean", new ImageBean());
        return "/admin/Images.html";
    }

    @GetMapping("/Admin/Images/Edit")
    public String editImage(@RequestParam("id") Integer id, Model model) {
        if (id == null) {
            model.addAttribute("error", "Invalid image ID.");
            return "/admin/Images.html";
        }

        Optional<ImageEntity> imageEntityOpt = imageService.getImageById(id);
        if (imageEntityOpt.isEmpty()) {
            model.addAttribute("error", "Image not found.");
            return "/admin/Images.html";
        }

        ImageEntity imageEntity = imageEntityOpt.get();
        ImageBean imageBean = new ImageBean();
        imageBean.setId(imageEntity.getId());
        imageBean.setCurrentImage(imageEntity.getImage());
        imageBean.setProduct(imageEntity.getProduct());

        List<ProductEntity> products = productService.getAllProducts(); 
        model.addAttribute("imageBean", imageBean);
        model.addAttribute("listImages", imageService.getAllImages());
        model.addAttribute("products", products); 

        return "/admin/Images.html";
    }

    @PostMapping("/Admin/Images/Save")
    public String saveImage(@Valid @ModelAttribute("imageBean") ImageBean imageBean, 
                            Errors errors, Model model, RedirectAttributes redirectAttributes) {
	
        if (imageBean.getImageFile() == null || imageBean.getImageFile().isEmpty()) {
        	errors.rejectValue("imageFile", "image.empty", "Hình ảnh không được để trống");
            model.addAttribute("imageBean", imageBean);
            model.addAttribute("listImages", imageService.getAllImages());
            model.addAttribute("products", productService.getAllProducts());
            return "/admin/Images.html";
        }

        if (imageBean.getProduct() == null) {
            errors.rejectValue("product", "product.empty", "Vui lòng chọn sản phẩm");
            model.addAttribute("imageBean", imageBean);
            model.addAttribute("listImages", imageService.getAllImages());
            model.addAttribute("products", productService.getAllProducts());
            return "/admin/Images.html";
        }
        
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> {
                System.out.println(error.getDefaultMessage());
            });
            model.addAttribute("error", "Validation errors occurred");
            model.addAttribute("imageBean", imageBean);
            model.addAttribute("listImages", imageService.getAllImages());
            model.addAttribute("products", productService.getAllProducts());
            return "/admin/Images.html";
        }

        String response;
        if (imageBean.getId() == null) {
            response = imageService.insertImage(imageBean);
            if (response != null) {
                model.addAttribute("error", response); 
                return "/admin/Images.html";
            }
            redirectAttributes.addFlashAttribute("successMessage", "Thêm hình ảnh thành công!");
        } else { 
            response = imageService.updateImage(imageBean);
            if (response != null) {
                model.addAttribute("error", response); 
                return "/admin/Images.html";
            }
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật hình ảnh thành công!");
        }

        return "redirect:/Admin/Images";
    }

    @PostMapping("/Admin/Images/Delete")
    public String deleteImage(@RequestParam("id") int id, RedirectAttributes redirectAttributes) {
        boolean deleted = imageService.deleteImage(id);
        if (!deleted) {
            redirectAttributes.addFlashAttribute("error", "Xóa hình ảnh thất bại!");
        } else {
            redirectAttributes.addFlashAttribute("successMessage", "Xóa hình ảnh thành công!");
        }
        return "redirect:/Admin/Images";
    }
}
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

import com.poly.demo.beans.CategoryBean;
import com.poly.demo.enities.CategoryEntity;
import com.poly.demo.jpas.CategoryJPA;
import com.poly.demo.services.CategoryService;
import com.poly.demo.services.ImageService;

import jakarta.validation.Valid;


@Controller
public class CategoryController {

    @Autowired
    CategoryService categoryService;
    
    @Autowired
    CategoryJPA categoryJPA;

    @Autowired
    ImageService imageService;
    
    @GetMapping("/Admin/Category")
    public String showCategoryPage(
            @RequestParam(required = false) String search, 
            @RequestParam(required = false, defaultValue = "asc") String sort, 
            Model model) {

        List<CategoryEntity> categories;

        if (search != null && !search.isEmpty()) {
            categories = categoryService.getCategory(search, sort);
            if (categories.isEmpty()) {
                model.addAttribute("message", "Không tìm thấy danh mục"); 
            }
        } else {
            categories = categoryService.getCategory(null, sort);
        }

        model.addAttribute("listCategory", categories);
        model.addAttribute("categoryBean", new CategoryBean());
        return "/admin/Category.html";
    }

    @GetMapping("/Admin/Category/Edit")
    public String editCategory(@RequestParam("id") Integer id, Model model) {
        if (id == null) {
            model.addAttribute("error", "Invalid category ID.");
            return "redirect:/Admin/Category";
        }

        Optional<CategoryEntity> categoryEntityOpt = categoryService.getCategoryById(id);
        if (categoryEntityOpt.isEmpty()) {
            model.addAttribute("error", "Category not found.");
            return "redirect:/Admin/Category";
        }

        CategoryEntity categoryEntity = categoryEntityOpt.get();
        CategoryBean categoryBean = new CategoryBean();
        categoryBean.setId(categoryEntity.getId());
        categoryBean.setName(categoryEntity.getName());
        categoryBean.setCurrentImage(categoryEntity.getImage());

        model.addAttribute("categoryBean", categoryBean);
        model.addAttribute("listCategory", categoryService.getAllCategories());

        return "/admin/Category.html";
    }

    @PostMapping("/Admin/Category/Save")
    public String saveCategory(@Valid @ModelAttribute("categoryBean") CategoryBean categoryBean, 
                               Errors errors, Model model, RedirectAttributes redirectAttributes) {
        
        model.addAttribute("listCategory", categoryService.getAllCategories());

        // Kiểm tra nếu là edit và không có file mới được chọn, thì bỏ qua validation
        if (categoryBean.getId() != null && (categoryBean.getImage() == null || categoryBean.getImage().isEmpty())) {
            // Bỏ qua validation cho trường image
        } else if (categoryBean.getImage() == null || categoryBean.getImage().isEmpty()) {
            errors.rejectValue("image", "image.empty", "Hình ảnh không được để trống");
        }

        if (errors.hasErrors()) {
            model.addAttribute("error", "Thêm loại sản phẩm thất bại!");
            model.addAttribute("categoryBean", categoryBean);
            return "/admin/Category.html";
        }

        String response;
        if (categoryBean.getId() == null) {
            response = categoryService.insertCategory(categoryBean);
            if (response != null) {
                model.addAttribute("error", response); 
                return "/admin/Category.html";
            }
            redirectAttributes.addFlashAttribute("successMessage", "Thêm danh mục thành công!");
        } else { 
            response = categoryService.updateCategory(categoryBean);
            if (response != null) {
                model.addAttribute("error", response); 
                return "/admin/Category.html";
            }
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật danh mục thành công!");
        }

        return "redirect:/Admin/Category";
    }

    @PostMapping("/Admin/Category/Delete")
    public String deleteCategory(@RequestParam("id") int id, RedirectAttributes redirectAttributes) {
        boolean deleted = categoryService.deleteCategory(id);
        if (!deleted) {
            redirectAttributes.addFlashAttribute("error", "Xóa loại sản phẩm thất bại!");
        } else {
            redirectAttributes.addFlashAttribute("successMessage", "Xóa loại sản phẩm thành công!");
        }
        return "redirect:/Admin/Category";
    }
}



package com.poly.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.demo.beans.CategoryBean;
import com.poly.demo.enities.CategoryEntity;
import com.poly.demo.jpas.CategoryJPA;
import org.springframework.data.domain.Sort;

@Service
public class CategoryService {
    @Autowired
    private CategoryJPA categoryJPA;

    @Autowired
    private ImageService imageService;

    public List<CategoryEntity> getCategory(String search, String sort) {
        if (search != null && !search.isEmpty()) {
            Sort sorting = Sort.by(sort.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, "name");
            return categoryJPA.findByNameContainingIgnoreCase(search, sorting);
        } else {
            return sort.equals("asc") 
                ? categoryJPA.findAllByOrderByNameAsc() 
                : categoryJPA.findAllByOrderByNameDesc();
        }
    }

    
    public String insertCategory(CategoryBean categoryBean) {
        Optional<CategoryEntity> existingCategory = categoryJPA.findByName(categoryBean.getName());
        if (existingCategory.isPresent()) {
            return "Danh mục đã tồn tại!";
        }

        try {
            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setName(categoryBean.getName());
            if (categoryBean.getImage() != null && !categoryBean.getImage().isEmpty()) {
                String imagePath = imageService.saveImage(categoryBean.getImage());
                categoryEntity.setImage(imagePath);
            }
            categoryJPA.save(categoryEntity);
        } catch (Exception e) {
            return "Có lỗi trong quá trình thêm danh mục: " + e.getMessage();
        }

        return null;
    }

    public String updateCategory(CategoryBean categoryBean) {
        CategoryEntity categoryEntity = categoryJPA.findById(categoryBean.getId()).orElse(null);
        if (categoryEntity == null) {
            return "Category not found.";
        }
        categoryEntity.setName(categoryBean.getName());
        if (categoryBean.getImage() != null && !categoryBean.getImage().isEmpty()) {
            String imagePath = imageService.saveImage(categoryBean.getImage());
            categoryEntity.setImage(imagePath);
        } else {
            categoryEntity.setImage(categoryBean.getCurrentImage());
        }
        categoryJPA.save(categoryEntity);
        return null;
    }

    public boolean deleteCategory(int id){
        try{
          Optional<CategoryEntity> category = categoryJPA.findById(id);
          if(!category.isPresent()){
            return false; 
          }

          categoryJPA.delete(category.get());
          
        }catch(Exception e){
          return false;
        }

        return true;
      }
    
    public List<CategoryEntity> getAllCategories() {
        return categoryJPA.findAll();
    }
    
    public Optional<CategoryEntity> getCategoryById(Integer id){
    	return categoryJPA.findById(id);
    }
		
	
}


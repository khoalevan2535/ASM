package com.poly.demo.beans;

import java.io.Serializable;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.poly.demo.enities.ProductEntity;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class ImageBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String imagePath; 

    private String currentImage;
    
    @NotNull(message = "Sản phẩm liên kết không được để trống")
    @ManyToOne
    @JoinColumn(name = "Product_id")
    private ProductEntity product;

    @Transient 
    @NotNull(message = "Hình ảnh không được để trống")
    private MultipartFile imageFile; 

    public String isImageFileError() {
        if (imageFile == null || imageFile.isEmpty()) {
            return "Hình ảnh không được để trống";
        }
        double size = imageFile.getSize() / 1024.0 / 1024.0; 
        if (size > 20) {
            return "Kích thước tệp tin hình ảnh tối đa là 20MB";
        }
        return null;
    }
}
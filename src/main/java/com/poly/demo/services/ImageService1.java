package com.poly.demo.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.demo.beans.ImageBean;
import com.poly.demo.enities.ImageEntity;
import com.poly.demo.enities.Payment_statusEntity;
import com.poly.demo.jpas.ImageJPA;

@Service
public class ImageService1 {

    @Autowired
    private ImageJPA imageJPA;

    @Autowired
    private ImageService imageService;

    public List<ImageEntity> getAllImages() {
        return imageJPA.findAll();
    }

    public Optional<ImageEntity> getImageById(Integer id) {
        return imageJPA.findById(id);
    }

    public String insertImage(ImageBean imageBean) {
        String fileName = imageService.saveImage(imageBean.getImageFile());
        imageBean.setImagePath(fileName);

        ImageEntity newImage = new ImageEntity();
        newImage.setImage(imageBean.getImagePath()); 
        newImage.setProduct(imageBean.getProduct());

        imageJPA.save(newImage);
        return null;
    }

    public String updateImage(ImageBean imageBean) {
        Optional<ImageEntity> existingImage = imageJPA.findById(imageBean.getId());
        if (!existingImage.isPresent()) {
            return "Hình ảnh không tồn tại!";
        }

        ImageEntity imageToUpdate = existingImage.get();
        if (imageBean.getImageFile() != null && !imageBean.getImageFile().isEmpty()) {
            String fileName = imageService.saveImage(imageBean.getImageFile());
            imageToUpdate.setImage(fileName);
        }
        imageToUpdate.setProduct(imageBean.getProduct());

        imageJPA.save(imageToUpdate);
        return null;
    }

    public boolean deleteImage(int id) {
        try {
            Optional<ImageEntity> image = imageJPA.findById(id);
            if (!image.isPresent()) {
                return false;
            }

            Path imagePath = Paths.get("images").resolve(image.get().getImage());
            Files.deleteIfExists(imagePath);

            imageJPA.delete(image.get());
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
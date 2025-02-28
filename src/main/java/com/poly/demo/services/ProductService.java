package com.poly.demo.services;

import com.poly.demo.enities.ProductEntity;
import com.poly.demo.jpas.ProductJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductJPA productJPA;

    public List<ProductEntity> getAllProducts() {
        return productJPA.findAll();
    }
    

}
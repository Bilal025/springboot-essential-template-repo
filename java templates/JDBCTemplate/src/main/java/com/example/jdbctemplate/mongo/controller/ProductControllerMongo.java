package com.example.jdbctemplate.mongo.controller;

import com.example.jdbctemplate.mongo.model.ProductMongo;
import com.example.jdbctemplate.mongo.repository.ProductRepositoryMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductControllerMongo {
    @Autowired
    private ProductRepositoryMongo productRepository;

    @GetMapping
    public List<ProductMongo> getAllProducts() {
        return productRepository.findAll();
    }

    @PostMapping
    public String addProduct(@RequestBody ProductMongo product) {
        productRepository.save(product);
        return "Product added successfully!";
    }
}

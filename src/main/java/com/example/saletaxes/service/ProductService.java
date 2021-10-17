package com.example.saletaxes.service;

import com.example.saletaxes.entity.Product;
import com.example.saletaxes.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public List<Product> getAllProducts() {
        List<Product> productList = productRepo.findAll();
        return productList;
    }

    public Product getProductByID(Long id) {
        return productRepo.findById(id).get();
    }

    public List<Product> saveAllProducts(List<Product> productList) {
        return productRepo.saveAll(productList);
    }
}

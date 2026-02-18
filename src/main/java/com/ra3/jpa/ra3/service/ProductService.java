package com.ra3.jpa.ra3.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ra3.jpa.ra3.dto.ProductRequestDto;
import com.ra3.jpa.ra3.model.Product;
import com.ra3.jpa.ra3.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product crear(ProductRequestDto product){
        Product entity = new Product();
        if(productRepository.findById(product.getId()).isEmpty()){
            BeanUtils.copyProperties(product, entity);
            entity.setId(null);
            return productRepository.save(entity);
        }
        return null;
    }

    public Product update(ProductRequestDto product){
        Product entity = new Product();
        if(productRepository.findById(product.getId()).isPresent()){
            BeanUtils.copyProperties(product, entity);
            return productRepository.save(entity);
        }
        return null;
    }
}

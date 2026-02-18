package com.ra3.jpa.ra3.controller;

import java.util.List;
import java.util.Optional;
import com.ra3.jpa.ra3.model.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ra3.jpa.ra3.model.Product;
import com.ra3.jpa.ra3.repository.ProductRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/producte")
public class ProductController {


    @Autowired
    public ProductRepository productRepository;


    @GetMapping("/all")
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // CAMBIAR ESTE Y CREAR UNO PARA BUSQUEDA CON LOS DTO
    @PostMapping("/create")
    public String createProduct(@RequestBody Product product) {
        
        productRepository.save(product);
        
        return "Creat Correctament";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        productRepository.deleteById(id);
        return "Eliminat correctament";

    }
    
    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productRepository.findById(id);
        
    }
    
    @GetMapping("/categoria/{category}")
    public List<Product> getMethodName(@PathVariable Category category) {
        return productRepository.findByCategoria(category);
    }
    
}

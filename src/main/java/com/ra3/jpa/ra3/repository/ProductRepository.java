package com.ra3.jpa.ra3.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.ra3.jpa.ra3.model.Category;
import com.ra3.jpa.ra3.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findByName(String name);
    List<Product> findByCategoria(Category categoria);
}

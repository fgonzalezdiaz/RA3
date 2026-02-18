package com.ra3.jpa.ra3.model;
import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    @Enumerated(value = EnumType.STRING)
    private Category categoria;
    private int stock;
    private Date dataCreated;
    private Date dataUpdated;
    public Product(){}
    
    @PrePersist
    protected void onCreate() {
        dataCreated = new Date(System.currentTimeMillis());
    }
    
    @PreUpdate
    protected void onUpdate() {
        dataUpdated = new Date(System.currentTimeMillis());
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategoria() {
        return categoria;
    }

    public void setCategoria(Category categoria) {
        this.categoria = categoria;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Date getDataCreated() {
        return dataCreated;
    }

    public void setDataCreated(Date dataCreated) {
        this.dataCreated = dataCreated;
    }

    public Date getDataUpdated() {
        return dataUpdated;
    }

    public void setDataUpdated(Date dataUpdated) {
        this.dataUpdated = dataUpdated;
    }
}    


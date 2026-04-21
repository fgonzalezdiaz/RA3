package com.ra3.jpa.ra3.dto;

import java.math.BigDecimal;

import com.ra3.jpa.ra3.model.Condition;

public class ProductRequestDto {

    private String name;
    private String description;
    private Integer stock;
    private BigDecimal price;
    private BigDecimal rating;
    private Condition condition;

    public ProductRequestDto() {}

    public ProductRequestDto(String name, String description, Integer stock, BigDecimal price, BigDecimal rating, Condition condition) {
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.price = price;
        this.rating = rating;
        this.condition = condition;
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}

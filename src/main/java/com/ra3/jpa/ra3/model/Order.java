package com.ra3.jpa.ra3.model;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Aqui ira logica de relacion
    private int customerId;
    private Date orderDate;
    private BigDecimal totalAmount;
    private OrderStatus orderStatus;
    private Boolean status;
    private Date dataCreated;
    private Date dataUpdated;
    
    public Order(int customerId, Date orderDate, BigDecimal totalAmount, OrderStatus orderStatus,
            Boolean status, Date dataCreated, Date dataUpdated) {
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
        this.status = status;
        this.dataCreated = dataCreated;
        this.dataUpdated = dataUpdated;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public Date getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
    public Boolean getStatus() {
        return status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
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

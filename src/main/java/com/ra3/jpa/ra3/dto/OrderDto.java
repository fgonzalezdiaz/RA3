package com.ra3.jpa.ra3.dto;

import java.math.BigDecimal;
import java.sql.Date;

import com.ra3.jpa.ra3.model.Customer;
import com.ra3.jpa.ra3.model.Order;
import com.ra3.jpa.ra3.model.OrderStatus;

public class OrderDto {

    private Long customerId;
    private Date orderDate;
    private BigDecimal totalAmount;
    private OrderStatus orderStatus;

    public static OrderDto toDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setCustomerId(order.getCustomer().getId());
        dto.setOrderDate(order.getOrderDate());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setOrderStatus(order.getOrderStatus());
        return dto;
    }

    public static Order toEntity(OrderDto orderDto, Customer customer) {
        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(orderDto.getOrderDate());
        order.setTotalAmount(orderDto.getTotalAmount());
        order.setOrderStatus(orderDto.getOrderStatus());
        return order;
    }

    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Long customerId) {
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

}

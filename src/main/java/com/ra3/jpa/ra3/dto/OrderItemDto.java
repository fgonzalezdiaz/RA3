package com.ra3.jpa.ra3.dto;

import java.math.BigDecimal;

import com.ra3.jpa.ra3.model.Order;
import com.ra3.jpa.ra3.model.OrderItem;
import com.ra3.jpa.ra3.model.Product;

public class OrderItemDto {

    private Long orderId;
    private Long productId;
    private Long quantity;
    private BigDecimal unitPrice;

    public static OrderItemDto toDto(OrderItem orderItem) {
        OrderItemDto dto = new OrderItemDto();
        dto.setOrderId(orderItem.getOrder().getId());
        dto.setProductId(orderItem.getProduct().getId());
        dto.setQuantity(orderItem.getQuantity());
        dto.setUnitPrice(orderItem.getUnitPrice());
        return dto;
    }

    public static OrderItem toEntity(OrderItemDto orderItemDto, Order order, Product product) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setQuantity(orderItemDto.getQuantity());
        orderItem.setUnitPrice(orderItemDto.getUnitPrice());
        return orderItem;
    }

    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public Long getProductId() {
        return productId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public Long getQuantity() {
        return quantity;
    }
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

}

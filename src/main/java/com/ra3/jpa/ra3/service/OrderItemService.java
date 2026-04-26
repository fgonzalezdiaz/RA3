package com.ra3.jpa.ra3.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ra3.jpa.ra3.dto.OrderItemDto;
import com.ra3.jpa.ra3.model.Order;
import com.ra3.jpa.ra3.model.OrderItem;
import com.ra3.jpa.ra3.model.Product;
import com.ra3.jpa.ra3.repository.OrderItemRepository;
import com.ra3.jpa.ra3.repository.OrderRepository;
import com.ra3.jpa.ra3.repository.ProductRepository;

@Service
public class OrderItemService {

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(orderItemRepository.findAll());
    }

    public ResponseEntity<?> findById(Long id) {
        Optional<OrderItem> orderItem = orderItemRepository.findById(id);
        if (!orderItem.isPresent()) {
            return ResponseEntity.ok().body("Este orderItem no existe");
        }
        return ResponseEntity.ok().body(orderItem.get());
    }

    public ResponseEntity<?> save(OrderItemDto orderItemDto) {
        Optional<Order> order = orderRepository.findById(orderItemDto.getOrderId());
        if (!order.isPresent()) {
            return ResponseEntity.ok().body("Este order no existe");
        }
        Optional<Product> product = productRepository.findById(orderItemDto.getProductId());
        if (!product.isPresent()) {
            return ResponseEntity.ok().body("Este producto no existe");
        }
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order.get());
        orderItem.setProduct(product.get());
        orderItem.setQuantity(orderItemDto.getQuantity());
        orderItem.setUnitPrice(orderItemDto.getUnitPrice());
        orderItemRepository.save(orderItem);
        return ResponseEntity.ok().body("OrderItem guardado correctamente");
    }

    public ResponseEntity<?> update(Long id, OrderItemDto orderItemDto) {
        Optional<OrderItem> existing = orderItemRepository.findById(id);
        if (!existing.isPresent()) {
            return ResponseEntity.ok().body("Este orderItem no existe");
        }
        OrderItem orderItem = existing.get();
        orderItem.setQuantity(orderItemDto.getQuantity());
        orderItem.setUnitPrice(orderItemDto.getUnitPrice());
        orderItemRepository.save(orderItem);
        return ResponseEntity.ok().body("OrderItem actualizado correctamente");
    }

    public ResponseEntity<?> delete(Long id) {
        if (!orderItemRepository.findById(id).isPresent()) {
            return ResponseEntity.ok().body("Este orderItem no existe");
        }
        orderItemRepository.deleteById(id);
        return ResponseEntity.ok().body("OrderItem eliminado correctamente");
    }

}

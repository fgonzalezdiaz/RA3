package com.ra3.jpa.ra3.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ra3.jpa.ra3.dto.OrderDto;
import com.ra3.jpa.ra3.model.Customer;
import com.ra3.jpa.ra3.model.Order;
import com.ra3.jpa.ra3.repository.CustomerRepository;
import com.ra3.jpa.ra3.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(orderRepository.findAll());
    }

    public ResponseEntity<?> findById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (!order.isPresent()) {
            return ResponseEntity.ok().body("Este order no existe");
        }
        return ResponseEntity.ok().body(order.get());
    }

    public ResponseEntity<?> save(OrderDto orderDto) {
        Optional<Customer> customer = customerRepository.findById(orderDto.getCustomerId());
        if (!customer.isPresent()) {
            return ResponseEntity.ok().body("Este customer no existe");
        }
        Order order = OrderDto.toEntity(orderDto, customer.get());
        order.setStatus(true);
        orderRepository.save(order);
        return ResponseEntity.ok().body("Order guardado correctamente");
    }

    public ResponseEntity<?> update(Long id, OrderDto orderDto) {
        Optional<Order> existing = orderRepository.findById(id);
        if (!existing.isPresent()) {
            return ResponseEntity.ok().body("Este order no existe");
        }
        Order order = existing.get();
        order.setOrderDate(orderDto.getOrderDate());
        order.setTotalAmount(orderDto.getTotalAmount());
        order.setOrderStatus(orderDto.getOrderStatus());
        orderRepository.save(order);
        return ResponseEntity.ok().body("Order actualizado correctamente");
    }

    public ResponseEntity<?> delete(Long id) {
        if (!orderRepository.findById(id).isPresent()) {
            return ResponseEntity.ok().body("Este order no existe");
        }
        orderRepository.deleteById(id);
        return ResponseEntity.ok().body("Order eliminado correctamente");
    }

}

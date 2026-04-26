package com.ra3.jpa.ra3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ra3.jpa.ra3.dto.OrderItemDto;
import com.ra3.jpa.ra3.service.OrderItemService;

@RestController
@RequestMapping("/api/orderitem")
public class OrderItemController {

    @Autowired
    OrderItemService orderItemService;

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        return orderItemService.findAll();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return orderItemService.findById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody OrderItemDto orderItemDto) {
        return orderItemService.save(orderItemDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody OrderItemDto orderItemDto) {
        return orderItemService.update(id, orderItemDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return orderItemService.delete(id);
    }

}

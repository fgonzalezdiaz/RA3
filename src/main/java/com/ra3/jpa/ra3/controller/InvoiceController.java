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

import com.ra3.jpa.ra3.dto.InvoiceDto;
import com.ra3.jpa.ra3.service.InvoiceService;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        return invoiceService.findAll();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return invoiceService.findById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody InvoiceDto invoiceDto) {
        return invoiceService.save(invoiceDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody InvoiceDto invoiceDto) {
        return invoiceService.update(id, invoiceDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return invoiceService.delete(id);
    }

}

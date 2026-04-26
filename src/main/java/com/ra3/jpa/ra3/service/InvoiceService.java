package com.ra3.jpa.ra3.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ra3.jpa.ra3.dto.InvoiceDto;
import com.ra3.jpa.ra3.model.Invoice;
import com.ra3.jpa.ra3.model.Order;
import com.ra3.jpa.ra3.repository.InvoiceRepository;
import com.ra3.jpa.ra3.repository.OrderRepository;

@Service
public class InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    OrderRepository orderRepository;

    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(invoiceRepository.findAll());
    }

    public ResponseEntity<?> findById(Long id) {
        Optional<Invoice> invoice = invoiceRepository.findById(id);
        if (!invoice.isPresent()) {
            return ResponseEntity.ok().body("Esta invoice no existe");
        }
        return ResponseEntity.ok().body(invoice.get());
    }

    public ResponseEntity<?> save(InvoiceDto invoiceDto) {
        Optional<Order> order = orderRepository.findById(invoiceDto.getOrderId());
        if (!order.isPresent()) {
            return ResponseEntity.ok().body("Este order no existe");
        }
        Invoice invoice = InvoiceDto.toEntity(invoiceDto, order.get());
        invoiceRepository.save(invoice);
        return ResponseEntity.ok().body("Invoice guardada correctamente");
    }

    public ResponseEntity<?> update(Long id, InvoiceDto invoiceDto) {
        Optional<Invoice> existing = invoiceRepository.findById(id);
        if (!existing.isPresent()) {
            return ResponseEntity.ok().body("Esta invoice no existe");
        }
        Invoice invoice = existing.get();
        invoice.setInvoiceNumber(invoiceDto.getInvoiceNumber());
        invoice.setIssueDate(invoiceDto.getIssueDate());
        invoice.setTaxAmount(invoiceDto.getTaxAmount());
        invoice.setTotalWithTax(invoiceDto.getTotalWithTax());
        invoiceRepository.save(invoice);
        return ResponseEntity.ok().body("Invoice actualizada correctamente");
    }

    public ResponseEntity<?> delete(Long id) {
        if (!invoiceRepository.findById(id).isPresent()) {
            return ResponseEntity.ok().body("Esta invoice no existe");
        }
        invoiceRepository.deleteById(id);
        return ResponseEntity.ok().body("Invoice eliminada correctamente");
    }

}

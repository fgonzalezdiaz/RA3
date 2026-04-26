package com.ra3.jpa.ra3.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ra3.jpa.ra3.dto.CustomerDto;
import com.ra3.jpa.ra3.model.Customer;
import com.ra3.jpa.ra3.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(customerRepository.findAll());
    }

    public ResponseEntity<?> findById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (!customer.isPresent()) {
            return ResponseEntity.ok().body("Este customer no existe");
        }
        return ResponseEntity.ok().body(customer.get());
    }

    public ResponseEntity<?> save(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setPhone(customerDto.getPhone());
        customer.setStatus(true);
        customerRepository.save(customer);
        return ResponseEntity.ok().body("Customer guardado correctamente");
    }

    public ResponseEntity<?> update(Long id, CustomerDto customerDto) {
        Optional<Customer> existing = customerRepository.findById(id);
        if (!existing.isPresent()) {
            return ResponseEntity.ok().body("Este customer no existe");
        }
        Customer customer = existing.get();
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setPhone(customerDto.getPhone());
        customerRepository.save(customer);
        return ResponseEntity.ok().body("Customer actualizado correctamente");
    }

    public ResponseEntity<?> delete(Long id) {
        if (!customerRepository.findById(id).isPresent()) {
            return ResponseEntity.ok().body("Este customer no existe");
        }
        customerRepository.deleteById(id);
        return ResponseEntity.ok().body("Customer eliminado correctamente");
    }

}

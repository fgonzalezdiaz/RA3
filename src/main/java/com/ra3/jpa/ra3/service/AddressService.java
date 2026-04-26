package com.ra3.jpa.ra3.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ra3.jpa.ra3.dto.AddressDto;
import com.ra3.jpa.ra3.model.Address;
import com.ra3.jpa.ra3.model.Customer;
import com.ra3.jpa.ra3.repository.AddressRepository;
import com.ra3.jpa.ra3.repository.CustomerRepository;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    CustomerRepository customerRepository;

    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(addressRepository.findAll());
    }

    public ResponseEntity<?> findById(Long id) {
        Optional<Address> address = addressRepository.findById(id);
        if (!address.isPresent()) {
            return ResponseEntity.ok().body("Esta direccion no existe");
        }
        return ResponseEntity.ok().body(address.get());
    }

    public ResponseEntity<?> saveAddress(Long customerId, AddressDto addressDto) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (!customer.isPresent()) {
            return ResponseEntity.ok().body("Este customer no existe");
        }

        AddressDto dto = new AddressDto();
        Address address = dto.toEntity(addressDto);
        address.setCustomer(customer.get());

        addressRepository.save(address);
        return ResponseEntity.ok().body("Direccion guardada correctamente");
    }

    public ResponseEntity<?> updateAddress(Long id, AddressDto addressDto) {
        Optional<Address> existing = addressRepository.findById(id);
        if (!existing.isPresent()) {
            return ResponseEntity.ok().body("Esta direccion no existe");
        }

        Address address = existing.get();
        address.setAddress(addressDto.getAddress());
        address.setCity(addressDto.getCity());
        address.setPostalCode(addressDto.getPostalCode());
        address.setCountry(addressDto.getCountry());

        addressRepository.save(address);
        return ResponseEntity.ok().body("Direccion actualizada correctamente");
    }

    public ResponseEntity<?> deleteAddress(Long id) {
        if (!addressRepository.findById(id).isPresent()) {
            return ResponseEntity.ok().body("Esta direccion no existe");
        }
        addressRepository.deleteById(id);
        return ResponseEntity.ok().body("Direccion eliminada correctamente");
    }
}

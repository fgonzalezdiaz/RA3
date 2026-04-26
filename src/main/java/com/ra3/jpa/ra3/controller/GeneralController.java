package com.ra3.jpa.ra3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ra3.jpa.ra3.dto.CreateOrderRequestDto;
import com.ra3.jpa.ra3.dto.ErrorDto;
import com.ra3.jpa.ra3.dto.UserCustomerDto;
import com.ra3.jpa.ra3.service.CustomerService;
import com.ra3.jpa.ra3.service.GeneralService;
import com.ra3.jpa.ra3.service.UserService;


@RestController
@RequestMapping("/api/general")
public class GeneralController {
    
    //Helpers de resposta d'error 
    private ResponseEntity<ErrorDto> notFound(Long id) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ErrorDto(404, "Producte amb id " + id + " no trobat"));
    }

    private ResponseEntity<ErrorDto> serverError(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ErrorDto(500, e.getMessage()));
    }

    @Autowired
    CustomerService customerService;
    
    @Autowired
    UserService userService;

    @Autowired
    GeneralService generalService;


    // Endpoint 1 - Guardar user + customer mitjançant DTO
    @PostMapping("/save/user/customer")
    public ResponseEntity<?> createUserCustomer(@RequestBody UserCustomerDto user) {
        try{
            return ResponseEntity.ok().body(generalService.createUserCustomer(user));
        } catch (Exception e) {
            return serverError(e);
        } 
    }

    // Endpoint 2 - Consulta de un usuari + info de customer
    @GetMapping("/getAllUserInfo/{id}")
    public ResponseEntity<?> getAllUserInfo(@PathVariable Long id) {
        try{
            return ResponseEntity.ok().body(generalService.findAllInfoUser(id));
        } catch (Exception e) {
            return serverError(e);
        }
    }

    // Endpoint 3 - Endpoint para borrar todas las direccion del customer
    @DeleteMapping("/delete/address/{id}")
    public ResponseEntity<?> deleteAddressCustomer(@PathVariable Long id){
        try{
            return ResponseEntity.ok().body(generalService.deleteCustomerAddress(id));
        } catch (Exception e) {
            return serverError(e);
        }
    }

    // Endpoint 4 - Endpoint para consultar tots els customers
    @GetMapping("/findAll/customer/address")
    public ResponseEntity<?> findAllCustomerAddress() {
        try{
            return ResponseEntity.ok().body(generalService.findAllCustomerAddress());
        } catch (Exception e) {
            return serverError(e);
        }
    }

    // Endpoint 5 - Crear una order amb els seus order items
    @PostMapping("/create/order")
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequestDto dto) {
        try {
            return ResponseEntity.ok().body(generalService.createOrder(dto));
        } catch (Exception e) {
            return serverError(e);
        }
    }

    // Endpoint 6 - Processar una order (PENDENT -> PROCESSAT)
    @PutMapping("/process/order/{id}")
    public ResponseEntity<?> processOrder(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(generalService.processOrder(id));
        } catch (Exception e) {
            return serverError(e);
        }
    }

    // Endpoint 7 - Endpoint per esborrar rols a l'usuari
    @DeleteMapping("/delete/rol/{id}")
    public ResponseEntity<?> eliminaRolesUsuari(@PathVariable Long id, @RequestBody List<Long> idroles){
        try{
            return ResponseEntity.ok().body(generalService.eliminaRolesUsuari(id, idroles));
        } catch (Exception e){
            return serverError(e);
        }
    }
    
    



    

    
}

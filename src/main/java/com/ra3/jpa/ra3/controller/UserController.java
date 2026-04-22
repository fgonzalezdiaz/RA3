package com.ra3.jpa.ra3.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ra3.jpa.ra3.dto.ErrorDto;
import com.ra3.jpa.ra3.dto.UserCustomerDto;
import com.ra3.jpa.ra3.dto.UserDto;
import com.ra3.jpa.ra3.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;


    //Helpers de resposta d'error 
    private ResponseEntity<ErrorDto> notFound(Long id) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ErrorDto(404, "Producte amb id " + id + " no trobat"));
    }

    private ResponseEntity<ErrorDto> serverError(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ErrorDto(500, e.getMessage()));
    }

    // Endpoints

    @PostMapping("/create/user/customer")
    public UserDto createUserCustomer(@RequestBody UserCustomerDto user) {
        return userService.createUserCustomer(user);   
    }
    


}

package com.ra3.jpa.ra3.mapper;

import com.ra3.jpa.ra3.dto.CustomerDto;
import com.ra3.jpa.ra3.model.Customer;

public class CustomerMapper {

    public static Customer toEntity(CustomerDto customerDto){
        Customer customer = new Customer();
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setPhone(customerDto.getPhone());

        return customer;
    }
    
    public static CustomerDto toDto(Customer customer){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setPhone(customer.getPhone());

        return customerDto;
    }



}

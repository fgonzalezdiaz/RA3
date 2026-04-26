package com.ra3.jpa.ra3.dto;

import com.ra3.jpa.ra3.model.Customer;

public class CustomerDto {

    private String firstName;
    private String lastName;
    private String phone;

    public static CustomerDto toDto(Customer customer) {
        CustomerDto dto = new CustomerDto();
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setPhone(customer.getPhone());
        return dto;
    }

    public static Customer toEntity(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setPhone(customerDto.getPhone());
        return customer;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

}

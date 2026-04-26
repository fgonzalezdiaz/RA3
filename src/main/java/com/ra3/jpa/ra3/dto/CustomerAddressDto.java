package com.ra3.jpa.ra3.dto;

import java.util.List;

public class CustomerAddressDto {

    private String firstName;
    private String lastName;
    private String phone;

    List<AddressDto> addreses;

    public CustomerAddressDto toDto(CustomerDto customerDto, AddressDto addressDto){
        CustomerAddressDto c = new CustomerAddressDto();
        c.setFirstName(customerDto.getFirstName());
        c.setLastName(customerDto.getLastName());
        c.setPhone(customerDto.getPhone());

        return c;
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

    public List<AddressDto> getAddreses() {
        return addreses;
    }

    public void setAddreses(List<AddressDto> addreses) {
        this.addreses = addreses;
    }


}

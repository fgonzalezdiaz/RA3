package com.ra3.jpa.ra3.dto;

import com.ra3.jpa.ra3.model.Address;

public class AddressDto {
    private String address;
    private String city;
    private String postalCode;
    private String country;

    public Address toEntity(AddressDto addressDto){
        Address address = new Address();
        address.setAddress(addressDto.getAddress());
        address.setCity(addressDto.getCity());
        address.setPostalCode(addressDto.getPostalCode());
        address.setCountry(addressDto.getCountry());

        return address;
    }

    public AddressDto toDto(Address address){
        AddressDto addressDto = new AddressDto();
        addressDto.setAddress(address.getAddress());
        addressDto.setCity(address.getCity());
        addressDto.setPostalCode(address.getPostalCode());
        addressDto.setCountry(address.getCountry());

        return addressDto;
    }



    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

}

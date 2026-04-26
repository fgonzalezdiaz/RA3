package com.ra3.jpa.ra3.dto;

public class CustomerAddressDto {

    private String firstName;
    private String lastName;
    private String phone;

    private String address;
    private String city;
    private String postalCode;
    private String country;

    public CustomerAddressDto toDto(CustomerDto customerDto, AddressDto addressDto){
        CustomerAddressDto c = new CustomerAddressDto();
        c.setFirstName(customerDto.getFirstName());
        c.setLastName(customerDto.getLastName());
        c.setPhone(customerDto.getPhone());

        c.setAddress(addressDto.getAddress());
        c.setCity(addressDto.getCity());
        c.setPostalCode(addressDto.getPostalCode());
        c.setCountry(addressDto.getCountry());

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

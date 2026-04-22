package com.ra3.jpa.ra3.mapper;

import com.ra3.jpa.ra3.dto.CustomerDto;
import com.ra3.jpa.ra3.dto.UserCustomerDto;
import com.ra3.jpa.ra3.dto.UserDto;

public class UserCustomerMapper {

    public static UserDto userCustomerToUserDto(UserCustomerDto userCustomerDto) {
        UserDto userDto = new UserDto();
        userDto.setEmail(userCustomerDto.getEmail());
        userDto.setPassword(userCustomerDto.getPassword());

        return userDto;
    }

    public static CustomerDto userCustomerToCustomerDto(UserCustomerDto userCustomerDto) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstName(userCustomerDto.getFirstName());
        customerDto.setLastName(userCustomerDto.getLastName());
        customerDto.setPhone(userCustomerDto.getPhone());

        return customerDto;
    }

}

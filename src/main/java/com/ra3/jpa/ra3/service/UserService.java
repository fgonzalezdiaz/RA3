package com.ra3.jpa.ra3.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ra3.jpa.ra3.dto.CustomerDto;
import com.ra3.jpa.ra3.dto.UserCustomerDto;
import com.ra3.jpa.ra3.dto.UserDto;
import com.ra3.jpa.ra3.mapper.UserCustomerMapper;
import com.ra3.jpa.ra3.mapper.UserMapper;
import com.ra3.jpa.ra3.model.Customer;
import com.ra3.jpa.ra3.model.User;
import com.ra3.jpa.ra3.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserDto createUserCustomer(UserCustomerDto userCustomerDto){
        UserDto userDto = UserCustomerMapper.userCustomerToUserDto(userCustomerDto);
        CustomerDto customerDto = UserCustomerMapper.userCustomerToCustomerDto(userCustomerDto);
        
        User user = new User();
        

        Customer customer = new Customer();
 
        // Convertir los dos dtos a entitis subirlos a la BBDD 
        // y return SOLO de userDto

        return UserMapper.userDto(user);
    }
}

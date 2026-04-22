package com.ra3.jpa.ra3.mapper;

import com.ra3.jpa.ra3.dto.UserDto;
import com.ra3.jpa.ra3.model.User;

public class UserMapper {

    public static User toEntity(UserDto userDto){
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        return user;
    }

    public static UserDto userDto(User user){
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());

        return userDto;
    }

}

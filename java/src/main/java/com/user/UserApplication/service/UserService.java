package com.user.UserApplication.service;

//import com.user.UserApplication.entity.User;

import com.user.UserApplication.dto.UserDto;
import com.user.UserApplication.entity.User;

import java.text.ParseException;

public interface UserService {
    User registerOrUpdateUser(UserDto userDto) throws ParseException;
    User authenticateUser(String username, String password);

}

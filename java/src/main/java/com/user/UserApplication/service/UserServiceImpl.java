package com.user.UserApplication.service;

import com.user.UserApplication.dto.UserDto;
import com.user.UserApplication.entity.User;
import com.user.UserApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerOrUpdateUser(UserDto userDto) throws ParseException {
        // Check if a user with the provided username already exists
        User existingUser = userRepository.findByUsername(userDto.getUsername());
        if (existingUser != null) {
            // User with the same username exists, update the existing user
            updateExistingUser(existingUser, userDto);
            return userRepository.save(existingUser);
        } else {
            // User with the username doesn't exist, create a new user
            User user = new User(userDto);
            return userRepository.save(user);
        }
    }

    private void updateExistingUser(User existingUser, UserDto userDto) throws ParseException {
        // Update the existing user's information here
        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setLastName(userDto.getLastName());
        existingUser.setStreetAddress(userDto.getStreetAddress());
    }

    @Override
    public User authenticateUser(String username, String password) {

        // Check if a user with the provided username already exists
        User existingUser = userRepository.findByUsername(username);
        if (existingUser != null && existingUser.getPassword().equals(password)) {
            // Passwords match, return the authenticated user
            return existingUser;
        }

        // Authentication failed, return null
        return null;
    }



}

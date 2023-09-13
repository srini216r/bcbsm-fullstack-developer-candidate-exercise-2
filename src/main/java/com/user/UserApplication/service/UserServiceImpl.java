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
    public User login(String username, String password) {
        // Use Spring Security's AuthenticationManager to authenticate the user.
      //  UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
       // Authentication authentication = authenticationManager.authenticate(token);

        // If authentication is successful, return the authenticated user.
       // if (authentication.isAuthenticated()) {
         //   return (User) authentication.getPrincipal();
       // }

        // If authentication fails, you can throw an exception or return null.
       // throw new AuthenticationException("Authentication failed");
        return new User();
    }


}

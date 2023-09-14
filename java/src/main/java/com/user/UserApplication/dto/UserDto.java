package com.user.UserApplication.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
public class UserDto {
    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @DateTimeFormat(pattern = "MM-dd-yyyy")
    @NotBlank(message = "Date of birth is required")
    @Pattern(regexp = "^(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])-\\d{4}$", message = "Invalid date format (MM-DD-YYYY)")
    private String dateOfBirth;

    @NotBlank(message = "Street address is required")
    private String streetAddress;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is required")
    private String state;

    // Zip code format: XXXXX
    @Pattern(regexp = "\\d{5}", message = "Zip code should be a 5-digit number")
    private String zipCode;

    @NotBlank(message = "Username is required")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Username must be alphanumeric with no special characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).*$", message = "Password must contain at least one capital letter, one number, and one special character (@#$%^&+=!)")
    private String password;
}

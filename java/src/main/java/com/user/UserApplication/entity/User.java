package com.user.UserApplication.entity;

import com.user.UserApplication.dto.UserDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String id;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotNull(message = "Date of birth is required")
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    @Past(message = "Date of birth should be in the past")
    private Date dateOfBirth;

    @NotBlank(message = "Street address is required")
    private String streetAddress;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is required")
    private String state;

    @Pattern(regexp = "\\d{5}", message = "Zip code should be a 5-digit number")
    private String zipCode;

    @NotBlank(message = "Username is required")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Username must be alphanumeric with no special characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).*$", message = "Password must contain at least one capital letter, one number, and one special character (@#$%^&+=!)")
    private String password;

    public User(UserDto userDto) throws ParseException {
        this.firstName = userDto.getFirstName();
        this.lastName = userDto.getLastName();
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        this.dateOfBirth = formatter.parse(userDto.getDateOfBirth());  this.streetAddress = userDto.getStreetAddress();
        this.city = userDto.getCity();
        this.state = userDto.getState();
        this.zipCode = userDto.getZipCode();
        this.username = userDto.getUsername();
        this.password = userDto.getPassword();
    }
}

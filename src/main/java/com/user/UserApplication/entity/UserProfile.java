package com.user.UserApplication.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "userProfiles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {
    @Id
    private String id;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Street address is required")
    private String address;

    private String userId;

    // Constructors, if needed

    // You don't need to manually write getter and setter methods.
}

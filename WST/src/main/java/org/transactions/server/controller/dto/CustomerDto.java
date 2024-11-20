package org.transactions.server.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CustomerDto {

    @NotBlank(message = "firstName is mandatory")
    private String firstName;

    @NotBlank(message = "lastName is mandatory")
    private String lastName;

    @NotBlank
    @Email(message = "Email should be valid")
    private String email;

}

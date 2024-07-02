package com.quantumbank.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.UUID;

@Data
public class UserRegisterDto {
    private UUID id;
    @NotBlank(message = "The Name field is mandatory")
    private String name;
    @NotBlank(message = "The CPF field is mandatory")
    @Size(min = 11, max = 11, message = "The CPF field must contain 11 characters")
    private String cpf;
    @NotBlank(message = "The E-mail field is mandatory")
    @Email(message = "The email field is incorrect")
    private String email;
    @NotBlank(message = "The password field is mandatory")
    @Size(min = 8, message = "The password field must contain at least 8 characters")
    private String password;
    private boolean enabled = true;

}

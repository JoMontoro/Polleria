package com.example.integrador.Entidades_Model;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegisterDto {
    @NotEmpty(message="El nombre no puede ser vacío")
    private String firstName;
    @NotEmpty(message="El apellido no puede ser vacío")
    private String lastName;
    @NotEmpty(message="El email no puede ser vacío")
    @Email (message="El mail debe de tener un @")
    private String email;
    private String phone;
    private String address;
    @Size(min=6, message="El tamaño minimo de caracteres es 6")
    private String password;
    private String confirmPassword;

}

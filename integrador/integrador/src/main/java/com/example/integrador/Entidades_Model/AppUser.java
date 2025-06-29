package com.example.integrador.Entidades_Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class AppUser {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    @Column(unique=true, nullable=false)
    private String email;
    private String phone;
    private String address;
    private String password;
    private String role;
    private Date createdAt;
}

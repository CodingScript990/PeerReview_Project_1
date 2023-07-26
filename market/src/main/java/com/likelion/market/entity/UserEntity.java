package com.likelion.market.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 필수 부분
    @Column(nullable = false, unique = true)
    private String username; // ID

    private String password; // Password
    private String rePassword; // Re-Password

    // 부수적인 부분
    private String email; // Email
    private String phone; // PhoneNumber
    private String address; // Address
}

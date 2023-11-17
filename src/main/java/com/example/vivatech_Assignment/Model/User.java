package com.example.vivatech_Assignment.Model;

import com.example.vivatech_Assignment.Enum.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String password;
    private boolean active;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String otp;
    private LocalDateTime otpGenratedTime;


}

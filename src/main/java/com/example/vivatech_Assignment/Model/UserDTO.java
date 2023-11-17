package com.example.vivatech_Assignment.Model;

import com.example.vivatech_Assignment.Enum.Role;
import lombok.Data;

@Data
public class UserDTO {
    private Role role;
    private String name;
    private String email;
    private String password;
}

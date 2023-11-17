package com.example.vivatech_Assignment.Service;

import com.example.vivatech_Assignment.Model.Login;
import com.example.vivatech_Assignment.Model.User;
import com.example.vivatech_Assignment.Model.UserDTO;

public interface UserService {
    public String register(UserDTO userDTO);

    public String verifyOTPRequest(String email, String otp);

    public String regenOTPProcess(String email);

    public String logIn(Login logInDTO);

    public User getUserByEmail(String name);
}

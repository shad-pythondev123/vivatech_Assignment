package com.example.vivatech_Assignment.Service;

//import com.example.vivatech_Assignment.Model.Login;
import com.example.vivatech_Assignment.Model.Login;
import com.example.vivatech_Assignment.Model.User;
//import com.example.vivatech_Assignment.Model.UserDTO;
import com.example.vivatech_Assignment.Model.UserDTO;
import com.example.vivatech_Assignment.Repository.UserRepository;
import com.example.vivatech_Assignment.Util.EmailUtil;
import com.example.vivatech_Assignment.Util.OtpUtil;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    OtpUtil otpUtil;

    @Autowired
    EmailUtil emailUtil;

    @Override
    public String register(UserDTO userDTO) {
        String otp = otpUtil.generatedOTP();

        try {
            emailUtil.sendOtpToEmail(userDTO.getEmail(),otp);
        } catch (MessagingException e) {
            throw new RuntimeException("Unable to Send OTP (PLEASE TRY AGAIN");
        }

        User user = new User();

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setOtp(otp);
        user.setRole(userDTO.getRole());
        user.setOtpGenratedTime(LocalDateTime.now());

        userRepository.save(user);

        return "User Registered Successfully";
    }


    @Override
    public String verifyOTPRequest(String email, String otp) {

        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User Not Found with this Email"));

        if(user.getOtp().equals(otp) && Duration.between(user.getOtpGenratedTime(),LocalDateTime.now()).getSeconds()<(100 * 60)){
            user.setActive(true);
            userRepository.save(user);
            return "OTP Verified";
        }

        return "Please Regenerate the OTP and try Again";
    }

    @Override
    public String regenOTPProcess(String email) {

        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User Not Found with this Email"));

        String otp = otpUtil.generatedOTP();

        try {
            emailUtil.sendOtpToEmail(email,otp);
        } catch (MessagingException e) {
            throw new RuntimeException("Unable to Send OTP (PLEASE TRY AGAIN");
        }

        user.setOtp(otp);

        user.setOtpGenratedTime(LocalDateTime.now());
        userRepository.save(user);

        return "Email Sent................ OTP Generated Successfully";
    }


    @Override
    public String logIn(Login logInDTO) {

        User user = userRepository.findByEmail(logInDTO.getEmail()).orElseThrow(() -> new RuntimeException("User Not Found with this Email"));

        if(logInDTO.getPassword().equals(user.getPassword())){
            return "Password is incorrect";
        }

        return "User LogIn Successfully";
    }

    @Override
    public User getUserByEmail(String name) {
        User user = userRepository.findByEmail(name).orElseThrow(() -> new RuntimeException("User Not Found with this Email"));
        return user;
    }
}

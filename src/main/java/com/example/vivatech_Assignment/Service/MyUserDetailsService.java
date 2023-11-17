package com.example.vivatech_Assignment.Service;

import com.example.vivatech_Assignment.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("I am here ");
        Optional<com.example.vivatech_Assignment.Model.User> customer =  userRepository.findByEmail(username);

        if(customer.isPresent()){

            com.example.vivatech_Assignment.Model.User user = customer.get();
            List<GrantedAuthority> authorities = new ArrayList<>();
            SimpleGrantedAuthority sga = new SimpleGrantedAuthority(user.getRole().toString());
            authorities.add(sga);
            return new User(user.getEmail(), user.getPassword(), authorities);

        }else {
            throw new UsernameNotFoundException("User Details not found with this username: "+username);
        }



    }
}

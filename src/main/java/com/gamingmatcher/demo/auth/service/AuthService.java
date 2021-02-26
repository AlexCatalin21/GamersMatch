package com.gamingmatcher.demo.auth.service;

import com.gamingmatcher.demo.auth.dto.LoginRequest;
import com.gamingmatcher.demo.auth.dto.RegisterRequest;
import com.gamingmatcher.demo.auth.model.AppUser;
import com.gamingmatcher.demo.auth.repository.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpSession;


@Service
@AllArgsConstructor

public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUser getUserByEmail(String email) {
        return userRepository.getByEmail(email);
    }


    public ResponseEntity<String> register(RegisterRequest request){
        ResponseEntity<String> validation = validateRegister(request);
        if (validation.getStatusCode().equals(HttpStatus.OK)) {
            AppUser newAppUser = createUserFromRequest(request);

            userRepository.save(newAppUser);
        }
        return validation;
    }

    public ResponseEntity<String> login(LoginRequest request, HttpSession session) {
        ResponseEntity<String> validation = validateLogin(request);
        if (validation.getStatusCode().equals(HttpStatus.OK)) {
            AppUser user = getUserByEmail(request.getEmail());
            session.setAttribute("user", user);
        }
        return validation;
    }


    private AppUser createUserFromRequest(RegisterRequest request) {
        AppUser appUser = new AppUser();
        appUser.setUsername(request.getUsername());
        appUser.setEmail(request.getEmail());
        appUser.setPassword(passwordEncoder.encode(request.getPassword()));
        return appUser;
    }

    private ResponseEntity<String> validateRegister(RegisterRequest request) {
        AppUser appUserOptional = getUserByEmail(request.getEmail());
        if (appUserOptional != null) {
            return new ResponseEntity<>("Email already exists!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            return new ResponseEntity<>("Passwords don't match!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Registration successful", HttpStatus.OK);
    }

    private ResponseEntity<String> validateLogin(LoginRequest request) {
        String errorMessage = "Invalid credentials";
        AppUser user = getUserByEmail(request.getEmail());
        if (user == null) {
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Login successful", HttpStatus.OK);
    }

}

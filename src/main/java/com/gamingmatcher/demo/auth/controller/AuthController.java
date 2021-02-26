package com.gamingmatcher.demo.auth.controller;

import com.gamingmatcher.demo.auth.dto.LoginRequest;
import com.gamingmatcher.demo.auth.dto.RegisterRequest;
import com.gamingmatcher.demo.auth.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest request) {

        return authService.register(request);
    }


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request, HttpServletResponse response) {
        return authService.login(request, (HttpSession) response);
    }


    @GetMapping("/logout")
    public void logoutUser(HttpSession session, HttpServletResponse response) throws IOException {
        session.removeAttribute("user");
        response.sendRedirect("/");
    }
}

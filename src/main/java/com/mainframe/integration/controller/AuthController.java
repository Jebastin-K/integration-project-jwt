package com.mainframe.integration.controller;

import com.mainframe.integration.model.LoginRequest;
import com.mainframe.integration.model.LoginResponse;
import com.mainframe.integration.model.PaymentRequest;
import com.mainframe.integration.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        if ("admin".equals(request.getUsername()) && "admin123".equals(request.getPassword())) {
            String token = JwtUtil.generateToken(request.getUsername());
            return ResponseEntity.ok(new LoginResponse(token));

        }
        return ResponseEntity
                .badRequest()
                .body("Invalid Credentials");
    }
}

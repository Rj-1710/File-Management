package com.mphasis.parent.api;

import com.mphasis.parent.entity.UserDetails;
import com.mphasis.parent.exception.InvalidCredentialsException;
import com.mphasis.parent.exception.InvalidTokenException;
import com.mphasis.parent.model.dto.AuthRequest;
import com.mphasis.parent.service.UserDetailService;
import com.mphasis.parent.utility.JwtUtil;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserDetailService userDetailService;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(UserDetailService userDetailService, JwtUtil jwtUtil) {
        this.userDetailService = userDetailService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthRequest authRequest) {
        String username = authRequest.getUsername();
        String password = authRequest.getPassword();

        try {
            userDetailService.validateUser(username, password);
            String token = jwtUtil.generateToken(username);

            if (token == null || token.isEmpty()) {
                throw new InvalidTokenException("Token generation failed!");
            }

            Map<String, String> response = new HashMap<>();
            response.put("token", token);

            return ResponseEntity.ok(response); 
        } catch (IllegalArgumentException e) {
            throw new InvalidCredentialsException("Invalid username or password!");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserDetails> register(@RequestBody AuthRequest authRequest) {
        String userName = authRequest.getUsername();
        String password = authRequest.getPassword();

        try {
            UserDetails userDetails = userDetailService.registerUser(userName, password);
            return ResponseEntity.ok(userDetails);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


}

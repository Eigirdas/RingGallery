package lt.ca.javau12.ring_store.controllers;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lt.ca.javau12.ring_store.Dto.LoginRequest;
import lt.ca.javau12.ring_store.Dto.LoginResponse;
import lt.ca.javau12.ring_store.Dto.UserCreateDto;
import lt.ca.javau12.ring_store.Dto.UserDto;
import lt.ca.javau12.ring_store.services.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerNewUser(@RequestBody UserCreateDto dto) {
    	UserDto created = userService.registerNewUser(dto);
        return ResponseEntity
        		.status(HttpStatus.CREATED)
        		.body(created);
    }
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {
        try {
            LoginResponse response = userService.authenticateUser(
                request.username(),
                request.password()
            );
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
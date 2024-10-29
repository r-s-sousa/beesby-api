package com.beesby.api.controllers;

import com.beesby.api.entities.User;
import com.beesby.api.requests.AuthRequest;
import com.beesby.api.requests.UserSignUpRequest;
import com.beesby.api.responses.AuthResponse;
import com.beesby.api.responses.UserResponse;
import com.beesby.api.services.AuthService;
import com.beesby.api.services.JwtService;
import com.beesby.api.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor
@RequestMapping("/v1")
@RestController
@Tag(name = "authentication", description = "Authentication operations")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping(path = "/auth/user-sign-in")
    public ResponseEntity<AuthResponse> signIn(@RequestBody @Valid AuthRequest authRequest) {

        User user = authService.signIn(authRequest);
        String jwtToken = jwtService.generateToken(user);

        AuthResponse authResponse = AuthResponse.builder()
                .token(jwtToken)
                .expiresIn(jwtService.getExpirationTime())
                .build();

        return ResponseEntity.ok(authResponse);
    }

    @PostMapping(path = "/auth/user-sign-up")
    public ResponseEntity<UserResponse> signUp(@RequestBody @Valid UserSignUpRequest userSignUpRequest,
                                               UriComponentsBuilder uriComponentsBuilder) {
        User user = authService.signUp(userSignUpRequest);
        UserResponse userResponse = userService.mapToResponse(user);
        URI uri = uriComponentsBuilder.path("/v1/users/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(userResponse);
    }

    @GetMapping(path = "/auth/me")
    public ResponseEntity<UserResponse> me() {
        User user = authService.getMe();
        UserResponse userResponse = userService.mapToResponse(user);
        return ResponseEntity.ok(userResponse);
    }
}

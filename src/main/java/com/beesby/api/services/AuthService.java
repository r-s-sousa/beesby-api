package com.beesby.api.services;

import com.beesby.api.entities.User;
import com.beesby.api.enums.UserStatus;
import com.beesby.api.repositories.UserRepository;
import com.beesby.api.requests.AuthRequest;
import com.beesby.api.requests.UserSignUpRequest;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public AuthService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    public User signUp(@Valid UserSignUpRequest userSignUpRequest) {
        User user = User.builder()
                .name(userSignUpRequest.getName())
                .birthDate(userSignUpRequest.getBirthDate())
                .cpf(userSignUpRequest.getCpf())
                .status(UserStatus.ACTIVE.toString())
                .password(passwordEncoder.encode(userSignUpRequest.getPassword()))
                .build();

        return userService.save(user);
    }

    public User signIn(AuthRequest authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        authRequest.getCpf(),
                        authRequest.getPassword()
                )
        );

        return userRepository.findByCpf(authRequest.getCpf()).orElseThrow();
    }

    public User getMe() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }
}

package com.monicah.Umoja_Welfare.Auth;


import com.google.gson.Gson;
import com.monicah.Umoja_Welfare.Entity.Role;
import com.monicah.Umoja_Welfare.Entity.UserEntity;
import com.monicah.Umoja_Welfare.Exceptions.UserExistException;
import com.monicah.Umoja_Welfare.Repository.UserRepository;
import com.monicah.Umoja_Welfare.Utils.DBUtilService.UserDBUtilService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDBUtilService userDBUtilService;
    public AuthenticationResponse register(RegisterRequest request) {

        var userEntity = userDBUtilService.checkUserEmail(request.getEmail());
        log.info("userEntity: {}", userEntity.isPresent());
        if (userEntity.isPresent()) {
            log.info("user already exist");
            throw new UserExistException("Email already exist");
        }
        var user= UserEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN)
                .build();
        log.info("We are about to create a new user {}",new Gson().toJson(user));

        userRepository.save(user);
        log.info("User Created Successfully");
        //logic for generated token

        var jwtToken = jwtService.generateToken(new HashMap<>(),user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
        // Save the new user to the database
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(new HashMap<>(),user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}

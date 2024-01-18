package com.divya.inventorymanagement.Auth;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.divya.inventorymanagement.Exception.EmailAlreadyExistsException;
import com.divya.inventorymanagement.Repository.TokenRepository;
import com.divya.inventorymanagement.Repository.UserRepository;
import com.divya.inventorymanagement.Service.JwtService;
import com.divya.inventorymanagement.Users.Role;
import com.divya.inventorymanagement.Users.User;
import com.divya.inventorymanagement.token.TokenType;
import com.divya.inventorymanagement.token.Token;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

        private final UserRepository userRepository;
        private final TokenRepository tokenRepository;
        private final PasswordEncoder passwordEncoder;
        private final AuthenticationManager authenticationManager;
        private final JwtService jwtService;

        public AuthenticationResponse register(RegisterRequest request){
                User user = User.builder()
                                .firstname(request.getFirstname())
                                .lastname(request.getLastname())
                                .email(request.getEmail())
                                .password(passwordEncoder.encode(request.getPassword()))
                                .role(Role.valueOf(request.getRole().toUpperCase()))
                                .build();

                System.out.println(request.getRole());
                if (userRepository.existsByEmail(user.getEmail())) {
                        throw new EmailAlreadyExistsException("Email already exists");
                }
                User savedUser = userRepository.save(user);

                var jwt = jwtService.generateToken(user);
                saveUserToken(savedUser,jwt);
                return AuthenticationResponse.builder()
                                .token(jwt)
                                .build();
        }


        private void revokeAllUserTokens(User user)
        {
        List<Token> validUserTokens = tokenRepository.findActiveTokensByUserId(user.getId());
        
        if(validUserTokens.isEmpty())
        {
                return;
        }

        validUserTokens.forEach(
                t -> {
                        t.setExpired(true);
                        t.setRevoked(true);
                }
        );
        tokenRepository.saveAll(validUserTokens); 
    }

        private void saveUserToken(User user, String token) {
                var usertoken = Token.builder()
                .user(user)
                .token(token)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();
        tokenRepository.save(usertoken);
        }

        public AuthenticationResponse authenticate(AuthenticationRequest request) {
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
                var user = userRepository.findByEmail(request.getEmail())
                                .orElseThrow();
                var jwtToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user,jwtToken);
                return AuthenticationResponse.builder()
                                .token(jwtToken)
                                .build();

        }

}

package com.HealthCare.SystemApplication.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.HealthCare.SystemApplication.dto.AuthenticationRequest;
import com.HealthCare.SystemApplication.dto.AuthenticationResponse;
import com.HealthCare.SystemApplication.dto.RegisterRequest;
import com.HealthCare.SystemApplication.exception.EmailAlreadyExistsException;
import com.HealthCare.SystemApplication.model.Doctor;
import com.HealthCare.SystemApplication.model.Patient;
import com.HealthCare.SystemApplication.model.Receptionist;
import com.HealthCare.SystemApplication.model.Role;
import com.HealthCare.SystemApplication.model.Token;
import com.HealthCare.SystemApplication.model.TokenType;
import com.HealthCare.SystemApplication.model.User;
import com.HealthCare.SystemApplication.repository.DoctorRepo;
import com.HealthCare.SystemApplication.repository.PatientRepo;
import com.HealthCare.SystemApplication.repository.ReceptionistRepo;
import com.HealthCare.SystemApplication.repository.TokenRepo;
import com.HealthCare.SystemApplication.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

        private final UserRepo userRepository;
        private final PatientRepo patientRepo;
        private final DoctorRepo doctorRepo;
        private final ReceptionistRepo recepRepo;
        private final TokenRepo tokenRepository;
        private final PasswordEncoder passwordEncoder;
        private final AuthenticationManager authenticationManager;
        private final JwtService jwtService;

        public AuthenticationResponse register(RegisterRequest request) {
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
                // System.out.println("patient inserted:" +
                // (request.getRole().equals("PATIENT")));

                if (request.getRole().equals("PATIENT")) {
                        Patient patient = new Patient(user);
                        patientRepo.save(patient);
                        // System.out.println("patient inserted");
                }
                if (request.getRole().equals("DOCTOR")) {
                        Doctor doctor = new Doctor(user);
                        doctorRepo.save(doctor);
                        // System.out.println("DOCTOR inserted");

                }
                if (request.getRole().equals("RECEPTIONIST")) {
                        Receptionist recp = new Receptionist(user);
                        recepRepo.save(recp);
                        // System.out.println("RECP inserted");

                }

                var jwt = jwtService.generateToken(user);
                saveUserToken(savedUser, jwt);
                return AuthenticationResponse.builder()
                                .token(jwt)
                                .build();
        }

        // private void revokeAllUserTokens(User user) {
        // List<Token> validUserTokens =
        // tokenRepository.findActiveTokensByUserId(user.getId());

        // if (validUserTokens.isEmpty()) {
        // return;
        // }

        // validUserTokens.forEach(
        // t -> {
        // t.setExpired(true);
        // t.setRevoked(true);
        // });
        // tokenRepository.saveAll(validUserTokens);
        // }

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
                // revokeAllUserTokens(user);
                saveUserToken(user, jwtToken);
                return AuthenticationResponse.builder()
                                .token(jwtToken)
                                .build();

        }

}

package com.HealthCare.SystemApplication.Auth;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.HealthCare.SystemApplication.Controller.ReceptionistController;
import com.HealthCare.SystemApplication.Exception.EmailAlreadyExistsException;
import com.HealthCare.SystemApplication.Model.Doctor;
import com.HealthCare.SystemApplication.Model.Patient;
import com.HealthCare.SystemApplication.Model.Receptionist;
import com.HealthCare.SystemApplication.Repository.DoctorRepo;
import com.HealthCare.SystemApplication.Repository.PatientRepo;
import com.HealthCare.SystemApplication.Repository.ReceptionistRepo;
import com.HealthCare.SystemApplication.Repository.TokenRepository;
import com.HealthCare.SystemApplication.Repository.UserRepository;
import com.HealthCare.SystemApplication.Service.JwtService;
import com.HealthCare.SystemApplication.Users.Role;
import com.HealthCare.SystemApplication.Users.User;
import com.HealthCare.SystemApplication.token.Token;
import com.HealthCare.SystemApplication.token.TokenType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

        private final UserRepository userRepository;
        private final PatientRepo patientRepo;
        private final DoctorRepo doctorRepo;
        private final ReceptionistRepo recepRepo;
        private final TokenRepository tokenRepository;
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
                        System.out.println("RECP inserted");

                }

                var jwt = jwtService.generateToken(user);
                saveUserToken(savedUser, jwt);
                return AuthenticationResponse.builder()
                                .token(jwt)
                                .build();
        }

        private void revokeAllUserTokens(User user) {
                List<Token> validUserTokens = tokenRepository.findActiveTokensByUserId(user.getId());

                if (validUserTokens.isEmpty()) {
                        return;
                }

                validUserTokens.forEach(
                                t -> {
                                        t.setExpired(true);
                                        t.setRevoked(true);
                                });
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
                saveUserToken(user, jwtToken);
                return AuthenticationResponse.builder()
                                .token(jwtToken)
                                .build();

        }

}

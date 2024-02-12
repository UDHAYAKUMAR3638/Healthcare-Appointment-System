package com.HealthCare.SystemApplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.HealthCare.SystemApplication.model.User;

@Service
public interface UserService {
    public Optional<User> getUser(Integer Id);

    public List<User> getAll();

    public String deleteUser(Integer Id);

    public User updateUser(Integer id, User user);

    public Optional<User> getUserEmail(String email);

    public User updateUserByEmail(String email, User user);

}

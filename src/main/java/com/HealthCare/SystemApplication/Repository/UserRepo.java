package com.HealthCare.SystemApplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HealthCare.SystemApplication.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    public Optional<User> findByEmail(String email);

    public boolean existsByEmail(String email);

    User deleteUserByEmail(String email);

    public void deleteByEmail(String doctorEmail);

}

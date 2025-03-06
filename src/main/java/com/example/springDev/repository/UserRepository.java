package com.example.springDev.repository;

import com.example.springDev.domain.User;
import com.example.springDev.dto.UserResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUserEmail(String userEmail);


    Optional<User> findById(int id);

    UserResponseDto findByUserEmailAndPassword(final String userEmail, final String password);
}

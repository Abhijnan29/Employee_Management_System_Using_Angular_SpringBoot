package com.CRUDexample.CRUDdemo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CRUDexample.CRUDdemo.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByUsername(String username);
}

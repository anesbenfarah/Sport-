package com.example.auth_service.repositories;

import com.example.auth_service.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,Integer> {
    AppUser findByUsername(String username);
}

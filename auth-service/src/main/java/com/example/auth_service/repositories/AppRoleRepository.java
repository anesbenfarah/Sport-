package com.example.auth_service.repositories;

import com.example.auth_service.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,Integer> {
    AppRole findByRole(String role);
}

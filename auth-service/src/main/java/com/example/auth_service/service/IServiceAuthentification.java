package com.example.auth_service.service;

import com.example.auth_service.entities.AppRole;
import com.example.auth_service.entities.AppUser;

public interface IServiceAuthentification {
    public AppUser createAppUser(AppUser appUser);
    public AppRole createAppRole(AppRole appRole);
    public void addRoleToUser(String username,String role);
    public AppUser LoadUserByUserName(String username);
}

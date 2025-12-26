package com.example.auth_service.service;

import com.example.auth_service.entities.AppRole;
import com.example.auth_service.entities.AppUser;
import com.example.auth_service.repositories.AppRoleRepository;
import com.example.auth_service.repositories.AppUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ServiceAuthentification implements IServiceAuthentification{
    private final AppUserRepository appUserRepository;
    private final AppRoleRepository appRoleRepository;

    public ServiceAuthentification(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
    }

    private PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
    @Override
    public AppUser createAppUser(AppUser appUser) {
        if(appUserRepository.findByUsername(appUser.getUsername())!=null){
            throw new RuntimeException("username éja existant");}
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        if (appUser.getRoles() == null) {
            appUser.setRoles(new ArrayList<>());
        }
        return appUserRepository.save(appUser);
    }

    @Override
    public AppRole createAppRole(AppRole appRole) {
        if (appRoleRepository.findByRole(appRole.getRole()) != null) {
            throw new RuntimeException("Rôle déjà existant !");
        }
        return appRoleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String username, String role) {
        AppUser appUser=appUserRepository.findByUsername(username);
        if (appUser == null) throw new RuntimeException("Utilisateur introuvable !");
        AppRole appRole=appRoleRepository.findByRole(role);
        if (appRole == null) throw new RuntimeException("Rôle introuvable !");
        appUser.getRoles().add(appRole);
        appUserRepository.save(appUser);

    }

    @Override
    public AppUser LoadUserByUserName(String username) {
        return appUserRepository.findByUsername(username);
    }
}

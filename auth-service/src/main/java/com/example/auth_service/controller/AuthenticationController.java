package com.example.auth_service.controller;

import com.example.auth_service.entities.AppRole;
import com.example.auth_service.entities.AppUser;
import com.example.auth_service.service.IServiceAuthentification;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/api/auth")
public class AuthenticationController {
    private final IServiceAuthentification iServiceAuthentication;

    public AuthenticationController(IServiceAuthentification iServiceAuthentication) {
        this.iServiceAuthentication = iServiceAuthentication;
    }

    @PostMapping("/addUser")
    public AppUser addUser(@RequestBody AppUser appUser) {
        return iServiceAuthentication.createAppUser(appUser);
    }
    @PostMapping("/addRole")
    public AppRole addRole(@RequestBody AppRole appRole) {
        return iServiceAuthentication.createAppRole(appRole);
    }
    @PostMapping("/addRoleToUser")
    public void addRoleToUser(@RequestParam String username, @RequestParam String role) {
        iServiceAuthentication.addRoleToUser(username, role);
    }
}

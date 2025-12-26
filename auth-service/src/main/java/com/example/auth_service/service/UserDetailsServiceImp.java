package com.example.auth_service.service;

import com.example.auth_service.entities.AppUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
    private final IServiceAuthentification iServiceAuthentication;

    public UserDetailsServiceImp(IServiceAuthentification iServiceAuthentication) {
        this.iServiceAuthentication = iServiceAuthentication;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = iServiceAuthentication.LoadUserByUserName(username);

        if (appUser == null) {
            throw new UsernameNotFoundException(username+" Utilisateur non trouvé !");
        }
        String[] roles=appUser.getRoles().stream().map(u->u.getRole()).toArray(String[]::new);
        return User
                .withUsername(appUser.getUsername())
                .password(appUser.getPassword())
                .roles(roles)
                .build();
    }
}

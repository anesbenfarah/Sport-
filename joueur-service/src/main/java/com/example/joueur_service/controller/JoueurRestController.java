package com.example.joueur_service.controller;


import com.example.joueur_service.entity.Joueur;
import com.example.joueur_service.service.IServiceJoueur;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/joueur/")
public class JoueurRestController {
    IServiceJoueur iServiceJoueur;

    @PostMapping("add")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Joueur add(@RequestBody Joueur joueur){
        return iServiceJoueur.addJoueur(joueur);
    }

    @GetMapping("all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Joueur> all(){
        return iServiceJoueur.getAllJoueurs();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Optional<Joueur> find(@PathVariable int id){
        return iServiceJoueur.getJoueurById(id);
    }
}

package com.example.joueur_service.controller;


import com.example.joueur_service.entity.Joueur;
import com.example.joueur_service.service.IServiceJoueur;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/joueur/")
public class JoueurRestController {
    IServiceJoueur iServiceJoueur;

    @PostMapping("add")
    public Joueur add(@RequestBody Joueur joueur){
        return iServiceJoueur.addJoueur(joueur);
    }

    @GetMapping("all")
    public List<Joueur> all(){
        return iServiceJoueur.getAllJoueurs();
    }
}

package com.example.joueur_service.service;

import com.example.joueur_service.entity.Joueur;

import java.util.List;

public interface IServiceJoueur {


   public Joueur addJoueur(Joueur joueur);

    public List<Joueur> getAllJoueurs();
}

package com.example.joueur_service.service;

import com.example.joueur_service.entity.Joueur;
import com.example.joueur_service.repository.JoueurRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@AllArgsConstructor
public class ServiceJoueur implements IServiceJoueur{
    JoueurRepository joueurRepository;

    @Override
    public Joueur addJoueur(Joueur joueur) {
        return joueurRepository.save(joueur);
    }
    @Override
    public List<Joueur> getAllJoueurs() {
        return joueurRepository.findAll();
    }

}

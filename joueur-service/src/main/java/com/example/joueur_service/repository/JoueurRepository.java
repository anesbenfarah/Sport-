package com.example.joueur_service.repository;

import com.example.joueur_service.entity.Joueur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JoueurRepository extends JpaRepository<Joueur, Integer> {
}

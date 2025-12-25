package com.example.equipe_service.service;



import com.example.equipe_service.entity.Equipe;

import java.util.List;
import java.util.Optional;

public interface IServiceEquipe {


   public Equipe addEquipe(Equipe equipe);

    public List<Equipe> getAllEquipe();

    Optional<Equipe> getEquipeById(int id);
}

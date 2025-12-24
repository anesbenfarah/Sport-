package com.example.equipe_service.service;



import com.example.equipe_service.entity.Equipe;

import java.util.List;

public interface IServiceEquipe {


   public Equipe addEquipe(Equipe equipe);

    public List<Equipe> getAllEquipe();
}

package com.example.equipe_service.service;


import com.example.equipe_service.entity.Equipe;
import com.example.equipe_service.repository.EquipeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ServiceEquipe implements IServiceEquipe {
    EquipeRepository equipeRepository;

    @Override
    public Equipe addEquipe(Equipe equipe) {
        return equipeRepository.save(equipe);
    }
    @Override
    public List<Equipe> getAllEquipe() {
        return equipeRepository.findAll();
    }

}

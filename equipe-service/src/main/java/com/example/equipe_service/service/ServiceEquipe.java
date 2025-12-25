package com.example.equipe_service.service;


import com.example.equipe_service.entity.Equipe;
import com.example.equipe_service.repository.EquipeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    @Override
    public Optional<Equipe> getEquipeById(int id) {
        return equipeRepository.findById(id);
    }

}

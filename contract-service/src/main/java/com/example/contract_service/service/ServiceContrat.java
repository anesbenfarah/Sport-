package com.example.contract_service.service;

import com.example.contract_service.clients.EquipeClient;
import com.example.contract_service.clients.JoueurClient;
import com.example.contract_service.dto.ContratRequestDTO;
import com.example.contract_service.dto.ContratResponseDTO;
import com.example.contract_service.entity.Contrat;
import com.example.contract_service.mapper.ContratMapper;
import com.example.contract_service.model.Equipe;
import com.example.contract_service.model.Joueur;
import com.example.contract_service.repository.ContratRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ServiceContrat implements IServiceContrat {

    private final ContratRepository contratRepository;
    private final JoueurClient joueurClient;
    private final EquipeClient equipeClient;

    @Override
    public ContratResponseDTO createContrat(ContratRequestDTO dto) {

        // Vérification existence joueur / équipe
        Joueur joueur = joueurClient.getJoueurById(dto.getJoueurId());
        Equipe equipe = equipeClient.getEquipeById(dto.getEquipeId());

        if (joueur == null || equipe == null) {
            return null;
        }

        Contrat contrat = ContratMapper.toEntity(dto);
        Contrat saved = contratRepository.save(contrat);

        enrichirContrat(saved);
        return ContratMapper.toDTO(saved);
    }

    @Override
    public List<ContratResponseDTO> getAllContrats() {
        return contratRepository.findAll()
                .stream()
                .peek(this::enrichirContrat)
                .map(ContratMapper::toDTO)
                .toList();
    }

    @Override
    public ContratResponseDTO getContratById(int id) {
        return contratRepository.findById(id)
                .map(contrat -> {
                    enrichirContrat(contrat);
                    return ContratMapper.toDTO(contrat);
                })
                .orElse(null);
    }

    @Override
    public ContratResponseDTO updateContrat(int id, ContratRequestDTO dto) {

        if (!contratRepository.existsById(id)) return null;

        Contrat contrat = ContratMapper.toEntity(dto);
        contrat.setId(id);

        Contrat updated = contratRepository.save(contrat);
        enrichirContrat(updated);

        return ContratMapper.toDTO(updated);
    }

    @Override
    public void deleteContrat(int id) {

    }


    // Méthode privée d'enrichissement
    private void enrichirContrat(Contrat contrat) {
        if (contrat == null) return;

        try {
            Joueur joueur = joueurClient.getJoueurById(contrat.getJoueurId());
            Equipe equipe = equipeClient.getEquipeById(contrat.getEquipeId());

            contrat.setJoueur(joueur);
            contrat.setEquipe(equipe);

        } catch (Exception e) {
            log.error("Erreur enrichissement contrat {} : {}", contrat.getId(), e.getMessage());
            contrat.setJoueur(null);
            contrat.setEquipe(null);
        }
    }

}
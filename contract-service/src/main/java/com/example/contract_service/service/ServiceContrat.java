package com.example.contract_service.service;

import com.example.contract_service.clients.EquipeClient;
import com.example.contract_service.clients.JoueurClient;
import com.example.contract_service.entity.Contrat;
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
    public Contrat createContrat(Contrat contrat) {
        // Vérification que le joueur et l'équipe existent
        Optional<Joueur> joueurOpt = Optional.ofNullable(joueurClient.getJoueurById(contrat.getJoueurId()));
        Optional<Equipe> equipeOpt = Optional.ofNullable(equipeClient.getEquipeById(contrat.getEquipeId()));

        if (joueurOpt.isEmpty() || equipeOpt.isEmpty()) {
            log.warn("Création contrat échouée : joueurId {} ou equipeId {} introuvable", contrat.getJoueurId(), contrat.getEquipeId());
            return null; // Le controller renverra BAD_REQUEST
        }

        // Sauvegarde
        Contrat saved = contratRepository.save(contrat);

        // Enrichissement immédiat après création
        enrichirContrat(saved);

        return saved;
    }

    @Override
    public List<Contrat> getAllContrats() {
        List<Contrat> contrats = contratRepository.findAll();
        contrats.forEach(this::enrichirContrat);
        return contrats;
    }

    @Override
    public Contrat getContratById(int id) {
        Optional<Contrat> optional = contratRepository.findById(id);
        if (optional.isPresent()) {
            Contrat contrat = optional.get();
            enrichirContrat(contrat);
            return contrat;
        }
        return null;
    }

    @Override
    public Contrat updateContrat(int id, Contrat contratDetails) {
        if (!contratRepository.existsById(id)) {
            return null;
        }

        // Optionnel : vérifier à nouveau joueur et équipe lors de mise à jour
        Optional<Joueur> joueurOpt = Optional.ofNullable(joueurClient.getJoueurById(contratDetails.getJoueurId()));
        Optional<Equipe> equipeOpt = Optional.ofNullable(equipeClient.getEquipeById(contratDetails.getEquipeId()));

        if (joueurOpt.isEmpty() || equipeOpt.isEmpty()) {
            return null;
        }

        contratDetails.setId(id);
        Contrat updated = contratRepository.save(contratDetails);
        enrichirContrat(updated);
        return updated;
    }

    @Override
    public void deleteContrat(int id) {
        contratRepository.deleteById(id);
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
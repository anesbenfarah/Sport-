package com.example.contract_service.controller;

import com.example.contract_service.entity.Contrat;
import com.example.contract_service.service.IServiceContrat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contrats")
public class ContratRestController {

    @Autowired
    private IServiceContrat iServiceContrat;

    // 📋 Tous les contrats (déjà enrichis avec joueur et équipe)
    @GetMapping
    public List<Contrat> getAllContrats() {
        return iServiceContrat.getAllContrats();
    }

    // 🔍 Un contrat par ID (déjà enrichi)
    @GetMapping("/{id}")
    public ResponseEntity<Contrat> getContratById(@PathVariable int id) {
        Contrat contrat = iServiceContrat.getContratById(id);
        return contrat != null ? ResponseEntity.ok(contrat) : ResponseEntity.notFound().build();
    }

    // ➕ Créer un nouveau contrat
    @PostMapping
    public ResponseEntity<?> createContrat(@RequestBody Contrat contrat) {
        Contrat saved = iServiceContrat.createContrat(contrat);

        if (saved == null) {
            return new ResponseEntity<>(
                    "Joueur ou Équipe introuvable",
                    HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // ✏️ Mettre à jour un contrat
    @PutMapping("/{id}")
    public ResponseEntity<Contrat> updateContrat(@PathVariable int id, @RequestBody Contrat contrat) {
        Contrat updated = iServiceContrat.updateContrat(id, contrat);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    // 🗑️ Supprimer un contrat
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContrat(@PathVariable int id) {
        iServiceContrat.deleteContrat(id);
        return ResponseEntity.noContent().build();
    }
}
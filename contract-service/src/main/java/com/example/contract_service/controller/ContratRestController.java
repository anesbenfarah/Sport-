package com.example.contract_service.controller;

import com.example.contract_service.dto.ContratRequestDTO;
import com.example.contract_service.dto.ContratResponseDTO;
import com.example.contract_service.entity.Contrat;
import com.example.contract_service.service.IServiceContrat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/contrats")
public class ContratRestController {

    @Autowired
    private IServiceContrat service;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<ContratResponseDTO> getAll() {
        return service.getAllContrats();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ContratResponseDTO> getById(@PathVariable int id) {
        ContratResponseDTO dto = service.getContratById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> create(@RequestBody ContratRequestDTO dto) {
        ContratResponseDTO created = service.createContrat(dto);
        if (created == null) {
            return ResponseEntity.badRequest().body("Joueur ou Équipe introuvable");
        }
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ContratResponseDTO> update(
            @PathVariable int id,
            @RequestBody ContratRequestDTO dto) {

        ContratResponseDTO updated = service.updateContrat(id, dto);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.deleteContrat(id);
        return ResponseEntity.noContent().build();
    }
}

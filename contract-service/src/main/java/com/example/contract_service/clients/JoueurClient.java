package com.example.contract_service.clients;

import com.example.contract_service.model.Joueur;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "joueur-service", url = "http://localhost:8081")
public interface JoueurClient {
    @GetMapping("/api/joueur/{id}")  // Adapter le chemin selon ton joueur-service
    Joueur getJoueurById(@PathVariable("id") int id);
}
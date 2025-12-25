package com.example.contract_service.clients;

import com.example.contract_service.model.Equipe;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "equipe-service", url = "http://localhost:8082")
public interface EquipeClient {
    @GetMapping("/api/equipe/{id}")  // Adapter le chemin selon ton equipe-service
    Equipe getEquipeById(@PathVariable("id") int id);
}
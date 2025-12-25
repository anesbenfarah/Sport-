package com.example.contract_service.repository;

import com.example.contract_service.entity.Contrat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratRepository extends JpaRepository<Contrat, Integer> {
}

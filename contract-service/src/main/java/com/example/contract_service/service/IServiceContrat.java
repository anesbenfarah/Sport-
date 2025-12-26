package com.example.contract_service.service;
import com.example.contract_service.dto.ContratRequestDTO;
import com.example.contract_service.dto.ContratResponseDTO;

import java.util.List;

public interface IServiceContrat {
    ContratResponseDTO createContrat(ContratRequestDTO dto);
    List<ContratResponseDTO> getAllContrats();
    ContratResponseDTO getContratById(int id);
    ContratResponseDTO updateContrat(int id, ContratRequestDTO dto);
    void deleteContrat(int id);
}
package com.example.contract_service.mapper;

import com.example.contract_service.dto.ContratRequestDTO;
import com.example.contract_service.dto.ContratResponseDTO;
import com.example.contract_service.entity.Contrat;

public class ContratMapper {


    public static Contrat toEntity(ContratRequestDTO dto) {
        return new Contrat(
                0,
                dto.getDateDebut(),
                dto.getDateFin(),
                dto.getSalaire(),
                dto.getJoueurId(),
                dto.getEquipeId(),
                null,
                null
        );
    }


    public static ContratResponseDTO toDTO(Contrat contrat) {
        return new ContratResponseDTO(
                contrat.getId(),
                contrat.getDateDebut(),
                contrat.getDateFin(),
                contrat.getSalaire(),
                contrat.getJoueur(),
                contrat.getEquipe()
        );
    }
}

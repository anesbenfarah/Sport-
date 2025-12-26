package com.example.contract_service.dto;

import com.example.contract_service.model.Equipe;
import com.example.contract_service.model.Joueur;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContratResponseDTO {

    private int id;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private double salaire;

    private Joueur joueur;
    private Equipe equipe;
}
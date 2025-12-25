package com.example.contract_service.service;

import com.example.contract_service.entity.Contrat;
import java.util.List;

public interface IServiceContrat {
    Contrat createContrat(Contrat contrat);
    List<Contrat> getAllContrats();
    Contrat getContratById(int id);
    Contrat updateContrat(int id, Contrat contrat);
    void deleteContrat(int id);
}
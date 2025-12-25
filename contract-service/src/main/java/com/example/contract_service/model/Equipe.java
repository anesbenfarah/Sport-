package com.example.contract_service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Equipe {
    private int id;
    private String nom;
    private String pays;

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPays() {
        return pays;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }
}

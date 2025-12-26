package com.example.equipe_service.controller;



import com.example.equipe_service.entity.Equipe;
import com.example.equipe_service.service.IServiceEquipe;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/equipe/")
public class EquipeRestController {
    IServiceEquipe iServiceEquipe;

    @PostMapping("add")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Equipe add(@RequestBody Equipe equipe){
        return iServiceEquipe.addEquipe(equipe);
    }

    @GetMapping("all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Equipe> all(){
        return iServiceEquipe.getAllEquipe();
    }
    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Optional<Equipe> find(@PathVariable int id){
        return iServiceEquipe.getEquipeById(id);
    }
}

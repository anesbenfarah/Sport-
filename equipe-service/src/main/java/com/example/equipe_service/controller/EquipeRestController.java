package com.example.equipe_service.controller;



import com.example.equipe_service.entity.Equipe;
import com.example.equipe_service.service.IServiceEquipe;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/equipe/")
public class EquipeRestController {
    IServiceEquipe iServiceEquipe;

    @PostMapping("add")
    public Equipe add(@RequestBody Equipe equipe){
        return iServiceEquipe.addEquipe(equipe);
    }

    @GetMapping("all")
    public List<Equipe> all(){
        return iServiceEquipe.getAllEquipe();
    }
    @GetMapping("{id}")
    public Optional<Equipe> find(@PathVariable int id){
        return iServiceEquipe.getEquipeById(id);
    }
}

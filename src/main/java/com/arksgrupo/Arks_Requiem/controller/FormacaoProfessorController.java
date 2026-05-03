package com.arksgrupo.Arks_Requiem.controller;

import com.arksgrupo.Arks_Requiem.dto.FormacaoProfessorDTO;
import com.arksgrupo.Arks_Requiem.service.FormacaoProfessorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professor/formacao")
public class FormacaoProfessorController {
    @Autowired
    private FormacaoProfessorService service;
    
    // ADICIONAR FORMAÇÃO
    @PostMapping
    public FormacaoProfessorDTO salvar( @RequestBody FormacaoProfessorDTO dto){
        return service.salvar(dto);
    }

    // LISTAR FORMAÇÕES DO PROFESSOR
    @GetMapping("/professor/{matricula}")
    public List<FormacaoProfessorDTO>
    listarPorProfessor(@PathVariable Integer matricula){
        return service.listarPorProfessor(matricula);
    }

    // DELETAR FORMAÇÃO
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        service.deletar(id);
    }
}

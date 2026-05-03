package com.arksgrupo.Arks_Requiem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arksgrupo.Arks_Requiem.model.MatrizDisciplina;
import com.arksgrupo.Arks_Requiem.service.MatrizDisciplinaService;


@RestController
@RequestMapping("/matriz-disciplina")
public class MatrizDisciplinaController {
    @Autowired
    private MatrizDisciplinaService service;

     // Endpoint para adicionar disciplina na matriz curricular
    @PostMapping("/{matrizId}/disciplina/{disciplinaId}")
    public MatrizDisciplina adicionar(@PathVariable Long matrizId,@PathVariable Long disciplinaId){
        return service.adicionarDisciplina(matrizId, disciplinaId);
    }
     // Endpoint para listar disciplinas da matriz curricular
    @GetMapping("/{matrizId}")
    public List<MatrizDisciplina> listar(@PathVariable Long matrizId){
        return service.listarDisciplinas(matrizId);
    }
}
package com.arksgrupo.Arks_Requiem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arksgrupo.Arks_Requiem.dto.ProfessorDTO;
import com.arksgrupo.Arks_Requiem.service.ProfessorService;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private ProfessorService service;

    // Criar professor
    @PostMapping
    public ProfessorDTO criar(@RequestBody ProfessorDTO dto){

        return service.salvar(dto);
    }


    // Listar professores
    @GetMapping
    public List<ProfessorDTO> listar(){
        return service.listar();
    }


    // Buscar professor por matrícula
    @GetMapping("/{matricula}")
    public ProfessorDTO buscar(@PathVariable Long matricula){
        return service.buscarPorMatricula(matricula);
    }


    // Atualizar professor
    @PutMapping("/{matricula}")
    public ProfessorDTO atualizar(@PathVariable Long matricula,@RequestBody ProfessorDTO dto){
        return service.atualizar(matricula, dto);
    }

    // Inativar professor
    @PatchMapping("/{matricula}/inativar")
    public ResponseEntity<Void> inativar (@PathVariable("matricula") Long matricula){
        service.inativar(matricula);
        return ResponseEntity.noContent().build();
    }

    //reativar professor
    @PatchMapping("/{matricula}/reativar")
    public ResponseEntity<Void> reativar (@PathVariable("matricula") Long matricula){
        service.reativar(matricula);
        return ResponseEntity.noContent().build();
    }
}
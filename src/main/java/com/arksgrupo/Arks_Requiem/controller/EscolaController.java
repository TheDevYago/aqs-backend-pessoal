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

import com.arksgrupo.Arks_Requiem.dto.EscolaDTO;
import com.arksgrupo.Arks_Requiem.service.EscolaService;

@RestController
@RequestMapping("/escola")
public class EscolaController{
    @Autowired
    private EscolaService service;
    
   // CRIAR
    @PostMapping
    public EscolaDTO criar(@RequestBody EscolaDTO dto){
        return service.salvar(dto);
    }

    // LISTAR
    @GetMapping
    public List<EscolaDTO> listar(){
        return service.listar();
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public EscolaDTO buscar(@PathVariable Long id){
        return service.buscarPorId(id);
    }

    // ATUALIZAR
    @PutMapping("/{id}")
    public EscolaDTO atualizar(@PathVariable Long id, @RequestBody EscolaDTO dto){
        return service.atualizar(id, dto);
    }

    // INATIVAR
    @PatchMapping("/{id}/inativar") // [AJUSTE] é mais recomendado
    public ResponseEntity<Void> inativar(@PathVariable Long id){
        service.inativar(id);
        return ResponseEntity.noContent().build();
    }
}

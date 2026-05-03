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

import com.arksgrupo.Arks_Requiem.dto.IesDTO;
import com.arksgrupo.Arks_Requiem.service.IesService;

@RestController
@RequestMapping("/ies")
public class IesController {
    @Autowired
    private IesService service;

    // CRIAR
    @PostMapping
    public ResponseEntity<IesDTO> criar(@RequestBody IesDTO dto){
        return ResponseEntity.ok(service.salvar(dto));
    }


    // LISTAR
    @GetMapping
    public ResponseEntity<List<IesDTO>> listar(){
        return ResponseEntity.ok(service.listar());
    }


    // BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<IesDTO> buscar(@PathVariable Long id){
        return ResponseEntity.ok(service.buscarPorId(id));
    }


    // ATUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<IesDTO> atualizar(@PathVariable Long id, @RequestBody IesDTO dto){
        return ResponseEntity.ok(service.atualizar(id, dto));
    }


    // DESATIVAR
    @PatchMapping("/{id}/inativar") // [AJUSTE] é mais recomendado
    public ResponseEntity<Void> desativar(@PathVariable("id") Long id){
        service.desativar(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/reativar")
    public ResponseEntity<Void> reativar(@PathVariable("id") Long id){
        service.reativar(id);

        return ResponseEntity.noContent().build();
    }
}

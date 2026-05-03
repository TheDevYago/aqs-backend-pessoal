package com.arksgrupo.Arks_Requiem.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.arksgrupo.Arks_Requiem.dto.MatrizDTO;
import com.arksgrupo.Arks_Requiem.service.MatrizService;

@RestController
@RequestMapping("/matriz")
public class MatrizController {
    @Autowired
    private MatrizService service;

    // Endpoint para criar matriz vinculada a um curso
    @PostMapping("/curso/{cursoId}")
    public MatrizDTO criar( @PathVariable MatrizDTO dto) {
        return service.salvar(dto);
    }

    @GetMapping
    public List<MatrizDTO> listarTodas(){
        return service.listarTodas();
    }

     @GetMapping("/{id}")
    public MatrizDTO buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id);
    }

    @PostMapping("/{id}/disciplinas")
    public void vincularDisciplinas(
        @PathVariable Long id,@RequestBody List<Long>disciplinasIds){
        service.vincularDisciplinas(id, disciplinasIds);
    }

    @PutMapping("/{id}")
    public MatrizDTO atualizar(@PathVariable Long id,@RequestBody MatrizDTO dto){
        return service.atualizar(id, dto);
    }

    @PatchMapping("/{id}/inativar") // [AJUSTE]
    public ResponseEntity<Void> inativar(@PathVariable Long id){
        service.inativar(id);
        return ResponseEntity.noContent().build();
    }
}
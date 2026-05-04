package com.arksgrupo.Arks_Requiem.controller;

import com.arksgrupo.Arks_Requiem.dto.DisciplinaDTO;
import com.arksgrupo.Arks_Requiem.service.DisciplinaService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/disciplina")
public class DisciplinaController {
    @Autowired
    private DisciplinaService service;

    // CRIAR DISCIPLINA
    @PostMapping
    public DisciplinaDTO salvar(@RequestBody DisciplinaDTO dto){
        return service.salvar(dto);
    }

    // LISTAR TODAS
    @GetMapping
    public List<DisciplinaDTO> listarTodos(){
        return service.listarTodos();
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public DisciplinaDTO buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id);
    }

     // ATUALIZAR DISCIPLINA
    @PutMapping("/{id}")
    public DisciplinaDTO atualizar(@PathVariable Long id,@RequestBody DisciplinaDTO dto){
        return service.atualizar(id, dto);
    }

    // INATIVAR DISCIPLINA
    @PatchMapping("/{id}/inativar") // [AJUSTE]
    public ResponseEntity<Void> inativar(@PathVariable Long id){
        service.inativar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/reativar")
    public ResponseEntity<Void> reativar(@PathVariable Long id){
        service.reativar(id);
        return ResponseEntity.noContent().build();
    }
}
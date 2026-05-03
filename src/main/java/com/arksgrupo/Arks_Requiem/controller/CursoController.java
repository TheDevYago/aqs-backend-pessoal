package com.arksgrupo.Arks_Requiem.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.arksgrupo.Arks_Requiem.dto.CursoDTO;
import com.arksgrupo.Arks_Requiem.service.CursoService;

@RestController
@RequestMapping("/curso")
public class CursoController {
    @Autowired
    private CursoService service;

    @PostMapping
    public CursoDTO criar(@RequestBody CursoDTO dto){
        return service.salvar(dto);
    }

    @GetMapping
    public List<CursoDTO> listar(){
        return service.listar();
    }

    @GetMapping("/{id}")
    public CursoDTO buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public CursoDTO atualizar(@PathVariable Long id, @RequestBody CursoDTO curso) {
        return service.atualizar(id, curso);
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        service.inativar(id);
        return ResponseEntity.noContent().build();
    }
}

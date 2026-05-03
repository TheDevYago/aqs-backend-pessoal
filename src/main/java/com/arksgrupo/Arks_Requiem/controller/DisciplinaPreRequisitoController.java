package com.arksgrupo.Arks_Requiem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.arksgrupo.Arks_Requiem.model.DisciplinaPreRequisito;
import com.arksgrupo.Arks_Requiem.service.DisciplinaPreRequisitoService;
import java.util.List;

@RestController
@RequestMapping("/disciplina-pre-requisitos")
@CrossOrigin(origins = "*")
public class DisciplinaPreRequisitoController {

    @Autowired
    private DisciplinaPreRequisitoService service;

    @PostMapping
    public ResponseEntity<DisciplinaPreRequisito> criar(@RequestBody DisciplinaPreRequisito data) {
        return ResponseEntity.ok(service.salvar(data));
    }

    @GetMapping
    public List<DisciplinaPreRequisito> listar() {
        return service.listarTodos();
    }
}
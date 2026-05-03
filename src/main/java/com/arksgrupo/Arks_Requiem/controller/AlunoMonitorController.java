package com.arksgrupo.Arks_Requiem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.arksgrupo.Arks_Requiem.dto.AlunoMonitorDTO;
import com.arksgrupo.Arks_Requiem.service.AlunoMonitorService;

@RestController
@RequestMapping("/alunos-monitor")
public class AlunoMonitorController {
    @Autowired
    private AlunoMonitorService service;

    // CRIAR
    @PostMapping
    public ResponseEntity<AlunoMonitorDTO> criar(@RequestBody AlunoMonitorDTO dto){
        return ResponseEntity.ok(service.salvar(dto));
    }

    // LISTAR
    @GetMapping
    public ResponseEntity<List<AlunoMonitorDTO>> listar(){
        return ResponseEntity.ok(service.listar());
    }


    // BUSCAR POR MATRICULA
    @GetMapping("/{matricula}")
    public ResponseEntity<AlunoMonitorDTO> buscar(@PathVariable Long matricula){
        return ResponseEntity.ok(service.buscarPorMatricula(matricula));
    }


    // ATUALIZAR
    @PutMapping("/{matricula}")
    public ResponseEntity<AlunoMonitorDTO> atualizar(@PathVariable Long matricula, @RequestBody AlunoMonitorDTO dto){
        return ResponseEntity.ok(service.atualizar(matricula, dto));
    }


    // INATIVAR
    @PatchMapping("/{matricula}/inativar")
    public ResponseEntity<Void> inativar(@PathVariable Long matricula){
        service.inativar(matricula);

        return ResponseEntity.noContent().build();
    }
}

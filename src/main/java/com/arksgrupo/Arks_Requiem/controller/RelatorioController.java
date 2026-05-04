package com.arksgrupo.Arks_Requiem.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.arksgrupo.Arks_Requiem.dto.RelatorioFiltroDTO;
import com.arksgrupo.Arks_Requiem.model.Relatorio;
import com.arksgrupo.Arks_Requiem.service.RelatorioService;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    @Autowired
    private RelatorioService service;

    @GetMapping("/download/{tipoId}")
    public ResponseEntity<byte[]> downloadRelatorio(
            @PathVariable String tipoId,
            @ModelAttribute RelatorioFiltroDTO filtro) { // Agrupa os params no DTO[cite: 13]
        return service.gerarArquivoRelatorio(tipoId, filtro);
    }
    
    @PostMapping
    public ResponseEntity<Relatorio> criar(@RequestBody Relatorio relatorio) {
        Relatorio salvo = service.salvaRelatorio(relatorio);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping
    public List<Relatorio> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Relatorio> buscar(@PathVariable Long id) {
        Relatorio relatorio = service.buscarId(id);
        if (relatorio != null) {
            return ResponseEntity.ok(relatorio);
        }
        return ResponseEntity.notFound().build();
    }

    
        

    @PutMapping("/{id}")
    public ResponseEntity<Relatorio> atualizar(@PathVariable Long id, @RequestBody Relatorio relatorio) {
        Relatorio atualizado = service.atualizar(id, relatorio);
        if (atualizado != null) {
            return ResponseEntity.ok(atualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
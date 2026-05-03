package com.arksgrupo.Arks_Requiem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.arksgrupo.Arks_Requiem.dto.MonitoriaDTO;
import com.arksgrupo.Arks_Requiem.service.MonitoriaService;

@RestController
@RequestMapping("/monitorias")
public class MonitoriaController {

    @Autowired
    private MonitoriaService service;
    
    // CRIAR
    @PostMapping
    public MonitoriaDTO salvar(@RequestBody MonitoriaDTO dto){
        return service.salvar(dto);
    }


    // LISTAR TODOS
    @GetMapping
    public List<MonitoriaDTO> listarTodas(){
        return service.listarTodas();
    }


    // BUSCAR POR ID
    @GetMapping("/{id}")
    public MonitoriaDTO buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id);
    }


    // ATUALIZAR
    @PutMapping("/{id}")
    public MonitoriaDTO atualizar(@PathVariable Long id,@RequestBody MonitoriaDTO dto){
        return service.atualizar(id, dto);
    }


    // INATIVAR
    @PatchMapping("/{id}/inativar")
    public ResponseEntity<Void> inativar(@PathVariable Long id){

        service.inativar(id);
        return ResponseEntity.noContent().build();
    }

}

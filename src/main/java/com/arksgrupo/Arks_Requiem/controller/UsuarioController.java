package com.arksgrupo.Arks_Requiem.controller;

import com.arksgrupo.Arks_Requiem.dto.UsuarioDTO;
import com.arksgrupo.Arks_Requiem.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    // LISTAR TODOS
    @GetMapping
    public List<UsuarioDTO> listar(){
        return service.listarTodos();
    }


    // BUSCAR POR ID
    @GetMapping("/{id}")
    public UsuarioDTO buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id);

    }

    // DELETAR
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        service.deletar(id);
    }
}

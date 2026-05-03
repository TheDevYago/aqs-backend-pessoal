package com.arksgrupo.Arks_Requiem.service;

import com.arksgrupo.Arks_Requiem.dto.UsuarioDTO;
import com.arksgrupo.Arks_Requiem.model.Usuario;
import com.arksgrupo.Arks_Requiem.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    // LISTAR TODOS USUÁRIOS
    public List<UsuarioDTO> listarTodos(){
        return repository.findAll().stream().map(this::converterParaDTO).collect(Collectors.toList());
    }

    // BUSCAR POR ID
    public UsuarioDTO buscarPorId(Long id){
        Usuario usuario = repository.findById(id).orElseThrow();

        return converterParaDTO(usuario);
    }

    // DELETAR
    public void deletar(Long id){
        repository.deleteById(id);
    }


    // CONVERSOR ENTITY → DTO
    private UsuarioDTO converterParaDTO(Usuario usuario){
        return new UsuarioDTO(usuario.getId(), usuario.getLogin(),usuario.getRole());
    }
}

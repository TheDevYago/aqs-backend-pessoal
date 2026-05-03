package com.arksgrupo.Arks_Requiem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arksgrupo.Arks_Requiem.dto.IesDTO;
import com.arksgrupo.Arks_Requiem.exception.ResourceNotFoundException;
import com.arksgrupo.Arks_Requiem.model.Ies;
import com.arksgrupo.Arks_Requiem.repository.IesRepository;

@Service
public class IesService {
    @Autowired
    private IesRepository repository;

    // SALVAR
    public IesDTO salvar(IesDTO dto){
        Ies ies = converterParaEntity(dto);
        ies = repository.save(ies);

        return converterParaDTO(ies);
    }

    // LISTAR
    public List<IesDTO> listar(){
        return repository.findAll().stream().map(this::converterParaDTO).collect(Collectors.toList());
    }


    // BUSCAR POR ID
    public IesDTO buscarPorId(Long id){
        Ies ies = repository.findById(id).orElseThrow(() ->new ResourceNotFoundException("IES não encontrada com id: " + id));
        
        return converterParaDTO(ies);
    }


    // ATUALIZAR
    public IesDTO atualizar(Long id, IesDTO dto){
        Ies ies = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("IES não encontrada com id: " + id));

        ies.setNome(dto.getNome());
        ies.setEndereco(dto.getEndereco());
        ies.setTelefone(dto.getTelefone());
        ies.setStatus(dto.getStatus() != null ? dto.getStatus() : true);

        ies = repository.save(ies);

        return converterParaDTO(ies);
    }


    // DESATIVAR (SOFT DELETE)
    @Transactional // [AJUSTE]
    public void desativar(Long id){
        Ies ies = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("IES não encontrada com id: " + id));

        ies.setStatus(false);
        
        repository.save(ies);
    }

    @Transactional
    public void reativar(Long id) {
        Ies ies = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("IES não encontrada com id: " + id));
        ies.setStatus(true);
    
        repository.save(ies);
    }


    // DTO → ENTITY
    private Ies converterParaEntity(IesDTO dto){
        Ies ies = new Ies();

        ies.setNome(dto.getNome());
        ies.setEndereco(dto.getEndereco());
        ies.setTelefone(dto.getTelefone());
        ies.setStatus(dto.getStatus() != null ? dto.getStatus() : true);

        return ies;
    }


    // ENTITY → DTO
    private IesDTO converterParaDTO(Ies ies){
        IesDTO dto = new IesDTO();

        dto.setId(ies.getId());
        dto.setNome(ies.getNome());
        dto.setEndereco(ies.getEndereco());
        dto.setTelefone(ies.getTelefone());
        dto.setStatus(ies.getStatus());

        return dto;
    }
}

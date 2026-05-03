package com.arksgrupo.Arks_Requiem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arksgrupo.Arks_Requiem.dto.AlunoMonitorDTO;
import com.arksgrupo.Arks_Requiem.exception.ResourceNotFoundException;
import com.arksgrupo.Arks_Requiem.model.AlunoMonitor; 
import com.arksgrupo.Arks_Requiem.repository.AlunoMonitorRepository;

@Service
public class AlunoMonitorService {
    @Autowired
    private AlunoMonitorRepository repository;

    
    // SALVAR
    public AlunoMonitorDTO salvar(AlunoMonitorDTO dto){
        AlunoMonitor aluno = converterParaEntity(dto);
        aluno = repository.save(aluno);

        return converterParaDTO(aluno);
    }

 // LISTAR
    public List<AlunoMonitorDTO> listar(){
        return repository.findAll().stream().map(this::converterParaDTO).collect(Collectors.toList());
    }
   
    // BUSCAR POR MATRICULA
    public AlunoMonitorDTO buscarPorMatricula(Long matricula){
        AlunoMonitor aluno = repository.findById(matricula).orElseThrow(() ->new ResourceNotFoundException("Aluno não encontrado: " + matricula));

        return converterParaDTO(aluno);
    }

    // ATUALIZAR
    public AlunoMonitorDTO atualizar(Long matricula, AlunoMonitorDTO dto){AlunoMonitor aluno = repository.findById(matricula).orElseThrow(() ->new ResourceNotFoundException("Aluno não encontrado: " + matricula));
        aluno.setNomeCompleto(dto.getNome());
        aluno.setStatus(dto.getStatus() != null ? dto.getStatus() : true);


        aluno = repository.save(aluno);

        return converterParaDTO(aluno);
    }

       // INATIVAR (SOFT DELETE)
    @Transactional
    public void inativar(Long matricula){
        AlunoMonitor aluno = repository.findById(matricula).orElseThrow(() ->new ResourceNotFoundException("Aluno não encontrado: " + matricula));
        
        aluno.setStatus(false);
        repository.save(aluno);
    }


     // DTO → ENTITY
    private AlunoMonitor converterParaEntity(AlunoMonitorDTO dto){
        AlunoMonitor aluno = new AlunoMonitor();

        aluno.setMatricula(dto.getMatricula());
        aluno.setNomeCompleto(dto.getNome());
        aluno.setStatus(dto.getStatus() != null ? dto.getStatus() : true);


        return aluno;
    }


    // ENTITY → DTO
    private AlunoMonitorDTO converterParaDTO(AlunoMonitor aluno){
        AlunoMonitorDTO dto = new AlunoMonitorDTO();

        dto.setMatricula(aluno.getMatricula());
        dto.setNome(aluno.getNomeCompleto());
        dto.setdataCadastro(aluno.getDataCadastro());
        dto.setStatus(aluno.getStatus());


        return dto;
    }

}
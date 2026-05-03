package com.arksgrupo.Arks_Requiem.service;

import com.arksgrupo.Arks_Requiem.dto.DisciplinaDTO;
import com.arksgrupo.Arks_Requiem.model.Disciplina;
import com.arksgrupo.Arks_Requiem.repository.DisciplinaRepository;
import com.arksgrupo.Arks_Requiem.repository.EscolaRepository;
import com.arksgrupo.Arks_Requiem.repository.MatrizRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DisciplinaService {
    @Autowired
    private DisciplinaRepository repository;
    @Autowired
    private EscolaRepository escolaRepository;
    @Autowired
    private MatrizRepository matrizRepository;

    public DisciplinaDTO salvar(DisciplinaDTO dto){

        Disciplina disciplina = converterParaEntity(dto);

        disciplina.setDataCadastro(LocalDateTime.now());

        disciplina = repository.save(disciplina);

        return converterParaDTO(disciplina);
    }


    public List<DisciplinaDTO> listarTodos(){
        return repository.findAll().stream().map(this::converterParaDTO).collect(Collectors.toList());
    }


    public DisciplinaDTO buscarPorId(Long id){
        Disciplina disciplina = repository.findById(id).orElseThrow();
        return converterParaDTO(disciplina);
    }


    public DisciplinaDTO atualizar(Long id, DisciplinaDTO dto){
        Disciplina disciplina = repository.findById(id).orElseThrow();

        disciplina.setSigla(dto.getSigla());
        disciplina.setDescricao(dto.getDescricao());
        disciplina.setCargaHoraria(dto.getCargaHoraria());
        disciplina.setStatus(Boolean.TRUE.equals(dto.getStatus()));
        disciplina.setPreRequisitosStr(dto.getPreRequisitosStr());
        disciplina.setEscola(escolaRepository.findById(dto.getEscolaId()).orElseThrow());
        disciplina.setMatriz(matrizRepository.findById(dto.getMatrizId()).orElseThrow());

        disciplina = repository.save(disciplina);

        return converterParaDTO(disciplina);
    }


    @Transactional // [AJUSTE]
    public void inativar(Long id){
        Disciplina disciplina = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Disciplina não encontrada com id: " + id));

        disciplina.setStatus(false);
        repository.save(disciplina);
    }

    private Disciplina converterParaEntity(DisciplinaDTO dto){

        Disciplina disciplina = new Disciplina();

        disciplina.setSigla(dto.getSigla());
        disciplina.setDescricao(dto.getDescricao());
        disciplina.setCargaHoraria(dto.getCargaHoraria());
        disciplina.setStatus(Boolean.TRUE.equals(dto.getStatus()));
        disciplina.setPreRequisitosStr(dto.getPreRequisitosStr());
        disciplina.setEscola(escolaRepository.findById(dto.getEscolaId()).orElseThrow());
        disciplina.setMatriz(matrizRepository.findById(dto.getMatrizId()).orElseThrow());

        return disciplina;
    }

    private DisciplinaDTO converterParaDTO(Disciplina disciplina){
        DisciplinaDTO dto = new DisciplinaDTO();
        
        dto.setId(disciplina.getId()); //[AJUSTE]
        dto.setSigla(disciplina.getSigla());
        dto.setDescricao(disciplina.getDescricao());
        dto.setCargaHoraria(disciplina.getCargaHoraria());
        dto.setStatus(disciplina.getStatus());
        dto.setPreRequisitosStr(disciplina.getPreRequisitosStr());
        dto.setDataCadastro(disciplina.getDataCadastro());
        dto.setEscolaId(disciplina.getEscola().getId());
        if(disciplina.getMatriz() != null){
            dto.setMatrizId(disciplina.getMatriz().getId());
        }
        return dto;
    }
}
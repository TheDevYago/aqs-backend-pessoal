package com.arksgrupo.Arks_Requiem.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arksgrupo.Arks_Requiem.dto.CursoDTO;
import com.arksgrupo.Arks_Requiem.exception.ResourceNotFoundException;
import com.arksgrupo.Arks_Requiem.model.Curso;
import com.arksgrupo.Arks_Requiem.repository.CursoRepository;
import com.arksgrupo.Arks_Requiem.repository.EscolaRepository;

@Service
public class CursoService {
    @Autowired
    private CursoRepository repository;
    @Autowired
    private EscolaRepository escolaRepository;

    public CursoDTO salvar(CursoDTO dto){
        Curso curso = converterParaEntity(dto);
        curso.setDataCadastro(LocalDateTime.now());
        curso = repository.save(curso);
        return converterParaDTO(curso);
    }

    public List<CursoDTO> listar(){
        return repository.findAll().stream().map(this::converterParaDTO).collect(Collectors.toList());
    }

    public CursoDTO buscarPorId(Long id){
        Curso curso = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado com id: " + id));
        return converterParaDTO(curso);
    }

    // ATUALIZAR CURSO
    public CursoDTO atualizar(Long id, CursoDTO dto){
        Curso curso =  repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado com id: " + id));
        
        curso.setSigla(dto.getSigla());
        curso.setDescricao(dto.getDescricao());
        curso.setTurno(dto.getTurno());
        curso.setCoordenador(dto.getCoordenadorCurso());
        curso.setStatus(dto.getStatus() != null ? dto.getStatus() : true);
        curso.setEscola(escolaRepository.findById(dto.getEscolaId()).orElseThrow(() -> new ResourceNotFoundException("Escola não encontrada")));

        curso = repository.save(curso);

        return converterParaDTO(curso);
    }

    @Transactional
    public void inativar(Long id){
        Curso curso = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado com id: " + id));
        
        curso.setStatus(false);
    }


    // DTO → ENTITY
    private Curso converterParaEntity(CursoDTO dto){
        Curso curso = new Curso();
        
        curso.setSigla(dto.getSigla());
        curso.setDescricao(dto.getDescricao());
        curso.setTurno(dto.getTurno());
        curso.setCoordenador(dto.getCoordenadorCurso());
        curso.setStatus(dto.getStatus() != null ? dto.getStatus() : true);

        curso.setEscola (escolaRepository.findById(dto.getEscolaId()).orElseThrow(() -> new ResourceNotFoundException("Escola não encontrada")));

        return curso;
    }

    // ENTITY → DTO 
    private CursoDTO converterParaDTO(Curso curso){
        CursoDTO dto = new CursoDTO();

        dto.setId(curso.getId());
        dto.setSigla(curso.getSigla());
        dto.setDescricao(curso.getDescricao());
        dto.setTurno(curso.getTurno());
        dto.setCoordenadorCurso(curso.getCoordenador());
        dto.setStatus(curso.getStatus());
        dto.setEscolaId(curso.getEscola().getId());
        dto.setDataCadastro(curso.getDataCadastro());

        return dto;
    }
}

package com.arksgrupo.Arks_Requiem.service;

import com.arksgrupo.Arks_Requiem.dto.FormacaoProfessorDTO;
import com.arksgrupo.Arks_Requiem.model.FormacaoProfessor;
import com.arksgrupo.Arks_Requiem.model.Professor;
import com.arksgrupo.Arks_Requiem.repository.FormacaoProfessorRepository;
import com.arksgrupo.Arks_Requiem.repository.ProfessorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormacaoProfessorService {
    @Autowired
    private FormacaoProfessorRepository repository;
    @Autowired
    private ProfessorRepository professorRepository;

    
    // SALVAR FORMAÇÃO
    public FormacaoProfessorDTO salvar(FormacaoProfessorDTO dto){
        FormacaoProfessor formacao = converterParaEntity(dto);
        formacao = repository.save(formacao);

        return converterParaDTO(formacao);
    }

     // LISTAR POR PROFESSOR
    public List<FormacaoProfessorDTO> listarPorProfessor(long matricula){
        return repository.findByProfessorMatricula(matricula).stream().map(this::converterParaDTO).collect(Collectors.toList());
    }

    // DELETAR FORMAÇÃO
    public void deletar(Long id){
        repository.deleteById(id);
    }

    // DTO → ENTITY
    private FormacaoProfessor
    converterParaEntity(FormacaoProfessorDTO dto){
        FormacaoProfessor formacao = new FormacaoProfessor();

        formacao.setTitulacao(dto.getTitulacao());
        formacao.setInstituicao(dto.getInstituicao());
        formacao.setAnoConclusao(dto.getAnoConclusao());
        formacao.setNomeCurso(dto.getNomeCurso());

        Professor professor =professorRepository.findById(dto.getProfessorMatricula()).orElseThrow();

        formacao.setProfessor(professor);

        return formacao;
    }


    // ENTITY → DTO
    private FormacaoProfessorDTO
    converterParaDTO(FormacaoProfessor formacao){

        FormacaoProfessorDTO dto =new FormacaoProfessorDTO();
        dto.setId(formacao.getId());
        dto.setTitulacao(formacao.getTitulacao());
        dto.setInstituicao(formacao.getInstituicao());
        dto.setAnoConclusao(formacao.getAnoConclusao());
        dto.setNomeCurso(formacao.getNomeCurso());
        dto.setProfessorMatricula(formacao.getProfessor().getMatricula());

        return dto;
    }

}

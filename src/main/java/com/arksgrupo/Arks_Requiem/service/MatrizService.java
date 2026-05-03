package com.arksgrupo.Arks_Requiem.service;

import java.time.LocalDateTime;
import java.util.List;

import com.arksgrupo.Arks_Requiem.dto.MatrizDTO;
import com.arksgrupo.Arks_Requiem.model.Disciplina;
import com.arksgrupo.Arks_Requiem.model.Matriz;
import com.arksgrupo.Arks_Requiem.repository.CursoRepository;
import com.arksgrupo.Arks_Requiem.repository.DisciplinaRepository;
import com.arksgrupo.Arks_Requiem.repository.MatrizRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
public class MatrizService {
    @Autowired
    private MatrizRepository repository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private DisciplinaRepository disciplinaRepository;
    
    // SALVAR MATRIZ
    public MatrizDTO salvar(MatrizDTO dto){
        validarMatrizAtiva(dto);
        Matriz matriz = converterParaEntity(dto);
        matriz.setDataCadastro(LocalDateTime.now());
        matriz = repository.save(matriz);
        return converterParaDTO(matriz);
    }

     // LISTAR MATRIZES
    public List<MatrizDTO> listarTodas(){
        return repository.findAll().stream().map(this::converterParaDTO).collect(Collectors.toList());
    }

    
    // BUSCAR MATRIZ
    public MatrizDTO buscarPorId(Long id){
        Matriz matriz = repository.findById(id).orElseThrow();

        return converterParaDTO(matriz);
    }


    // ATUALIZAR MATRIZ
    public MatrizDTO atualizar(Long id, MatrizDTO dto){
        validarMatrizAtiva(dto);

        Matriz matriz = repository.findById(id).orElseThrow();

        matriz.setNome(dto.getNome());
        matriz.setDescricao(dto.getDescricao());
        matriz.setStatus(dto.getStatus());
        matriz.setCurso(cursoRepository.findById(dto.getCursoId()).orElseThrow());

        matriz = repository.save(matriz);

        return converterParaDTO(matriz);
    }


    // DELETAR MATRIZ
    @Transactional
    public void inativar(Long id){
        Matriz matriz = repository.findById(id).orElseThrow(() -> new RuntimeException("Matriz não encontrada com id: " + id));

        matriz.setStatus(false);
        repository.save(matriz);
    }


    // VINCULAR DISCIPLINAS À MATRIZ
    public void vincularDisciplinas(Long matrizId, List<Long> disciplinasIds){
        Matriz matriz = repository.findById(matrizId).orElseThrow();
        if(!matriz.getStatus()){
            throw new RuntimeException("Não é possível vincular disciplinas a uma matriz inativa");
        }

        List<Disciplina> disciplinas = disciplinaRepository.findAllById(disciplinasIds);

        for(Disciplina disciplina : disciplinas){
            disciplina.setMatriz(matriz);
        }

        disciplinaRepository.saveAll(disciplinas);
}


    // REGRA: apenas 1 matriz ativa por curso
    private void validarMatrizAtiva(MatrizDTO dto){
        if(Boolean.TRUE.equals(dto.getStatus())){
            repository.findByCursoIdAndStatusTrue(dto.getCursoId()).ifPresent(matrizExistente -> {
                if(dto.getId() == null || !matrizExistente.getId().equals(dto.getId())){
                throw new RuntimeException("Já existe uma matriz ativa para este curso");
                }
            });
        }

    }


    // DTO → ENTITY
    private Matriz converterParaEntity(MatrizDTO dto){
        Matriz matriz = new Matriz();

        matriz.setNome(dto.getNome());
        matriz.setDescricao(dto.getDescricao());
        matriz.setStatus(dto.getStatus());
        matriz.setCurso(cursoRepository.findById(dto.getCursoId()).orElseThrow());

        return matriz;
    }


    // ENTITY → DTO
    private MatrizDTO converterParaDTO(Matriz matriz){
        MatrizDTO dto = new MatrizDTO();
        
        dto.setId(matriz.getId());
        dto.setNome(matriz.getNome());
        dto.setDescricao(matriz.getDescricao());
        dto.setStatus(matriz.getStatus());
        dto.setCursoId(matriz.getCurso().getId());
        dto.setDataCadastro( matriz.getDataCadastro());

        return dto;
    }
}


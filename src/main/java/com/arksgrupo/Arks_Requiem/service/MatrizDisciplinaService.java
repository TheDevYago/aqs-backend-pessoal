package com.arksgrupo.Arks_Requiem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arksgrupo.Arks_Requiem.model.Disciplina;
import com.arksgrupo.Arks_Requiem.model.Matriz;
import com.arksgrupo.Arks_Requiem.model.MatrizDisciplina;
import com.arksgrupo.Arks_Requiem.repository.DisciplinaRepository;
import com.arksgrupo.Arks_Requiem.repository.MatrizDisciplinaRepository;
import com.arksgrupo.Arks_Requiem.repository.MatrizRepository;

// Adiciona disciplina dentro da matriz curricular
@Service
public class MatrizDisciplinaService {
    @Autowired
    private MatrizDisciplinaRepository matrizDiciplinarepository;
    @Autowired
    private MatrizRepository matrizRepository;
    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public MatrizDisciplina adicionarDisciplina(Long matrizId, Long disciplinaId){
        Matriz matriz = matrizRepository.findById(matrizId).orElseThrow(() -> new RuntimeException("Matriz não encontrada"));
        Disciplina disciplina = disciplinaRepository.findById(disciplinaId).orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));
        MatrizDisciplina md = new MatrizDisciplina();

        md.setMatriz(matriz);

        md.setDisciplina(disciplina);

        return matrizDiciplinarepository.save(md);
    }
    
    // Lista disciplinas vinculadas à matriz curricular
    public List<MatrizDisciplina> listarDisciplinas(Long matrizId){
        return matrizDiciplinarepository.findByMatriz_Id(matrizId);
    }
}

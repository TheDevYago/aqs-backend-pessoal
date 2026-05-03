package com.arksgrupo.Arks_Requiem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arksgrupo.Arks_Requiem.model.MatrizDisciplina;
import com.arksgrupo.Arks_Requiem.model.MatrizDisciplinaId;

@Repository
public interface MatrizDisciplinaRepository extends JpaRepository<MatrizDisciplina, MatrizDisciplinaId> {
    // Lista todas as disciplinas vinculadas a uma matriz curricular
    List<MatrizDisciplina> findByMatriz_Id(Long matrizId);
}
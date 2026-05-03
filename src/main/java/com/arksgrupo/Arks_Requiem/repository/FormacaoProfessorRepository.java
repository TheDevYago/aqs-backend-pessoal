package com.arksgrupo.Arks_Requiem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.arksgrupo.Arks_Requiem.model.FormacaoProfessor;

public interface FormacaoProfessorRepository extends JpaRepository<FormacaoProfessor,Long>{
    List<FormacaoProfessor>
    findByProfessorMatricula(long matricula);
}
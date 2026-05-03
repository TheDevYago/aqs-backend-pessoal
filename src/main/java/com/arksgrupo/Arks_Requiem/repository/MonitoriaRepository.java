package com.arksgrupo.Arks_Requiem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.arksgrupo.Arks_Requiem.model.Monitoria;
import java.util.Optional;
import java.util.List;

public interface MonitoriaRepository extends JpaRepository<Monitoria, Long> {

    // Esse método ele meio que busca se existe um monitor ativo para a matrícula informada
    Optional<Monitoria> findByMatriculaAlunoAndStatusTrue(String matriculaAluno);

    List<Monitoria> findByProfessorMatricula(Long professorMatricula);
}
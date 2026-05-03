package com.arksgrupo.Arks_Requiem.repository;

import com.arksgrupo.Arks_Requiem.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
    // Aqui o Spring já cria o CRUD básico automaticamente
}
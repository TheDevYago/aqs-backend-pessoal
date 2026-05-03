package com.arksgrupo.Arks_Requiem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.arksgrupo.Arks_Requiem.model.DisciplinaPreRequisito;

@Repository
public interface DisciplinaPreRequisitoRepository extends JpaRepository<DisciplinaPreRequisito, Long> {
}
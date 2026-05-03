package com.arksgrupo.Arks_Requiem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arksgrupo.Arks_Requiem.model.AlunoMonitor;

@Repository
public interface AlunoMonitorRepository extends JpaRepository<AlunoMonitor, Long> {
    // O Spring Data JPA fornece os métodos CRUD básicos automaticamente

}
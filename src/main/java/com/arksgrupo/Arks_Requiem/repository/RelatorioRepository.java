package com.arksgrupo.Arks_Requiem.repository;

import com.arksgrupo.Arks_Requiem.model.Relatorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; 

@Repository
public interface RelatorioRepository extends JpaRepository<Relatorio, Long> {
}